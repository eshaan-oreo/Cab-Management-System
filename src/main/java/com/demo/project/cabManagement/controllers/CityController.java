package com.demo.project.cabManagement.controllers;

import com.demo.project.cabManagement.models.RegisterCity;
import com.demo.project.cabManagement.services.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/city")
@Slf4j
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping("")
    public ResponseEntity<String> registerCity(@RequestBody RegisterCity registerCity) {
        log.info("Registering a new City with input={}", registerCity);
        try{
            cityService.registerCity(registerCity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body("City registered successfully");
    }





}
