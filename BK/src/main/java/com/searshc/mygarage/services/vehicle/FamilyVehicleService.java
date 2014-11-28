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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scala.collection.mutable.StringBuilder;

import com.searshc.mygarage.apis.ncdb.NCDBApi;
import com.searshc.mygarage.apis.syw.SYWApi;
import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.dtos.VehicleConfirmationDTO;
import com.searshc.mygarage.dtos.VehicleConfirmationDTO.Status;
import com.searshc.mygarage.dtos.home.HomeDto;
import com.searshc.mygarage.entities.ConfirmedVehicle;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.exceptions.FamilyVehicleNotFoundException;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.exceptions.UserNotFoundException;
import com.searshc.mygarage.repositories.FamilyVehicleRepository;
import com.searshc.mygarage.services.ncdb.NCDBLocalService;
import com.searshc.mygarage.services.ncdb.NcdbService;
import com.searshc.mygarage.services.record.RecordService;
import com.searshc.mygarage.services.user.UserService;

@Service
@Transactional
public class FamilyVehicleService extends GenericService<FamilyVehicle, Long, FamilyVehicleRepository> {

<<<<<<< HEAD
    private static final Log log = LogFactory.getLog(FamilyVehicleService.class);

    @Inject
    private SYWApi sywApi;

    @Inject
    private NCDBApi ncdbApi;

    @Inject
    private NCDBLocalService ncdbLocal;

    @Inject
    private UserService userService;

    @Inject
    private VehicleService vehicleService;

    private RecordService recordService;
    private NcdbService ncdbService;

    @Inject
    public FamilyVehicleService(final RecordService recordService, final NcdbService ncdbService) {
        this.recordService = Validate.notNull(recordService, "The Record Service cannot be null");
        this.ncdbService = Validate.notNull(ncdbService, "The NCDB Service cannot be null");
    }

    @Override
    public FamilyVehicle getItem(final Long id) {
    	FamilyVehicle familyVehicle = this.repository.findOne(id);
    	if(familyVehicle == null) {
    		throw new FamilyVehicleNotFoundException("FamilyVehicle not found with id: " + id);
    	}
    	return familyVehicle;
    }
    
    public FamilyVehicle createAndSaveNewVehicle(final Long familyId, final Long tangibleId,
            final String make, final String model, final int year, final String color, final int mileage) {
        Vehicle vehicle = new Vehicle(year, make, model, null, null);
        FamilyVehicle familyVehicle = new FamilyVehicle(vehicle, familyId, tangibleId, null, mileage, null);
        return super.save(familyVehicle);
    }
=======
	private static final Log log = LogFactory.getLog(FamilyVehicleService.class);
	
	@Inject
	private SYWApi sywApi;

	@Inject
	private NCDBApi ncdbApi;

	@Inject
	private NCDBLocalService ncdbLocal;

	@Inject
	private UserService userService;
	
	@Inject
	private VehicleService vehicleService;
	
	private RecordService recordService;
	private NcdbService ncdbService;

	@Inject
	public FamilyVehicleService(final RecordService recordService, final NcdbService ncdbService) {
		this.recordService = Validate.notNull(recordService, "The Record Service cannot be null");
		this.ncdbService = Validate.notNull(ncdbService, "The NCDB Service cannot be null");
	}
	
	@Override
	public FamilyVehicle getItem(final Long id) throws FamilyVehicleNotFoundException {
		Validate.notNull(id, "The FamilyVehicleId cannot be null");
		FamilyVehicle result = this.repository.findOne(id);
		if(result == null) {
			throw new FamilyVehicleNotFoundException("FamilyVehicle not found with id: " + id);
		}
		return result;
	}
>>>>>>> MYG000-98 - Updated Add Manual Vehicle Method

    public List<FamilyVehicle> getFamilyVehiclesByFamilyId(final Long familyId) {
        return repository.getFamilyVehiclesByFamilyId(familyId);
    }

    public List<FamilyVehicle> getConfirmedFamilyVehiclesByUserId(final Long userId) {
        return repository.getFamilyVehiclesByUserId(userId);
    }

    public FamilyVehicle getFamilyVehicleByTangibleId(final Long tangibleId) {
        return repository.getFamilyVehicleByTangibleId(tangibleId);
    }

<<<<<<< HEAD
    public Integer getHighestMileage(final Long familyVehicleId) throws NCDBApiException {
        Integer highestMileage = -1;

        FamilyVehicle familyVehicle = this.getItem(familyVehicleId);
        Integer databaseHighestMileage = this.recordService.getHighestMileageByFamilyVehicleId(familyVehicleId);
        Integer ncdbHighMileage = this.ncdbService.getHighestMileage(familyVehicle.getFamilyId(), familyVehicle.getTangibleId());
        highestMileage = databaseHighestMileage > ncdbHighMileage ? databaseHighestMileage : ncdbHighMileage;
        log.debug(highestMileage + "is the Highest Mileage for familyVehicleId " + familyVehicleId);
        return highestMileage;
=======
    public Integer getHighestMileage(final Long familyVehicleId) throws FamilyVehicleNotFoundException, NCDBApiException {
    	Integer highestMileage = -1;
    	
    	FamilyVehicle familyVehicle = this.getItem(familyVehicleId);

		Integer databaseHighestMileage = this.recordService.getHighestMileageByFamilyVehicleId(familyVehicleId);
		Integer ncdbHighMileage = this.ncdbService.getHighestMileage(familyVehicle.getFamilyId(), familyVehicle.getTangibleId());
		highestMileage = databaseHighestMileage > ncdbHighMileage ? databaseHighestMileage : ncdbHighMileage;
		log.debug(highestMileage + "is the Highest Mileage for familyVehicleId " + familyVehicleId);
		return highestMileage;
>>>>>>> MYG000-98 - Updated Add Manual Vehicle Method
    }

    public Set<FamilyVehicle> combineNCDBAndLocalVehicles(final List<FamilyVehicle> ncdbVehicles, final List<FamilyVehicle> localVehicles) {
        Map<Long, FamilyVehicle> ncdbLocallyStoreVehiclesMap = new HashMap<Long, FamilyVehicle>();
        Long tangibleId;
        for (FamilyVehicle familyVehicle : localVehicles) {
            tangibleId = familyVehicle.getTangibleId();
            if (tangibleId != null && tangibleId >= 0) {
                ncdbLocallyStoreVehiclesMap.put(tangibleId, familyVehicle);
            }
        }

        FamilyVehicle ncdbVehicleLocallyStore;
        for (FamilyVehicle familyVehicle : ncdbVehicles) {
            ncdbVehicleLocallyStore = ncdbLocallyStoreVehiclesMap.get(familyVehicle.getTangibleId());
            if (ncdbVehicleLocallyStore != null) {
                familyVehicle.setId(ncdbVehicleLocallyStore.getId());
            }
        }

        Set<FamilyVehicle> result = new HashSet<FamilyVehicle>(localVehicles);
        result.addAll(ncdbVehicles);
        return result;
    }

    /**
     * Given a {@code token} prepare a all the home information.
     *
     * @param token
     * @return return the {@link HomeDto}.
     */
    public HomeDto getHomeInformation(String token) {
        User user = userService.processUserByToken(token);
        Set<VehicleConfirmationDTO> result = null;

        // If still familyId null, means that User not have register on NCDB.
        if (user.getFamilyId() != null) {
            result = createReport(user.getId(), user.getFamilyId());
        } else {
            result = createReportWithoutNCDB(user.getId());
        }

        return new HomeDto(result, user.getId());
    }

    /**
     * Create the {@code UserVehicleStatus} against to NCDB service information.
     *
     * @param userId of Virtual garage.
     * @return return {@link UserVehicleStatus}.
     */
    private Set<VehicleConfirmationDTO> createReport(final Long userId, final Long familyId) {
        Set<VehicleConfirmationDTO> result = new HashSet<VehicleConfirmationDTO>();
        List<FamilyVehicle> ncdbVehicles = ncdbApi.getVehicles(familyId);
        //If you want to look for the vehicles that belong a a family use "this.getVehiclesByFamilyId(familyId);"
        List<FamilyVehicle> localVehicles = getConfirmedFamilyVehiclesByUserId(userId);

        if (!CollectionUtils.isEmpty(ncdbVehicles)) {
            List<FamilyVehicle> linkedVehicles = getLinkedCar(localVehicles, ncdbVehicles);
            //FIXME: the difference between list should be using hashCode and equals methods
            //ncdbVehicles.removeAll(linkedVehicles);
            ncdbVehicles = this.getNcdbVehicles(ncdbVehicles, linkedVehicles);
            result.addAll(this.convert(ncdbVehicles, false));
            result.addAll(this.convert(linkedVehicles, true));
        }

        List<FamilyVehicle> manualCars = getManualVehicle(localVehicles, ncdbVehicles);
        result.addAll(this.convert(manualCars, true));
        return result;
    }

    /**
     * Calculate the list of {@link FamilyVehicle} that was added manually.
     *
     * @param localVehicle
     * @param ncdbVehicles
     * @return return {@link UserVehicleStatus}.
     */
    private List<FamilyVehicle> getManualVehicle(final List<FamilyVehicle> localVehicles, final List<FamilyVehicle> ncdbVehicles) {
        if (CollectionUtils.isEmpty(localVehicles)) {
            return new ArrayList<FamilyVehicle>();
        }

        if (CollectionUtils.isEmpty(ncdbVehicles)) {
            return localVehicles;
        }
        localVehicles.removeAll(ncdbVehicles);
        return localVehicles;
    }

    /**
     * Calculate the list of {@link FamilyVehicle} that was linked from NCDB.
     *
     * @param localVehicle
     * @param ncdbVehicles
     * @return return {@link UserVehicleStatus}.
     */
    private List<FamilyVehicle> getLinkedCar(final List<FamilyVehicle> localVehicles, final List<FamilyVehicle> ncdbVehicles) {
        List<FamilyVehicle> linkedVehicles = new ArrayList<FamilyVehicle>();
        if (!CollectionUtils.isEmpty(localVehicles) && !CollectionUtils.isEmpty(ncdbVehicles)) {
            for (FamilyVehicle ncdbVehicle : ncdbVehicles) {
                for (FamilyVehicle localVehicle : localVehicles) {
                    if (ncdbVehicle.getTangibleId().equals(localVehicle.getTangibleId())) {
                        linkedVehicles.add(localVehicle);
                    }
                }
            }
        }
        return linkedVehicles;
    }

    @Deprecated
    private List<FamilyVehicle> getNcdbVehicles(final List<FamilyVehicle> ncdbVehicles, final List<FamilyVehicle> linkedVehicles) {
        List<FamilyVehicle> result = new ArrayList<FamilyVehicle>();
        boolean isLinked = false;
        for (FamilyVehicle currentVehicle : ncdbVehicles) {
            isLinked = false;
            for (FamilyVehicle linkedVehicle : linkedVehicles) {
                if (currentVehicle.getTangibleId().equals(linkedVehicle.getTangibleId())) {
                    isLinked = true;
                }
            }
            if (!isLinked) {
                result.add(currentVehicle);
            }
        }
        return result;
    }

    /**
     * Create {@code UserVehicleStatus} without NCDB information because the
     * {@code userId} don't have familyId assigned. (Is the same that is not
     * have NCDB_Id).
     *
     * @param {@code userId} of Virtual garage.
     * @return return {
     * @linked UserVehicleStatus}.
     */
    private Set<VehicleConfirmationDTO> createReportWithoutNCDB(final Long userId) {
        Set<VehicleConfirmationDTO> result = new HashSet<VehicleConfirmationDTO>();
        List<FamilyVehicle> vehiclesUser = getConfirmedFamilyVehiclesByUserId(userId);
        if (!CollectionUtils.isEmpty(vehiclesUser)) {
            result.addAll(this.convert(vehiclesUser, true));
        }
        return result;
    }

    public Set<ConfirmedVehicle> convert(final List<VehicleConfirmationDTO> vehicleConfirmationDTOs, final User user) {
        Validate.notNull(vehicleConfirmationDTOs, "Could not convert a null list of VehicleConfirmationDTO");
        Set<ConfirmedVehicle> result = new HashSet<ConfirmedVehicle>();
        ConfirmedVehicle confirmedVehicle;
        FamilyVehicle familyVehicle;
        Mapper mapper = new DozerBeanMapper();

        for (VehicleConfirmationDTO dto : vehicleConfirmationDTOs) {
            familyVehicle = null;
            if (dto.isConfirmed() == false) {
                continue;
            }
            if (dto.getVehicleId() != 0) {
                try {
					familyVehicle = this.getItem(dto.getVehicleId());
				} catch (FamilyVehicleNotFoundException e) {
					log.error(e);
					continue;
				}
            } else if (dto.getTangibleId() != null) {
                familyVehicle = mapper.map(dto, FamilyVehicle.class);
                Vehicle vehicle = this.vehicleService.getVehicleByMakeModelAndYear(dto.getMake(), dto.getModel(), dto.getYear());
                if (vehicle == null) {
                    vehicle = this.vehicleService.save(new Vehicle(dto.getYear(), dto.getMake(), dto.getModel(), dto.getEngine(), null));
                }
                familyVehicle.setVehicle(vehicle);
            }

            if (familyVehicle == null || familyVehicle.getVehicle() == null) {
                //no vehicle found
                continue;
            }
            confirmedVehicle = new ConfirmedVehicle();
            confirmedVehicle.setUser(user);
            confirmedVehicle.setFamilyVehicle(familyVehicle);

            result.add(confirmedVehicle);
        }
        return result;
    }

    public Set<VehicleConfirmationDTO> convert(final List<FamilyVehicle> familyVehicles, final boolean isConfirmed) {
        Set<VehicleConfirmationDTO> result = new HashSet<VehicleConfirmationDTO>();
        VehicleConfirmationDTO dto;
        for (FamilyVehicle familyVehicle : familyVehicles) {
            dto = new VehicleConfirmationDTO();
            dto.setVehicleId(familyVehicle.getId());
            dto.setFamilyId(familyVehicle.getFamilyId());
            dto.setTangibleId(familyVehicle.getTangibleId());
            dto.setMake(familyVehicle.getVehicle().getMake());
            dto.setModel(familyVehicle.getVehicle().getModel());
            dto.setYear(familyVehicle.getVehicle().getYear());
            dto.setEngine(familyVehicle.getVehicle().getEngine());
            dto.setConfirmed(isConfirmed);
            dto.setStatus(this.determineFamlyVehicleStatus(familyVehicle));
            result.add(dto);
        }
        return result;
    }

    public Status determineFamlyVehicleStatus(final FamilyVehicle familyVehicle) {
        Long id = familyVehicle.getId();
        Long tangibleId = familyVehicle.getTangibleId();
        if (tangibleId != null) {
            if (id != null && id != 0) {
                return Status.LINKED;
            } else {
                return Status.NCDB;
            }
        } else if (id != null && id != 0) {
            return Status.MANUAL;
        } else {
            return null;
        }

    }

}
