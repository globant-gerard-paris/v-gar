package com.searshc.mygarage.services.vehicle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.dtos.VehicleConfirmationDTO;
import com.searshc.mygarage.entities.ConfirmedVehicle;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.repositories.ConfirmedVehicleRepository;

@Service
public class ConfirmedVehicleService extends GenericService<ConfirmedVehicle, Long, ConfirmedVehicleRepository> {

    private EntityManager em;

    @Inject
    public ConfirmedVehicleService(final EntityManager em) {
        this.em = Validate.notNull(em, "The Entity Manager cannot be null");
    }

    public List<ConfirmedVehicle> getConfirmedVehiclesByUserId(final Long userId) {
        return repository.getConfirmedVehiclesByUserId(userId);
    }

    public int deleteConfirmedVehiclesByUserId(final Long userId) {
        Query query = em.createQuery(
                "DELETE FROM ConfirmedVehicle cv WHERE cv.user.id = :userId");
        int deletedCount = query.setParameter("userId", userId).executeUpdate();
        return deletedCount;
    }

    

    public List<VehicleConfirmationDTO> discardUnconfirmed(VehicleConfirmationDTO[] vehicleConfirmationDTOs) {
        List<VehicleConfirmationDTO> result = new ArrayList<VehicleConfirmationDTO>();
        for (VehicleConfirmationDTO dto : vehicleConfirmationDTOs) {
            if (dto.isConfirmed()) {
                result.add(dto);
            }
        }
        return result;
    }

    public Set<FamilyVehicle> extractNoPersistedVehicles(Set<ConfirmedVehicle> confirmedVehicles) {
        Set<FamilyVehicle> result = new HashSet<FamilyVehicle>();
        for (ConfirmedVehicle confirmedVehicle : confirmedVehicles) {
            Long id = confirmedVehicle.getFamilyVehicle().getId();
            if (id == null || id == 0) {
                result.add(confirmedVehicle.getFamilyVehicle());
            }
        }
        return result;
    }
}
