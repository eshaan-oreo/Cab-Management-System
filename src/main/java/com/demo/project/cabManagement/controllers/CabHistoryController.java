package com.demo.project.cabManagement.controllers;

import com.demo.project.cabManagement.models.*;
import com.demo.project.cabManagement.services.CabHistoryService;
import com.demo.project.cabManagement.services.CabService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cab-history")
@Slf4j
@RequiredArgsConstructor
public class CabHistoryController {

    private final CabService cabService;

    @GetMapping("/idle-time")
    public ResponseEntity<String> registerCab(@RequestBody DurationInput durationInput) {
        try{
            long idleTime = cabService.getIdleTime(durationInput);
            return ResponseEntity.status(HttpStatus.OK).body("Cab was idle for " + idleTime + " milliseconds");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to fetch cab history due to error: " + e.getMessage());
        }
    }
}
