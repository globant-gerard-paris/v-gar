package com.searshc.mygarage.services.vehicle.component.status;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.searshc.mygarage.dtos.carprofile.component.VehicleComponentStatusDTO;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.services.vehicle.component.status.BrakeComponentStatusFactory.BrakeStatus;

public class BrakeComponentStatusFactoryTest {

	private static final String COMPONENT_NAME = "Brakes Inspection";
	
	private BrakeComponentStatusFactory brakeComponentStatusFactory;
	
	private ServiceRecord localServiceLessThan10Months;
	private ServiceRecord localServiceLessThan11Months;
	private ServiceRecord localServiceMoreThan11Months;
	
	private ServiceRecord ncdbServiceLessThan10Months;
	private ServiceRecord ncdbServiceLessThan11Months;
	private ServiceRecord ncdbServiceMoreThan11Months;
	
	@Before
	public void setUp(){
		this.brakeComponentStatusFactory = new BrakeComponentStatusFactory();
		this.localServiceLessThan10Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForBrakes(true, 5); //Five months ago
		this.localServiceLessThan11Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForBrakes(true, 10); //ten months and 5 days ago
		this.localServiceMoreThan11Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForBrakes(true, 14); //Fourteen months ago
		
		this.ncdbServiceLessThan10Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForBrakes(false, 5); //Five months ago
		this.ncdbServiceLessThan11Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForBrakes(false, 10); //ten months and 5 days ago
		this.ncdbServiceMoreThan11Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForBrakes(false, 14); //Fourteen months ago 
	}
	
	/* START OF TEST FOR LOCAL SERVICE RECORDS */
	@Test
	public void localServiceShouldReturnOKStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceLessThan10Months);
		records.add(this.localServiceLessThan11Months);
		records.add(this.localServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(BrakeStatus.FULL.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(BrakeStatus.FULL.getDescription()));
	}
	
	@Test
	public void localServiceShouldReturnAnnualInspectionApproachingDueStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceLessThan11Months);
		records.add(this.localServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(BrakeStatus.MEDIUM.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(BrakeStatus.MEDIUM.getDescription()));
	}
	
	@Test
	public void localServiceShouldReturnSuggestAnnualInspectionStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(BrakeStatus.LOW.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(BrakeStatus.LOW.getDescription()));
	}
	
	@Test
	public void localServiceSReturnSuggestAnnualInspectionStatusForNullList() {
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(null);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(BrakeStatus.EMPTY.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(BrakeStatus.EMPTY.getDescription()));
	}
	
	/* START OF TEST FOR NCDB SERVICE RECORDS */
	
	@Test
	public void ncdbServiceShouldReturnOKStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceLessThan10Months);
		records.add(this.ncdbServiceLessThan11Months);
		records.add(this.ncdbServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(BrakeStatus.FULL.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(BrakeStatus.FULL.getDescription()));
	}
	
	@Test
	public void ncdbServiceShouldReturnAnnualInspectionApproachingDueStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceLessThan11Months);
		records.add(this.ncdbServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(BrakeStatus.MEDIUM.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(BrakeStatus.MEDIUM.getDescription()));
	}
	
	@Test
	public void ncdbServiceShouldReturnSuggestAnnualInspectionStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(BrakeStatus.LOW.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(BrakeStatus.LOW.getDescription()));
	}
	
	@Test
	public void ncdbServiceSReturnSuggestAnnualInspectionStatusForNullList() {
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(null);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(BrakeStatus.EMPTY.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(BrakeStatus.EMPTY.getDescription()));
	}
	
	/* START OF TEST FOR LOCAL AND NCDB SERVICE RECORDS */
	@Test
	public void mixedServiceShouldReturnOKStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceLessThan10Months);
		records.add(this.ncdbServiceLessThan11Months);
		records.add(this.localServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(BrakeStatus.FULL.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(BrakeStatus.FULL.getDescription()));
	}
	
	@Test
	public void mixedServiceShouldReturnAnnualInspectionApproachingDueStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceLessThan11Months);
		records.add(this.localServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(BrakeStatus.MEDIUM.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(BrakeStatus.MEDIUM.getDescription()));
	}

}
