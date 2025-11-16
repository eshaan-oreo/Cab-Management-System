package com.demo.project.cabManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cab {

    @Id
    private String id;
    private String currentCityId;
    private String cabState;
//    private long onboardTime;
//    private long totalTimeTravelled;
    private long lastIdleTime; // using epochs for faster calculations


}
