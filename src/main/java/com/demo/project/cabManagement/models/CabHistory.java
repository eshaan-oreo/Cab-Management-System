package com.demo.project.cabManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CabHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String cabId;
    private String cabToState;
    private long time;
}
