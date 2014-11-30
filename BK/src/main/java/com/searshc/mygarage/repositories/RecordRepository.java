package com.searshc.mygarage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.record.Record;

import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends GenericRepository<Record, Long> {

    @Query("SELECT r FROM Record r WHERE r.familyVehicle.id = :familyVehicleId")
    List<Record> getRecordsByFamilyVehicleId(@Param("familyVehicleId") final Long familyVehicleId);

    @Query("SELECT MAX(mileage) FROM Record r where r.familyVehicle.id = :familyVehicleId")
    Integer getHighestMileageByFamilyVehicleId(@Param("familyVehicleId") final Long familyVehicleId);

    @Query("SELECT COUNT(r.id) FROM Record r WHERE r.date > :date AND r.suggestedService.sku = :sku")
    Integer countByDateAndSku(@Param("date") Date date, @Param("sku") String sku);
    
    @Query("DELETE FROM Record r where r.id = :recordId")
    @Modifying
    void deleteRecord(@Param("recordId") Long recordId);
}
