package com.searshc.mygarage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.FamilyVehicle;

@Repository
public interface FamilyVehicleRepository extends GenericRepository<FamilyVehicle, Long> {

    @Query("SELECT fv FROM FamilyVehicle fv WHERE fv.familyId = :familyId")
    List<FamilyVehicle> getFamilyVehiclesByFamilyId(@Param("familyId") Long familyId);

    @Query("SELECT fv FROM ConfirmedVehicle cv INNER JOIN cv.familyVehicle fv WHERE cv.user.id = :userId")
    List<FamilyVehicle> getFamilyVehiclesByUserId(@Param("userId") Long userId);

    @Query("SELECT uv FROM FamilyVehicle uv where uv.tangibleId = :tangibleId")
    FamilyVehicle getFamilyVehicleByTangibleId(@Param("tangibleId") final Long tangibleId);

    @Query("SELECT fv FROM FamilyVehicle fv WHERE fv.familyId = :familyId AND fv.tangibleId IS NULL AND fv NOT IN (SELECT cv.familyVehicle FROM ConfirmedVehicle cv WHERE cv.familyVehicle.familyId=:familyId)")
    List<FamilyVehicle> getLocalNonConfirmedFamilyVehicles(@Param("familyId") Long familyId);
}
