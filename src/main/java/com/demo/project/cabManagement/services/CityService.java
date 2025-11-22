package com.demo.project.cabManagement.services;

import com.demo.project.cabManagement.models.City;
import com.demo.project.cabManagement.models.RegisterCity;
import com.demo.project.cabManagement.repo.CityRepo;
import com.demo.project.cabManagement.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityService {

    private final CityRepo cityRepo;

    public AtomicInteger registerCities (List<RegisterCity> registerCities) {
        log.info("Registering new cities with input={}", registerCities);
        AtomicInteger successCnt = new AtomicInteger();
        try {
            registerCities.forEach(c -> {
                if (registerCity(c) == null) {
                    log.error("Failed to register city {}", c);
                } else successCnt.getAndIncrement();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return successCnt;
    }

    public City registerCity(RegisterCity registerCity) {
        log.info("Registering new cities with input={}", registerCity);
        if(cityRepo.existsById(registerCity.getCityId())){
            log.error("City with the given city id already exists");
            return null;
        }
        City city = MapperUtils.registerCityToCity(registerCity);
        log.info("Saving City={} in DB", city);
        cityRepo.saveAndFlush(city);
        return city;
    }

    public boolean ifCityExists(String cityId) {
        return cityRepo.existsById(cityId);
    }
}
