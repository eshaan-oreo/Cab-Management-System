package com.demo.project.cabManagement.repo;

import com.demo.project.cabManagement.models.Cab;
import com.demo.project.cabManagement.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends JpaRepository<City, String> {
}
