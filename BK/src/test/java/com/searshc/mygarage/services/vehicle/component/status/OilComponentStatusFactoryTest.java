package com.searshc.mygarage.services.vehicle.component.status;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.searshc.mygarage.dtos.carprofile.component.VehicleComponentStatusDTO;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.services.vehicle.component.status.OilComponentStatusFactory.OilStatus;

public class OilComponentStatusFactoryTest {

	private OilComponentStatusFactory oilComponentStatusFactory;
	
	private static final String COMPONENT_NAME = "Oil Change";
	
	private ServiceRecord localServiceLessThan2Months;
	private ServiceRecord localServiceLessThan3Months;
	private ServiceRecord localServiceMoreThan3Months;
	
	private ServiceRecord ncdbServiceLessThan2Months;
	private ServiceRecord ncdbServiceLessThan3Months;
	private ServiceRecord ncdbServiceMoreThan3Months;
	
	@Before
	public void setUp(){
		this.oilComponentStatusFactory = new OilComponentStatusFactory();
		this.localServiceLessThan2Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForOil(true, 1); //One months ago
		this.localServiceLessThan3Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForOil(true, 2); //Two months and 5 days ago
		this.localServiceMoreThan3Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForOil(true, 6); //Six months ago
		
		this.ncdbServiceLessThan2Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForOil(false, 1); //One months ago
		this.ncdbServiceLessThan3Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForOil(false, 2); //Two months and 5 days ago
		this.ncdbServiceMoreThan3Months = ServiceRecordFactoryUtilForTest.generateServiceRecordForOil(false, 6); //Six months ago 
	}
	
	/* START OF TEST FOR LOCAL SERVICE RECORDS */
	@Test
	public void localServiceShouldReturnOKStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceLessThan2Months);
		records.add(this.localServiceLessThan3Months);
		records.add(this.localServiceMoreThan3Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.oilComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(OilStatus.OK.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(OilStatus.OK.getDescription()));
	}
	
	@Test
	public void localServiceShouldReturnMaintenanceApproachingDueStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceLessThan3Months);
		records.add(this.localServiceMoreThan3Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.oilComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(OilStatus.MEDIUM.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(OilStatus.MEDIUM.getDescription()));
	}
	
	@Test
	public void localServiceShouldReturnSuggestTireRotationStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceMoreThan3Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.oilComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(OilStatus.LOW.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(OilStatus.LOW.getDescription()));
	}
	
	@Test
	public void localServiceSReturnSuggestAnnualInspectionStatusForNullList() {
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.oilComponentStatusFactory.createComponentStatus(null);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(OilStatus.EMPTY.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(OilStatus.EMPTY.getDescription()));
	}
	
	/* START OF TEST FOR NCDB SERVICE RECORDS */
	
	@Test
	public void ncdbServiceShouldReturnOKStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceLessThan2Months);
		records.add(this.ncdbServiceLessThan3Months);
		records.add(this.ncdbServiceMoreThan3Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.oilComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(OilStatus.OK.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(OilStatus.OK.getDescription()));
	}
	
	@Test
	public void ncdbServiceShouldReturnMaintenanceApproachingDueStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceLessThan3Months);
		records.add(this.ncdbServiceMoreThan3Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.oilComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(OilStatus.MEDIUM.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(OilStatus.MEDIUM.getDescription()));
	}
	
	@Test
	public void ncdbServiceShouldReturnSuggestTireRotationStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceMoreThan3Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.oilComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(OilStatus.LOW.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(OilStatus.LOW.getDescription()));
	}
	
	@Test
	public void ncdbServiceSReturnSuggestTireRotationStatusForNullList() {
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.oilComponentStatusFactory.createComponentStatus(null);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(OilStatus.EMPTY.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(OilStatus.EMPTY.getDescription()));
	}
	
	/* START OF TEST FOR LOCAL AND NCDB SERVICE RECORDS */
	@Test
	public void mixedServiceShouldReturnOKStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.localServiceLessThan2Months);
		records.add(this.ncdbServiceLessThan3Months);
		records.add(this.localServiceMoreThan3Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.oilComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(OilStatus.OK.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(OilStatus.OK.getDescription()));
	}
	
	@Test
	public void mixedServiceShouldReturnMaintenanceApproachingDueStatus() {
		List<ServiceRecord> records = new ArrayList<ServiceRecord>();
		records.add(this.ncdbServiceLessThan3Months);
		records.add(this.localServiceMoreThan3Months);
		
		VehicleComponentStatusDTO vehicleComponentStatusDTO = this.oilComponentStatusFactory.createComponentStatus(records);
		assertTrue(vehicleComponentStatusDTO.getComponentName().equalsIgnoreCase(COMPONENT_NAME));
		assertTrue(vehicleComponentStatusDTO.getStatusName().equalsIgnoreCase(OilStatus.MEDIUM.name()));
		assertTrue(vehicleComponentStatusDTO.getStatusDescription().equalsIgnoreCase(OilStatus.MEDIUM.getDescription()));
	}

}
