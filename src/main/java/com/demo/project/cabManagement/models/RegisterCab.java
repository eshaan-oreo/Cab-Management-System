package com.demo.project.cabManagement.models;

import com.demo.project.cabManagement.enums.CabState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RegisterCab {

    private String cabId;
    private String cityId;
    private CabState cabState;

}
