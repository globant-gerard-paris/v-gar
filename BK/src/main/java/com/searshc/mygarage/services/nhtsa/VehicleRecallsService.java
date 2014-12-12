package com.searshc.mygarage.services.nhtsa;

import java.util.List;

import org.springframework.stereotype.Service;

import com.searshc.mygarage.entities.recalls.VehicleRecalls;

@Service
public interface VehicleRecallsService {

    List<VehicleRecalls> getRecalls(final int year, final String make, final String model);

    List<VehicleRecalls> getRecallsOrderedByDate(final int year, final String make,
            final String model, final boolean isAscending);

    VehicleRecalls getLastRecall(final int year, final String make,
            final String model);

}
