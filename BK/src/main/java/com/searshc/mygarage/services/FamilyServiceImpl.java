package com.searshc.mygarage.services;

import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.entities.response.VehicleResponse;
import com.searshc.mygarage.entities.response.VehicleRetrievalResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Override
    public List<Vehicle> listVehicle(Integer familyId) {
        RestTemplate template = new RestTemplate();

        String xml = "<ESBMsg>\n"
                + "    <MDSHeader>\n"
                + "        <HeaderType>HDR-MDSREQ</HeaderType>\n"
                + "        <VersionNumber>001</VersionNumber>\n"
                + "        <ServiceName>RTVEH</ServiceName>\n"
                + "        <RequestorUserId>06091</RequestorUserId>\n"
                + "        <MessageOriginationTime>20130430155643426</MessageOriginationTime>\n"
                + "        <SequenceNumber>001</SequenceNumber>\n"
                + "    </MDSHeader>\n"
                + "    <Query>\n"
                + "        <fam_id_no>90915322</fam_id_no>\n"
                + "        <fam_typ_cd>R909153</fam_typ_cd>\n"
                + "    </Query>\n"
                + "</ESBMsg>";

        VehicleRetrievalResponse response = null;
        String strResponse = null;

        try {
            response = template.postForObject("http://10.129.217.205:1181/ncdb/HttpListener",
                    xml, VehicleRetrievalResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<Vehicle> vehicles = new ArrayList<Vehicle>();

        if (response != null) {
            
            for (VehicleResponse vehicle : response.getData().getVehicles()) {
                 Vehicle v = new Vehicle();
                 v.setVehicleMake(vehicle.getVehicleMake());
                 v.setVehicleModel(vehicle.getVehicleModel());    
                 vehicles.add(v);
            }            
            
        } else {

            Vehicle v1 = new Vehicle();
            v1.setVehicleMake("Honda");
            v1.setVehicleModel("Z23");
            v1.setFamilyTypeCode(strResponse);
            
            vehicles.add(v1);

//            Vehicle v2 = new Vehicle();
//            v2.setVehicleMake("Audi");
//            v2.setVehicleModel("M5");
//
//            
//            vehicles.add(v2);
        }

        return vehicles;
    }

}
