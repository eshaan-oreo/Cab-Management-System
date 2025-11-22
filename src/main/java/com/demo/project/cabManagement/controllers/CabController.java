package com.demo.project.cabManagement.controllers;

import com.demo.project.cabManagement.models.*;
import com.demo.project.cabManagement.services.CabService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cab")
@Slf4j
@RequiredArgsConstructor
public class CabController {

    private final CabService cabService;

    @PostMapping("")
    public ResponseEntity<String> registerCab(@RequestBody RegisterCabInput registerCabInput) {
        log.info("Registering a new cab with input={}", registerCabInput);
        try{
            cabService.registerCabs(registerCabInput);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to register cabs due to error: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Cabs registered successfully");
    }

    @PutMapping("")
    public ResponseEntity<String> updateCab(@RequestBody RegisterCab updateCab) {
        log.info("Updating cabs with input={}", updateCab);
        try{
            cabService.updateCab(updateCab);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to update cab due to error: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Cab updated successfully");
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookCab(@RequestBody BookCabInput bookCabInput) {
        log.info("Booking cab with input={}", bookCabInput);
        try{
            String cabId = cabService.bookCab(bookCabInput);
            return ResponseEntity.status(HttpStatus.OK).body("Cab Booked successfully with cab id: " + cabId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to book cab due to error: " + e.getMessage());
        }
    }
}
