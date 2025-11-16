package com.demo.project.cabManagement.services;

import com.demo.project.cabManagement.models.City;
import com.demo.project.cabManagement.models.RegisterCity;
import com.demo.project.cabManagement.repo.CityRepo;
import com.demo.project.cabManagement.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityService {

    private final CityRepo cityRepo;
    public City registerCity(RegisterCity registerCity) {
        log.info("Registering new cities with input={}", registerCity);
        if(cityRepo.existsById(registerCity.getCityId())){
            throw new RuntimeException("Cab with the given cab id already exists");
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
