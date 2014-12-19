package com.searshc.mygarage.services.vehicle.component.status;

import org.joda.time.DateTime;

import com.searshc.mygarage.entities.record.LocalServiceRecord;
import com.searshc.mygarage.entities.record.NcdbServiceRecord;
import com.searshc.mygarage.entities.record.RealServiceRecordItem;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.entities.record.ServiceRecordItem;
import com.searshc.mygarage.util.ServiceRecordType;

public class ServiceRecordFactoryUtilForTest {

	/*private final String BRAKES_LOCAL_SERVICE_CODE = "02801300000";
	private final String BRAKES_NCDB_SERVICE_CODE = "44";
	
	private final String TIRES_LOCAL_SERVICE_CODE = "02801315000"; //Suggested New Tires
	private final String TIRES_NCDB_SERVICE_CODE = "41"; //Tire Service
	
	private final String OIL_LOCAL_SERVICE_CODE = "02806672000"; //Adjust Low Oil Level
	private final String OIL_NCDB_SERVICE_CODE = "49"; //Oil Change Service*/
	
	private String brakesLocalServiceCode;
	private String brakesNCDBServiceCode;
	
	private String tiresLocalServiceCode; //Suggested New Tires
	private String tiresNCDBServiceCode; //Tire Service
	
	private String oilLocalServiceCode; //Adjust Low Oil Level
	private String oilNCDBServiceCode; //Oil Change Service
	
	
	
	
	/**
	 * @param brakesLocalServiceCode
	 * @param brakesNCDBServiceCode
	 * @param tiresLocalServiceCode
	 * @param tiresNCDBServiceCode
	 * @param oilLocalServiceCode
	 * @param oilNCDBServiceCode
	 */
	public ServiceRecordFactoryUtilForTest(String brakesLocalServiceCode,
			String brakesNCDBServiceCode, String tiresLocalServiceCode,
			String tiresNCDBServiceCode, String oilLocalServiceCode,
			String oilNCDBServiceCode) {
		super();
		this.brakesLocalServiceCode = brakesLocalServiceCode;
		this.brakesNCDBServiceCode = brakesNCDBServiceCode;
		this.tiresLocalServiceCode = tiresLocalServiceCode;
		this.tiresNCDBServiceCode = tiresNCDBServiceCode;
		this.oilLocalServiceCode = oilLocalServiceCode;
		this.oilNCDBServiceCode = oilNCDBServiceCode;
	}

	private ServiceRecord generateServiceRecord(boolean isLocalRecord, String serviceRecordItemCode, int monthsAgo) {
		ServiceRecordItem serviceRecordItem = new RealServiceRecordItem();
		serviceRecordItem.setCode(serviceRecordItemCode);

		ServiceRecord serviceRecord;
		if(isLocalRecord) {
			serviceRecord = new LocalServiceRecord();
			serviceRecord.setType(ServiceRecordType.LOCAL_SERVICE);
		} else {
			serviceRecord = new NcdbServiceRecord();
			serviceRecord.setType(ServiceRecordType.REMOTE_SERVICE);
		}

		DateTime now = DateTime.now();
		DateTime serviceDate = now.minusMonths(monthsAgo);
		serviceRecord.setDate(serviceDate.toDate());
		serviceRecord.addServiceRecordItem(serviceRecordItem);
		return serviceRecord;
	}
	
	public ServiceRecord generateServiceRecordForBrakes(boolean isLocalRecord, int monthsAgo) {
		String serviceItemCode = isLocalRecord ? brakesLocalServiceCode : brakesNCDBServiceCode;
		return generateServiceRecord(isLocalRecord, serviceItemCode, monthsAgo);
	}
	
	public ServiceRecord generateServiceRecordForTires(boolean isLocalRecord, int monthsAgo) {
		String serviceItemCode = isLocalRecord ? tiresLocalServiceCode : tiresNCDBServiceCode;
		return generateServiceRecord(isLocalRecord, serviceItemCode, monthsAgo);
	}
	public ServiceRecord generateServiceRecordForOil(boolean isLocalRecord, int monthsAgo) {
		String serviceItemCode = isLocalRecord ? oilLocalServiceCode : oilNCDBServiceCode;
		return generateServiceRecord(isLocalRecord, serviceItemCode, monthsAgo);
	}
}
