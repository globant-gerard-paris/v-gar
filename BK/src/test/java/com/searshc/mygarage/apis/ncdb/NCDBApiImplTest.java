package com.searshc.mygarage.apis.ncdb;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.searshc.mygarage.apis.ncdb.response.vehicle.VehicleResponse;
import com.searshc.mygarage.entities.FamilyVehicle;

public class NCDBApiImplTest {

    private VehicleResponse vehicleResponse1;
    private VehicleResponse vehicleResponse2;
    private List<VehicleResponse> vehicleResponseList;

    @Before
    public void setUp() {
        this.vehicleResponse1 = new VehicleResponse();
        this.vehicleResponse1.setTangibleIdNumber(132623330);
        this.vehicleResponse1.setTangibleTypeCode("V");
        this.vehicleResponse1.setFamilyIdNumber(73311110);
        this.vehicleResponse1.setFamilyTypeCode("R");
        this.vehicleResponse1.setVehicleYearNumber(2008);
        this.vehicleResponse1.setVehicleMake("HONDA");
        this.vehicleResponse1.setVehicleModel("ACCORD");
        this.vehicleResponse1.setVehicleEngine("4-2354 2.4L DOHC");
        this.vehicleResponse1.setVinNumber("");
        this.vehicleResponse1.setVinLast6Digits("");
        this.vehicleResponse1.setCatalogId(32846);
        this.vehicleResponse1.setLicensePlateNumber("");
        this.vehicleResponse1.setLastUpdateUserId("2013-03-28 16:07:06.0");
        this.vehicleResponse1.setVehicleSts("");

        this.vehicleResponse2 = new VehicleResponse();
        this.vehicleResponse2.setTangibleIdNumber(132621017);
        this.vehicleResponse2.setTangibleTypeCode("V");
        this.vehicleResponse2.setFamilyIdNumber(73311110);
        this.vehicleResponse2.setFamilyTypeCode("R");
        this.vehicleResponse2.setVehicleYearNumber(2007);
        this.vehicleResponse2.setVehicleMake("HONDA");
        this.vehicleResponse2.setVehicleModel("CIVIC");
        this.vehicleResponse2.setVehicleEngine("4-1799 1.8L SOHC");
        this.vehicleResponse2.setVinNumber("");
        this.vehicleResponse2.setVinLast6Digits("");
        this.vehicleResponse2.setCatalogId(31757);
        this.vehicleResponse2.setLicensePlateNumber("");
        this.vehicleResponse2.setLastUpdateUserId("2011-06-02 13:57:47.0");
        this.vehicleResponse2.setVehicleSts("");

        this.vehicleResponseList = new ArrayList<VehicleResponse>();
        this.vehicleResponseList.add(vehicleResponse1);
        this.vehicleResponseList.add(vehicleResponse2);
    }

    @Test
    public void convertToVehicleTest() {
        List<FamilyVehicle> result = new ArrayList<FamilyVehicle>();
        Mapper mapper = new DozerBeanMapper();
        for (VehicleResponse vehicleResponse : vehicleResponseList) {
            result.add(mapper.map(vehicleResponse, FamilyVehicle.class));
        }
        Assert.assertTrue(result.size() == 2);
    }
}
