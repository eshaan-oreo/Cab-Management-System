package com.demo.project.cabManagement.models;

import com.demo.project.cabManagement.enums.CabState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RegisterCity {
    private String cityId;
    private String cityName;
}
