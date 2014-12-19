package com.searshc.mygarage.services.vehicle.component.status;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.searshc.mygarage.dtos.carprofile.component.VehicleComponentStatusDTO;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.services.vehicle.component.status.TireComponentStatusFactory.TireStatus;

public class TireComponentStatusFactoryTest {

	private TireComponentStatusFactory tireComponentStatusFactory;
	
	private static final String COMPONENT_NAME = "Tire Rotation";
	
	private ServiceRecord localServiceLessThan4Months;
	private ServiceRecord localServiceLessThan5Months;
	private ServiceRecord localServiceMoreThan5Months;
	
	private ServiceRecord ncdbServiceLessThan4Months;
	private ServiceRecord ncdbServiceLessThan5Months;
	private ServiceRecord ncdbServiceMoreThan5Months;
	
	@Before
	public void setUp(){
		this.tireComponentStatusFactory = new TireComponentStatusFactory();
		this.localServiceLessThan4Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForTires(true, 2); //Two months ago
		this.localServiceLessThan5Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForTires(true, 4); //Four months and 5 days ago
		this.localServiceMoreThan5Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForTires(true, 8); //Eight months ago
		
		this.ncdbServiceLessThan4Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForTires(false, 2); //Two months ago
		this.ncdbServiceLessThan5Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForTires(false, 4); //Four months and 5 days ago
		this.ncdbServiceMoreThan5Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForTires(false, 8); //Eight months ago 
	}
	
	/* START OF TEST FOR LOCAL SERVICE RECORDS */
	@Test
	public void localServiceShouldReturnOKStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceLessThan4Months);
		records.add(this.localServiceLessThan5Months);
		records.add(this.localServiceMoreThan5Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.tireComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(TireStatus.FULL.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(TireStatus.FULL.getDescription()));
	}
	
	@Test
	public void localServiceShouldReturnMaintenanceApproachingDueStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceLessThan5Months);
		records.add(this.localServiceMoreThan5Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.tireComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(TireStatus.MEDIUM.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(TireStatus.MEDIUM.getDescription()));
	}
	
	@Test
	public void localServiceShouldReturnSuggestTireRotationStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceMoreThan5Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.tireComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(TireStatus.LOW.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(TireStatus.LOW.getDescription()));
	}
	
	@Test
	public void localServiceSReturnSuggestAnnualInspectionStatusForNullList() {
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.tireComponentStatusFactory.createComponentStatus(null);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(TireStatus.EMPTY.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(TireStatus.EMPTY.getDescription()));
	}
	
	/* START OF TEST FOR NCDB SERVICE RECORDS */
	
	@Test
	public void ncdbServiceShouldReturnOKStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceLessThan4Months);
		records.add(this.ncdbServiceLessThan5Months);
		records.add(this.ncdbServiceMoreThan5Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.tireComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(TireStatus.FULL.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(TireStatus.FULL.getDescription()));
	}
	
	@Test
	public void ncdbServiceShouldReturnMaintenanceApproachingDueStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceLessThan5Months);
		records.add(this.ncdbServiceMoreThan5Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.tireComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(TireStatus.MEDIUM.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(TireStatus.MEDIUM.getDescription()));
	}
	
	@Test
	public void ncdbServiceShouldReturnSuggestTireRotationStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceMoreThan5Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.tireComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(TireStatus.LOW.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(TireStatus.LOW.getDescription()));
	}
	
	@Test
	public void ncdbServiceSReturnSuggestTireRotationStatusForNullList() {
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.tireComponentStatusFactory.createComponentStatus(null);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(TireStatus.EMPTY.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(TireStatus.EMPTY.getDescription()));
	}
	
	/* START OF TEST FOR LOCAL AND NCDB SERVICE RECORDS */
	@Test
	public void mixedServiceShouldReturnOKStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceLessThan4Months);
		records.add(this.ncdbServiceLessThan5Months);
		records.add(this.localServiceMoreThan5Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.tireComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(TireStatus.FULL.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(TireStatus.FULL.getDescription()));
	}
	
	@Test
	public void mixedServiceShouldReturnMaintenanceApproachingDueStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceLessThan5Months);
		records.add(this.localServiceMoreThan5Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.tireComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(TireStatus.MEDIUM.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(TireStatus.MEDIUM.getDescription()));
	}

}
