package com.demo.project.cabManagement.services;

import com.demo.project.cabManagement.enums.CabState;
import com.demo.project.cabManagement.models.Cab;
import com.demo.project.cabManagement.models.CabHistory;
import com.demo.project.cabManagement.models.DurationInput;
import com.demo.project.cabManagement.repo.CabHistoryRepo;
import com.demo.project.cabManagement.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CabHistoryService {
    private final CabHistoryRepo cabHistoryRepo;

    public void setHistory(Cab cab) {
        CabHistory cabHistory = MapperUtils.cabToCabHistory(cab);
        cabHistoryRepo.saveAndFlush(cabHistory);
    }

    public List<CabHistory> getAllHistoryByCabIdWithTimeGreaterThanStartTimeAndLessThanEndTime(String cabId, Long startTime, Long endTime) {
        return cabHistoryRepo.findAllByCabIdWithTimeGreaterThanStartTimeAndLessThanEndTime(cabId, startTime, endTime);
    }
}
