package com.demo.project.cabManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
public class City {

    @Id
    private String id;
    private String name;

}
