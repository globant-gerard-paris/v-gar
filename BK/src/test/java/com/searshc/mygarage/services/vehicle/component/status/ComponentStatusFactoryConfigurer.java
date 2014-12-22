package com.searshc.mygarage.services.vehicle.component.status;

import java.util.HashSet;
import java.util.Set;

import org.mockito.internal.util.reflection.Whitebox;

public class ComponentStatusFactoryConfigurer {

	private final String BRAKES_LOCAL_SERVICE_CODE = "02801300000";
	private final String BRAKES_NCDB_SERVICE_CODE = "44";
	
	private final String TIRES_LOCAL_SERVICE_CODE = "02801315000"; //Suggested New Tires
	private final String TIRES_NCDB_SERVICE_CODE = "41"; //Tire Service
	
	private final String OIL_LOCAL_SERVICE_CODE = "02806672000"; //Adjust Low Oil Level
	private final String OIL_NCDB_SERVICE_CODE = "49"; //Oil Change Service*/
	
	protected ServiceRecordFactoryUtilForTest serviceRecordFactoryUtilForTest;
	protected TireComponentStatusFactory tireComponentStatusFactory;
	protected BrakeComponentStatusFactory brakeComponentStatusFactory;
	protected OilComponentStatusFactory oilComponentStatusFactory;
	
	public ComponentStatusFactoryConfigurer() {
		this.serviceRecordFactoryUtilForTest = new ServiceRecordFactoryUtilForTest(BRAKES_LOCAL_SERVICE_CODE,
				BRAKES_NCDB_SERVICE_CODE, TIRES_LOCAL_SERVICE_CODE, TIRES_NCDB_SERVICE_CODE,
				OIL_LOCAL_SERVICE_CODE, OIL_NCDB_SERVICE_CODE);
		this.tireComponentStatusFactory = new TireComponentStatusFactory();
		this.brakeComponentStatusFactory = new BrakeComponentStatusFactory();
		this.oilComponentStatusFactory = new OilComponentStatusFactory();
		
		Set<String> tireLocalServiceCategories = new HashSet<String>();
		tireLocalServiceCategories.add(TIRES_LOCAL_SERVICE_CODE);
		
		Set<String> tireNCDBServiceCategories = new HashSet<String>();
		tireNCDBServiceCategories.add(TIRES_NCDB_SERVICE_CODE);
		
		Set<String> brakesLocalServiceCategories = new HashSet<String>();
		brakesLocalServiceCategories.add(BRAKES_LOCAL_SERVICE_CODE);
		
		Set<String> brakesNCDBServiceCategories = new HashSet<String>();
		brakesNCDBServiceCategories.add(BRAKES_NCDB_SERVICE_CODE);
		
		Set<String> oilLocalServiceCategories = new HashSet<String>();
		oilLocalServiceCategories.add(OIL_LOCAL_SERVICE_CODE);
		
		Set<String> oilNCDBServiceCategories = new HashSet<String>();
		oilNCDBServiceCategories.add(OIL_NCDB_SERVICE_CODE);
		
		Whitebox.setInternalState(this.tireComponentStatusFactory, "localServiceCategories", tireLocalServiceCategories);
		Whitebox.setInternalState(this.tireComponentStatusFactory, "remoteServiceCategories", tireNCDBServiceCategories);
		
		Whitebox.setInternalState(this.brakeComponentStatusFactory, "localServiceCategories", brakesLocalServiceCategories);
		Whitebox.setInternalState(this.brakeComponentStatusFactory, "remoteServiceCategories", brakesNCDBServiceCategories);
		
		Whitebox.setInternalState(this.oilComponentStatusFactory, "localServiceCategories", oilLocalServiceCategories);
		Whitebox.setInternalState(this.oilComponentStatusFactory, "remoteServiceCategories", oilNCDBServiceCategories);
	}
	
}
