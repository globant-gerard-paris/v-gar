package com.searshc.mygarage.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.Validate;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.dtos.VehicleConfirmationDTO;
import com.searshc.mygarage.entities.UserInformation;
import com.searshc.mygarage.entities.UserVehicle;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.repositories.UserVehicleRepository;

@Service
public class UserVehicleService extends GenericService<UserVehicle, Long, UserVehicleRepository>{

	@Inject
	private EntityManager em;
	
	public List<UserVehicle> getUserVehiclesByUserId(final Long userId) {
		return repository.getUserVehiclesByUserId(userId);
	}
	
	public int deleteUserVehiclesByUserId(final Long userId) {
		//return repository.deleteUserVehiclesByUserId(userId);
		Query query = em.createQuery(
			      "DELETE FROM UserVehicle uv WHERE uv.userInformation.id = :userId");
		int deletedCount = query.setParameter("userId", userId).executeUpdate();
		return deletedCount;
			  
	}
	
	public Set<UserVehicle> convert(final List<VehicleConfirmationDTO> vehicleConfirmationDTOs, final UserInformation user) {
		Validate.notNull(vehicleConfirmationDTOs, "Could not convert a null list of VehicleConfirmationDTO");
		Set<UserVehicle> result = new HashSet<UserVehicle>();
		UserVehicle userVehicle;
		Vehicle vehicle;
		Mapper mapper = new DozerBeanMapper();
		
		for(VehicleConfirmationDTO dto : vehicleConfirmationDTOs) {
			if(dto.isConfirmed() == false){
				continue;
			}
			vehicle = mapper.map(dto, Vehicle.class);
			
			userVehicle = new UserVehicle();
			userVehicle.setUser(user);
			userVehicle.setVehicle(vehicle);
			userVehicle.setConfirmed(dto.isConfirmed());
			
			result.add(userVehicle);
		}
		return result;
	}
	
	public Set<VehicleConfirmationDTO> convert(final List<Vehicle> vehicles, final boolean isConfirmed) {
		Set<VehicleConfirmationDTO> result = new HashSet<VehicleConfirmationDTO>();
		VehicleConfirmationDTO dto;
		for(Vehicle vehicle : vehicles) {
			dto = new VehicleConfirmationDTO();
			dto.setVehicleId(vehicle.getId());
			dto.setFamilyId(vehicle.getFamilyId());
			dto.setTangibleId(vehicle.getTangibleId());
			dto.setMake(vehicle.getMake());
			dto.setModel(vehicle.getModel());
			dto.setYear(vehicle.getYear());
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
	
	public Set<Vehicle> extractNoPersistedVehicles(Set<UserVehicle> userVehicles) {
		Set<Vehicle> result = new HashSet<Vehicle>();
		for(UserVehicle userVehicle : userVehicles) {
			Long id = userVehicle.getVehicle().getId();
			if(id == null || id == 0) {
				result.add(userVehicle.getVehicle());
			}
		}
		return result;
	}
}
