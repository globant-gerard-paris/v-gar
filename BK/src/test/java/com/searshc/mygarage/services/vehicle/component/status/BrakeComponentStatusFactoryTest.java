package com.searshc.mygarage.services.vehicle.component.status;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.searshc.mygarage.dtos.carprofile.component.VehicleComponentStatusDTO;
import com.searshc.mygarage.entities.record.ServiceRecord;

public class BrakeComponentStatusFactoryTest {

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
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase("BRAKE"));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase("OK"));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase("OK"));
	}
	
	@Test
	public void localServiceShouldReturnAnnualInspectionApproachingDueStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceLessThan11Months);
		records.add(this.localServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase("BRAKE"));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase("ANNUAL_INSPECTION_APPROACHING_DUE"));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase("Annual inspection approaching due"));
	}
	
	@Test
	public void localServiceShouldReturnSuggestAnnualInspectionStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase("BRAKE"));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase("SUGGEST_ANNUAL_INSPECTION"));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase("Suggest annual inspection"));
	}
	
	@Test
	public void localServiceSReturnSuggestAnnualInspectionStatusForNullList() {
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(null);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase("BRAKE"));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase("SUGGEST_ANNUAL_INSPECTION"));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase("Suggest annual inspection"));
	}
	
	/* START OF TEST FOR NCDB SERVICE RECORDS */
	
	@Test
	public void ncdbServiceShouldReturnOKStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceLessThan10Months);
		records.add(this.ncdbServiceLessThan11Months);
		records.add(this.ncdbServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase("BRAKE"));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase("OK"));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase("OK"));
	}
	
	@Test
	public void ncdbServiceShouldReturnAnnualInspectionApproachingDueStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceLessThan11Months);
		records.add(this.ncdbServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase("BRAKE"));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase("ANNUAL_INSPECTION_APPROACHING_DUE"));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase("Annual inspection approaching due"));
	}
	
	@Test
	public void ncdbServiceShouldReturnSuggestAnnualInspectionStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase("BRAKE"));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase("SUGGEST_ANNUAL_INSPECTION"));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase("Suggest annual inspection"));
	}
	
	@Test
	public void ncdbServiceSReturnSuggestAnnualInspectionStatusForNullList() {
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(null);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase("BRAKE"));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase("SUGGEST_ANNUAL_INSPECTION"));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase("Suggest annual inspection"));
	}
	
	/* START OF TEST FOR LOCAL AND NCDB SERVICE RECORDS */
	@Test
	public void mixedServiceShouldReturnOKStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceLessThan10Months);
		records.add(this.ncdbServiceLessThan11Months);
		records.add(this.localServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase("BRAKE"));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase("OK"));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase("OK"));
	}
	
	@Test
	public void mixedServiceShouldReturnAnnualInspectionApproachingDueStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceLessThan11Months);
		records.add(this.localServiceMoreThan11Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.brakeComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase("BRAKE"));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase("ANNUAL_INSPECTION_APPROACHING_DUE"));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase("Annual inspection approaching due"));
	}

}
