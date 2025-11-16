package com.demo.project.cabManagement.utils;

import com.demo.project.cabManagement.models.*;

public class MapperUtils {

    public static Cab registerCabToCab(RegisterCab registerCab) {
        return Cab.builder()
                .id(registerCab.getCabId())
                .cabState(registerCab.getCabState().toString())
                .currentCityId(registerCab.getCityId())
                .lastIdleTime(System.currentTimeMillis())
                .build();
    }

    public static City registerCityToCity(RegisterCity registerCity) {
        return City.builder()
                .id(registerCity.getCityId())
                .name(registerCity.getCityName())
                .build();
    }

    public static CabHistory cabToCabHistory(Cab cab) {
        return CabHistory.builder()
                .cabId(cab.getId())
                .cabToState(cab.getCabState())
                .time(System.currentTimeMillis())
                .build();
    }
}
