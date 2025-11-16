package com.demo.project.cabManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DurationInput {
    private String cabId;
    private long startTime;
    private long endTime;
}
