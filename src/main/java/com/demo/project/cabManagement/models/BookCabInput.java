package com.demo.project.cabManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookCabInput {

    private String fromCityId;
    private String toCityId;
}
