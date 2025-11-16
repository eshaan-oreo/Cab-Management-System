package com.demo.project.cabManagement.repo;

import com.demo.project.cabManagement.models.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabRepo extends JpaRepository<Cab, String> {


    @Query(value = "select * from Cab where current_city_id = :fromId and cab_state = :state", nativeQuery = true)
    public List<Cab> fetchCabs(String fromId, String state);
}
