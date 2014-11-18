package com.searshc.mygarage.services.vehicle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.Validate;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.dtos.VehicleConfirmationDTO;
import com.searshc.mygarage.entities.ConfirmedVehicle;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.entities.UserVehicle;
import com.searshc.mygarage.repositories.ConfirmedVehicleRepository;

@Service
public class ConfirmedVehicleService extends GenericService<ConfirmedVehicle, Long, ConfirmedVehicleRepository>{

	private EntityManager em;
	private UserVehicleService userVehicleService;
	
	@Inject
	public ConfirmedVehicleService(final EntityManager em, final UserVehicleService userVehicleService) {
		this.em = Validate.notNull(em, "The Entity Manager cannot be null");
		this.userVehicleService = Validate.notNull(userVehicleService, "The UserVehicle Service cannot be null");
	}
	
	public List<ConfirmedVehicle> getUserVehiclesByUserId(final Long userId) {
		return repository.getConfirmedVehiclesByUserId(userId);
	}
	
	public int deleteUserVehiclesByUserId(final Long userId) {
		Query query = em.createQuery(
			      "DELETE FROM ConfirmedVehicle cv WHERE cv.user.id = :userId");
		int deletedCount = query.setParameter("userId", userId).executeUpdate();
		return deletedCount;
	}
	
	public Set<ConfirmedVehicle> convert(final List<VehicleConfirmationDTO> vehicleConfirmationDTOs, final User user) {
		Validate.notNull(vehicleConfirmationDTOs, "Could not convert a null list of VehicleConfirmationDTO");
		Set<ConfirmedVehicle> result = new HashSet<ConfirmedVehicle>();
		ConfirmedVehicle confirmedVehicle;
		UserVehicle userVehicle;
		Mapper mapper = new DozerBeanMapper();
		
		for(VehicleConfirmationDTO dto : vehicleConfirmationDTOs) {
			if(dto.isConfirmed() == false){
				continue;
			}
			if(dto.getVehicleId() != 0) {
				userVehicle = this.userVehicleService.getItem(dto.getVehicleId());
			} else if(dto.getTangibleId() != null) {
				userVehicle = this.userVehicleService.getUserVehicleByTangibleId(dto.getTangibleId());
			} else {
				userVehicle = mapper.map(dto, UserVehicle.class);
			}

			confirmedVehicle = new ConfirmedVehicle();
			confirmedVehicle.setUser(user);
			confirmedVehicle.setVehicle(userVehicle);
			
			result.add(confirmedVehicle);
		}
		return result;
	}
	
	public Set<VehicleConfirmationDTO> convert(final List<UserVehicle> userVehicles, final boolean isConfirmed) {
		Set<VehicleConfirmationDTO> result = new HashSet<VehicleConfirmationDTO>();
		VehicleConfirmationDTO dto;
		for(UserVehicle userVehicle : userVehicles) {
			dto = new VehicleConfirmationDTO();
			dto.setVehicleId(userVehicle.getId());
			dto.setFamilyId(userVehicle.getFamilyId());
			dto.setTangibleId(userVehicle.getTangibleId());
			dto.setMake(userVehicle.getVehicle().getMake());
			dto.setModel(userVehicle.getVehicle().getModel());
			dto.setYear(userVehicle.getVehicle().getYear());
			dto.setConfirmed(isConfirmed);		
			result.add(dto);
		}
		return result;
	}
	
	public List<VehicleConfirmationDTO> discardUnconfirmed(VehicleConfirmationDTO[] vehicleConfirmationDTOs) {
		List<VehicleConfirmationDTO> result = new ArrayList<VehicleConfirmationDTO>();
		for(VehicleConfirmationDTO dto : vehicleConfirmationDTOs) {
			if(dto.isConfirmed()) {
				result.add(dto);
			}
		}
		return result;
	}
	
	public Set<UserVehicle> extractNoPersistedVehicles(Set<ConfirmedVehicle> confirmedVehicles) {
		Set<UserVehicle> result = new HashSet<UserVehicle>();
		for(ConfirmedVehicle confirmedVehicle : confirmedVehicles) {
			Long id = confirmedVehicle.getVehicle().getId();
			if(id == null || id == 0) {
				result.add(confirmedVehicle.getVehicle());
			}
		}
		return result;
	}
}
