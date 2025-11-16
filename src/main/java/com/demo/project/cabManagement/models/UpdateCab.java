package com.demo.project.cabManagement.models;

import com.demo.project.cabManagement.enums.UpdateType;
import lombok.Data;

@Data
public class UpdateCab extends RegisterCab{
    private UpdateType updateType;
}
