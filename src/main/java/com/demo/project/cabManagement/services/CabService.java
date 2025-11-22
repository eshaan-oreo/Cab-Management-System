package com.demo.project.cabManagement.services;

import com.demo.project.cabManagement.enums.CabState;
import com.demo.project.cabManagement.enums.UpdateType;
import com.demo.project.cabManagement.models.*;
import com.demo.project.cabManagement.repo.CabRepo;
import com.demo.project.cabManagement.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CabService {

    private final CabRepo cabRepo;
    private final CityService cityService;
    private final CabHistoryService cabHistoryService;

    public AtomicInteger registerCabs(RegisterCabInput registerCabInput) {
        AtomicInteger successCnt = new AtomicInteger();
        log.info("Registering new cabs with input={}", registerCabInput);
        List<RegisterCab> cabs = registerCabInput.getRegisterCabList();
        List<Cab> cabList = cabs.stream()
                .map(registerCab -> {
                    if (cabRepo.existsById(registerCab.getCabId())) {
                        log.error("Cab with the given cab id already exists, use update cab to update details");
                        return null;
                    }
                    successCnt.incrementAndGet();
                    Cab cab = MapperUtils.registerCabToCab(registerCab);
                    log.info("Saving cab={} in DB", cab);
                    cabRepo.saveAndFlush(cab);
                    return cab;
                })
                .filter(Objects::nonNull)
                .toList();
        cabRepo.saveAllAndFlush(cabList);
        return successCnt;
    }

    public void updateCab(RegisterCab updateCab) {
        if (!cabRepo.existsById(updateCab.getCabId())) {
            throw new RuntimeException("Cab with the given cab id doesn't exist");
        }
        log.info("Updating cab with input={}", updateCab);
        String cabId = updateCab.getCabId();
        log.info("Fetching cab={} from cabDb", cabId);
        Cab currentCab = cabRepo.getReferenceById(cabId);
        updateCity(updateCab, currentCab);
        updateState(updateCab, currentCab);
        cabRepo.saveAndFlush(currentCab);
        log.info("Saved cab={} in DB", currentCab);
    }

    private void updateState(RegisterCab updateCab, Cab cab) {
        if (cab.getCabState().equalsIgnoreCase(updateCab.getCabState().toString())) {
            throw new RuntimeException("Cab state is already " + cab.getCabState());
        }
        if (CabState.ON_TRIP.toString().equalsIgnoreCase(cab.getCabState())) {
            // if the cab's state changes from ON_TRIP to IDLE, update the last idle time
            cab.setLastIdleTime(System.currentTimeMillis());
        }
        cab.setCabState(updateCab.getCabState().toString());
        cabHistoryService.setHistory(cab);
    }

    private void updateCity(RegisterCab updateCab, Cab cab) {
        if (!cityService.ifCityExists(updateCab.getCityId())) {
            throw new RuntimeException("City ID doesn't exist");
        }
        cab.setCurrentCityId(updateCab.getCityId());
    }

    public String bookCab(BookCabInput bookCabInput) {
        /*
            Book a cab lying in city with id fromCityId, with the least lastIdleTime, to get the cab
             sitting idle the most; in case of collision of lastIdleTime, select any random cab out
             of them.
             Now change the cab's status to ON_TRIP and currentCityId to null
         */
        if (!cityService.ifCityExists(bookCabInput.getFromCityId())) {
            throw new RuntimeException("From City ID doesn't exist");
        }
        if (!cityService.ifCityExists(bookCabInput.getToCityId())) {
            throw new RuntimeException("To City ID doesn't exist");
        }
        List<Cab> cabList = cabRepo.fetchCabs(bookCabInput.getFromCityId(), "IDLE");

        Cab earliestCab = getEarliestIdleCab(cabList);
        if (earliestCab == null) {
            throw new RuntimeException("No cabs available at this time");
        }
        earliestCab.setCabState(CabState.ON_TRIP.toString());
        earliestCab.setCurrentCityId(bookCabInput.getFromCityId());
        cabRepo.saveAndFlush(earliestCab);
        cabHistoryService.setHistory(earliestCab);
        return earliestCab.getId();
    }

    private Cab getEarliestIdleCab(List<Cab> cabs) {
        long minLastIdleTime = cabs.stream()
                .mapToLong(Cab::getLastIdleTime)
                .min()
                .orElse(Long.MAX_VALUE);

        List<Cab> mostIdleTimeCabs = cabs.stream()
                .filter(cab -> cab.getLastIdleTime() == minLastIdleTime)
                .toList();

        if (!mostIdleTimeCabs.isEmpty()) {
            Random random = new Random();
            return mostIdleTimeCabs.get(random.nextInt(mostIdleTimeCabs.size()));
        }
        return null;
    }

    public long getIdleTime(DurationInput durationInput) {
        String cabId = durationInput.getCabId();
        if (!cabRepo.existsById(cabId)) {
            throw new RuntimeException("Cab with id " + cabId + " does not exist");
        }
        List<CabHistory> cabHistoryList = cabHistoryService.getAllHistoryByCabIdWithTimeGreaterThanStartTimeAndLessThanEndTime(cabId, durationInput.getStartTime(), durationInput.getEndTime());
        cabHistoryList.sort(Comparator.comparing(CabHistory::getTime));
        long lastTimeChange = durationInput.getStartTime();
        long totalIdleTime = 0;
        CabState lastCabState = CabState.IDLE;
        for (int i = 0; i < cabHistoryList.size(); i++) {
            CabHistory cabHistory = cabHistoryList.get(i);
            if (cabHistory.getCabToState().equals(CabState.IDLE)) {
                lastCabState = CabState.IDLE;
            } else {
                lastCabState = CabState.ON_TRIP;
                totalIdleTime += cabHistory.getTime() - lastTimeChange;
            }
            lastTimeChange = cabHistory.getTime();
        }
        if (lastCabState == CabState.IDLE) {
            totalIdleTime += durationInput.getEndTime() - lastTimeChange;
        }
        return totalIdleTime;
    }
}
