package com.searshc.mygarage.services.vehicle.component.status;

import org.joda.time.DateTime;

import com.searshc.mygarage.entities.record.LocalServiceRecord;
import com.searshc.mygarage.entities.record.NcdbServiceRecord;
import com.searshc.mygarage.entities.record.RealServiceRecordItem;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.entities.record.ServiceRecordItem;
import com.searshc.mygarage.util.ServiceRecordType;

public class ServiceRecordFactoryUtilForTest {

	private static final String BRAKES_LOCAL_SERVICE_CODE = "02801300000";
	private static final String BRAKES_NCDB_SERVICE_CODE = "44";
	
	private static final String TIRES_LOCAL_SERVICE_CODE = "02801315000"; //Suggested New Tires
	private static final String TIRES_NCDB_SERVICE_CODE = "41"; //Tire Service
	
	private static final String OIL_LOCAL_SERVICE_CODE = "02806672000"; //Adjust Low Oil Level
	private static final String OIL_NCDB_SERVICE_CODE = "49"; //Oil Change Service
	
	
	private static ServiceRecord generateServiceRecord(boolean isLocalRecord, String serviceRecordItemCode, int monthsAgo) {
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
	
	public static ServiceRecord generateServiceRecordForBrakes(boolean isLocalRecord, int monthsAgo) {
		String serviceItemCode = isLocalRecord ? BRAKES_LOCAL_SERVICE_CODE : BRAKES_NCDB_SERVICE_CODE;
		return generateServiceRecord(isLocalRecord, serviceItemCode, monthsAgo);
	}
	
	public static ServiceRecord generateServiceRecordForTires(boolean isLocalRecord, int monthsAgo) {
		String serviceItemCode = isLocalRecord ? TIRES_LOCAL_SERVICE_CODE : TIRES_NCDB_SERVICE_CODE;
		return generateServiceRecord(isLocalRecord, serviceItemCode, monthsAgo);
	}
	public static ServiceRecord generateServiceRecordForOil(boolean isLocalRecord, int monthsAgo) {
		String serviceItemCode = isLocalRecord ? OIL_LOCAL_SERVICE_CODE : OIL_NCDB_SERVICE_CODE;
		return generateServiceRecord(isLocalRecord, serviceItemCode, monthsAgo);
	}
}
