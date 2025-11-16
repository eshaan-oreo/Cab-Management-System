package com.demo.project.cabManagement.repo;

import com.demo.project.cabManagement.models.CabHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CabHistoryRepo extends JpaRepository<CabHistory,Long> {
    @Query(value = "select * from cab_history where cab_id = :cabId and time >= :startTime and time <= :endTime", nativeQuery = true)
    public List<CabHistory> findAllByCabIdWithTimeGreaterThanStartTimeAndLessThanEndTime(String cabId, long startTime, long endTime);
}
