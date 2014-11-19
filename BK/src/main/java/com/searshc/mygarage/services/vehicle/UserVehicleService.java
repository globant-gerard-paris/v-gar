package com.searshc.mygarage.services.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.searshc.mygarage.apis.ncdb.NCDBApi;
import com.searshc.mygarage.apis.syw.SYWApi;
import com.searshc.mygarage.apis.syw.SYWUtils;
import com.searshc.mygarage.apis.syw.response.SYWUserResponse;
import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.Record;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.entities.UserVehicle;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.repositories.UserVehicleRepository;
import com.searshc.mygarage.services.ncdb.NCDBLocalService;
import com.searshc.mygarage.services.user.UserService;

@Service
@Transactional
public class UserVehicleService extends GenericService<UserVehicle, Long, UserVehicleRepository> {

	@Inject
	private SYWApi sywApi;

	@Inject
	private NCDBApi ncdbApi;

	@Inject
	private NCDBLocalService ncdbLocal;

	@Inject
	private UserService userService;

	public UserVehicle createAndSaveNewVehicle(final Long familyId, final Long tangibleId,
			final String make, final String model, final int year, final String color,
			final int mileage) {
		Vehicle vehicle = new Vehicle(year, make, model, null, null);
		UserVehicle userVehicle = new UserVehicle(vehicle, familyId, tangibleId, null, mileage,
				null);
		return super.save(userVehicle);
	}

	public List<UserVehicle> getVehiclesByFamilyId(final Long familyId) {
		return repository.getUserVehiclesByFamilyId(familyId);
	}

	public List<UserVehicle> getVehiclesByUserId(final Long userId) {
		return repository.getUserVehiclesByUserId(userId);
	}

	public UserVehicle getUserVehicleByTangibleId(final Long tangibleId) {
		return repository.getUserVehicleByTangibleId(tangibleId);
	}

	public void saveRecord(Long familyId, Long tangibleId, Record record) throws Exception {

	}

	public Set<UserVehicle> combineNCDBAndLocalVehicles(final List<UserVehicle> ncdbVehicles,
			final List<UserVehicle> localVehicles) {
		Map<Long, UserVehicle> ncdbLocallyStoreVehiclesMap = new HashMap<Long, UserVehicle>();
		Long tangibleId;
		for (UserVehicle userVehicle : localVehicles) {
			tangibleId = userVehicle.getTangibleId();
			if (tangibleId != null && tangibleId >= 0) {
				ncdbLocallyStoreVehiclesMap.put(tangibleId, userVehicle);
			}
		}

		UserVehicle ncdbVehicleLocallyStore;
		for (UserVehicle userVehicle : ncdbVehicles) {
			ncdbVehicleLocallyStore = ncdbLocallyStoreVehiclesMap.get(userVehicle.getTangibleId());
			if (ncdbVehicleLocallyStore != null) {
				userVehicle.setId(ncdbVehicleLocallyStore.getId());
			}
		}

		Set<UserVehicle> result = new HashSet<UserVehicle>(localVehicles);
		result.addAll(ncdbVehicles);
		return result;
	}

	/**
	 * Given a {@code token} prepare a Status of vehicles for the user.
	 * 
	 * @param token
	 * @return
	 */
	public UserVehicleStatus getUserVehicleStatus(String token) {
		Validate.notNull(token, "The token can't be null.");

		Long sywId = SYWUtils.getSywId(token);
		Validate.notNull(sywId, "The token " + token + " is not valid.");

		User user = userService.findBySywId(sywId);
		if (user == null) {
			SYWUserResponse userInfoByToken = sywApi.getUserInfoByToken(token);
			Validate.notNull(userInfoByToken, "Not found user on SYW service with token: " + token);
			user = userService.createUserFromSYWRespone(userInfoByToken);
		}

		if (user.getFamilyId() == null) {
			String familyId = ncdbLocal.getNcdbIdBySywMemberNumber(user.getSywrMemberNumber());
			user = assignFamilyId(user, familyId);
		}
		
		UserVehicleStatus vehicleStatus;
		
		// If still familyId null, means that User not have register on NCDB.
		if (user.getFamilyId() != null) {
			vehicleStatus = createReport(user.getFamilyId());
		} else {
			vehicleStatus = createReportWithoutNCDB(user.getId());
		}
		
		vehicleStatus.setUserId(user.getId());

		return vehicleStatus;
	}

	private User assignFamilyId(final User user, final String ncdbId) {
		if (!StringUtils.isEmpty(ncdbId)) {
			user.setFamilyId(Long.valueOf(ncdbId));
		}
		return user;
	}

	/**
	 * Create the {@code UserVehicleStatus} against to NCDB service information.
	 * 
	 * @param userId of Virtual garage.
	 * @return return {@link UserVehicleStatus}.
	 */
	private UserVehicleStatus createReport(final Long familyId) {
		UserVehicleStatus vehicleStatus = new UserVehicleStatus();
		List<UserVehicle> ncdbVehicles = ncdbApi.getVehicles(familyId);
		List<UserVehicle> localVehicles = getVehiclesByFamilyId(familyId);

		if (!CollectionUtils.isEmpty(ncdbVehicles)) {
			vehicleStatus.setNcdbCars(ncdbVehicles);
			vehicleStatus.setLinkedCars(getLinkedCar(localVehicles, ncdbVehicles));
		}

		List<UserVehicle> manualCars = getManualVehicle(localVehicles, ncdbVehicles);
		vehicleStatus.setManualCars(manualCars);
		return vehicleStatus;
	}
	
	/**
	 * Calculate the list of {@link UserVehicle} that was added manually.
	 * @param localVehicle
	 * @param ncdbVehicles
	 * @return return {@link UserVehicleStatus}.
	 */
	private List<UserVehicle> getManualVehicle(final List<UserVehicle> localVehicles,final List<UserVehicle> ncdbVehicles){
		if(CollectionUtils.isEmpty(localVehicles)){
			return new ArrayList<UserVehicle>();
		}
		
		if(CollectionUtils.isEmpty(ncdbVehicles)){
			return localVehicles;
		}
		localVehicles.removeAll(ncdbVehicles);
		return localVehicles;
	}
	
	/**
	 * Calculate the list of {@link UserVehicle} that was linked from NCDB.
	 * @param localVehicle
	 * @param ncdbVehicles
	 * @return return {@link UserVehicleStatus}.
	 */
	private List<UserVehicle> getLinkedCar(final List<UserVehicle> localVehicles,final List<UserVehicle> ncdbVehicles){
		List<UserVehicle> linkedVehicles = new ArrayList<UserVehicle>();
		if (!CollectionUtils.isEmpty(localVehicles) && !CollectionUtils.isEmpty(ncdbVehicles)) {
			for (UserVehicle ncdbVehicle : ncdbVehicles) {
				for (UserVehicle localVehicle : localVehicles) {
					if(localVehicle.getTangibleId() == ncdbVehicle.getTangibleId()){
						linkedVehicles.add(localVehicle);
					}
				}
			}
		}
		return linkedVehicles;
	}

	/**
	 * Create {@code UserVehicleStatus} without NCDB information because the {@code userId} don't
	 * have familyId assigned. (Is the same that is not have NCDB_Id).
	 * 
	 * @param {@code userId} of Virtual garage.
	 * @return return {@linked UserVehicleStatus}.
	 */
	private UserVehicleStatus createReportWithoutNCDB(final Long userId) {
		UserVehicleStatus userVehicleStatus = new UserVehicleStatus();
		userVehicleStatus.setNcdbCars(new ArrayList<UserVehicle>());
		userVehicleStatus.setLinkedCars(new ArrayList<UserVehicle>());
		List<UserVehicle> vehiclesUser = getVehiclesByUserId(userId);
		if (!CollectionUtils.isEmpty(vehiclesUser)) {
			userVehicleStatus.setLinkedCars(vehiclesUser);
		}
		return userVehicleStatus;
	}

}
