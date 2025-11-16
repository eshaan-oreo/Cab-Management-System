package com.demo.project.cabManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RegisterCabInput {
    private List<RegisterCab> registerCabList;
}