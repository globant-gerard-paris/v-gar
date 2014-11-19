package com.searshc.mygarage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.Record;

public interface RecordRepository extends GenericRepository<Record, Long> {

    @Query("SELECT r FROM Record r WHERE r.userVehicle.id = :userVehicleId")
    List<Record> getRecordsByUserVehicleId(@Param("userVehicleId") final Long userVehicleId);
}
