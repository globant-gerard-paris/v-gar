package com.searshc.mygarage.apis.ncdb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import scala.collection.mutable.StringBuilder;

import com.searshc.mygarage.apis.ncdb.response.EsbMsgRequest;
import com.searshc.mygarage.apis.ncdb.response.MdsHeader;
import com.searshc.mygarage.apis.ncdb.response.Query;
import com.searshc.mygarage.apis.ncdb.response.order.OrderHistoryResponse;
import com.searshc.mygarage.apis.ncdb.response.vehicle.VehicleResponse;
import com.searshc.mygarage.apis.ncdb.response.vehicle.VehicleRetrievalResponse;
import com.searshc.mygarage.apis.ncdb.response.vintovehicle.VehicleByLicensePlateOrVinNumberResponse;
import com.searshc.mygarage.apis.ncdb.response.vintovehicle.VehiclesListByLicensePlateOrVinNumberResponse;
import com.searshc.mygarage.dtos.VehicleGenericDescriptionDTO;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

@Component
public class NCDBApiImpl implements NCDBApi {

    private static final Log log = LogFactory.getLog(NCDBApiImpl.class);

    @Value("${ncdb.api.vehicle.retrieval.service.name}")
    private String vehicleRetrievalServiceName;

    @Value("${ncdb.api.order.history.inquiry.service.name}")
    private String orderHistoryInquiryServiceName;

    @Value("${ncdb.api.vehicle.list.inquiry.vinnumber.service.name}")
    private String vehicleListInquiryByVINNumberServiceName;

    @Value("${ncdb.api.endpoint}")
    private String serviceUrl;

    private final String requestorUserId = "06091";

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyyMMddHHmmssSSS");

    private final RestTemplate restTemplate = new RestTemplate();

    private final Mapper mapper = new DozerBeanMapper();

    @Override
    public OrderHistoryResponse getVehicleHistory(Long familyId, Long tangibleId) {

        MdsHeader header = new MdsHeader(this.orderHistoryInquiryServiceName);
        header.setRequestorUserId(this.requestorUserId);
        header.setMessageOriginationTime(this.simpleDateFormat.format(new Date()));
        header.setSequenceNumber("001");

        EsbMsgRequest request = new EsbMsgRequest(header, new Query(familyId, tangibleId));

        OrderHistoryResponse response = null;

        try {

            log.info(String.format("Looking NCDB orders for familyId: %s and tangibleId: %s at %s",
                    familyId, tangibleId, this.serviceUrl));

            response = this.restTemplate.postForObject(this.serviceUrl,
                    request, OrderHistoryResponse.class);

            log.info(String.format("%s orders were found", response.getOrdersHeader().size()));

        } catch (Exception ex) {

            String message = new StringBuilder()
                    .append("Could not get Transactions from NCDB for familyIdNumber: ")
                    .append(familyId)
                    .append(" and tangibleIdNumber: ")
                    .append(tangibleId).toString();

            log.error(message, ex);
        }

        return response;

    }

    @Override
    public List<FamilyVehicle> getVehicles(Long familyIdNumber) {
        VehicleRetrievalResponse vehicleRetrievalResponse = this.getNCDBVehicles(familyIdNumber);
        return vehicleRetrievalResponse != null
                ? this.convert(vehicleRetrievalResponse.getVehicles()) : new ArrayList<FamilyVehicle>();

    }

    private List<FamilyVehicle> convert(final List<VehicleResponse> vehicleResponseList) {
        Validate.noNullElements(vehicleResponseList, "The VehicleResponse list cannot be null");
        List<FamilyVehicle> result = new ArrayList<FamilyVehicle>();
        for (VehicleResponse vehicle
                : vehicleResponseList) {
            try {
                result.add(this.mapper.map(vehicle, FamilyVehicle.class));
            } catch (MappingException e) {
                log.error("Could convert VehicleResponse to Vehicle object. TangibleId: " + vehicle.getTangibleIdNumber(), e);
            }
        }
        return result;
    }

    private VehicleRetrievalResponse getNCDBVehicles(final Long familyIdNumber) {
        MdsHeader header = new MdsHeader(this.vehicleRetrievalServiceName);
        header.setRequestorUserId(this.requestorUserId);
        header.setMessageOriginationTime(this.simpleDateFormat.format(new Date()));
        header.setSequenceNumber("001");

        EsbMsgRequest request = new EsbMsgRequest(header, new Query(familyIdNumber));

        VehicleRetrievalResponse response = null;

        try {
            log.info("Looking NCDB vehicles for familyId " + familyIdNumber + " at " + serviceUrl);

            response = this.restTemplate.postForObject(this.serviceUrl,
                    request, VehicleRetrievalResponse.class);

            log.info(response.getVehicles().size() + " vehicles were found");

        } catch (Exception e) {
            String message = new StringBuilder()
                    .append("Could not get Vehicles from NCDB for familyIdNumber: ")
                    .append(familyIdNumber).toString();
            log.error(message, e);
        }

        return response;
    }

    @Override
    public List<VehicleGenericDescriptionDTO> getVehicleByVINNumber(final String vinNumber) {
        VehiclesListByLicensePlateOrVinNumberResponse ncdbVehiclesList = this.getNCDBVehicleByVINNumber(vinNumber);
        List<VehicleGenericDescriptionDTO> results = this.convertToVehicleByVINOrLicensePlateDTO(ncdbVehiclesList.getVehicles());
        return results;
    }

    @Override
    public List<VehicleGenericDescriptionDTO> getVehicleByLicensePlate(final String licensePlate) {
        VehiclesListByLicensePlateOrVinNumberResponse ncdbVehiclesList = this.getNCDBVehicleByLicensePlate(licensePlate);
        List<VehicleGenericDescriptionDTO> results = this.convertToVehicleByVINOrLicensePlateDTO(ncdbVehiclesList.getVehicles());
        return results;
    }

    public VehiclesListByLicensePlateOrVinNumberResponse getNCDBVehicleByVINNumber(final String vinNumber) {
        Validate.isTrue(!StringUtils.isEmpty(vinNumber), "The VIN Number is required");
        MdsHeader header = new MdsHeader(this.vehicleListInquiryByVINNumberServiceName);
        header.setRequestorUserId(this.requestorUserId);
        header.setMessageOriginationTime(this.simpleDateFormat.format(new Date()));
        header.setSequenceNumber("001");

        Query query = new Query();
        query.setFamilyTypeCode("R");
        query.setTangibleTypeCode("R");
        query.setVinNumber(vinNumber);

        EsbMsgRequest request = new EsbMsgRequest(header, query);
        VehiclesListByLicensePlateOrVinNumberResponse response = null;
        log.info(new StringBuilder().append("Looking NCDB vehicles by VIN number ").append(vinNumber).append(" at ").append(serviceUrl).toString());
        try {
            //TODO: use the real service
            //response = this.restTemplate.postForObject(this.serviceUrl, request, VehiclesListVINResponse.class);
            response = this.restTemplate.postForObject("http://localhost:8080/ncdbmock/vinnumber", request, VehiclesListByLicensePlateOrVinNumberResponse.class);
        } catch (Exception e) {
            String message = new StringBuilder().append("Could not get Vehicles by VIN Number: ").append(vinNumber).toString();
            log.error(message, e);
            throw new NCDBApiException(message);
        }
        log.info(response.getVehicleCount() + " vehicles were found");
        return response;
    }

    public VehiclesListByLicensePlateOrVinNumberResponse getNCDBVehicleByLicensePlate(final String licensePlate) {
        Validate.isTrue(!StringUtils.isEmpty(licensePlate), "The License Plate is required");
        MdsHeader header = new MdsHeader(this.vehicleListInquiryByVINNumberServiceName);
        header.setRequestorUserId(this.requestorUserId);
        header.setMessageOriginationTime(this.simpleDateFormat.format(new Date()));
        header.setSequenceNumber("001");

        Query query = new Query();
        query.setFamilyTypeCode("R");
        query.setTangibleTypeCode("R");
        query.setLicensePlateNumber(licensePlate);

        EsbMsgRequest request = new EsbMsgRequest(header, query);
        VehiclesListByLicensePlateOrVinNumberResponse response = null;
        log.info(new StringBuilder().append("Looking NCDB vehicles by Lincense Plate number ").append(licensePlate).append(" at ").append(serviceUrl).toString());
        try {
            //TODO: use the real service
            //response = this.restTemplate.postForObject(this.serviceUrl, request, VehiclesListVINResponse.class);
            response = this.restTemplate.postForObject("http://localhost:8080/ncdbmock/vinnumber", request, VehiclesListByLicensePlateOrVinNumberResponse.class);
        } catch (Exception e) {
            String message = new StringBuilder().append("Could not get Vehicles by License Plate Number: ").append(licensePlate).toString();
            log.error(message, e);
            throw new NCDBApiException(message);
        }
        log.info(response.getVehicleCount() + " vehicles were found");
        return response;
    }

    public List<VehicleGenericDescriptionDTO> convertToVehicleByVINOrLicensePlateDTO(List<VehicleByLicensePlateOrVinNumberResponse> vehicleVINResponseList) {
        Validate.notNull(vehicleVINResponseList, "The List of Vehicles cannot be null");
        log.debug("Items to be converted: " + vehicleVINResponseList.size());
        List<VehicleGenericDescriptionDTO> result = new ArrayList<VehicleGenericDescriptionDTO>();
        for (VehicleByLicensePlateOrVinNumberResponse ncdbVehicle : vehicleVINResponseList) {
            try {
                result.add(this.mapper.map(ncdbVehicle, VehicleGenericDescriptionDTO.class));
            } catch (MappingException e) {
                log.error("Could not convert to VehicleByVINOrLicensePlateDTO", e);
            }
        }
        log.debug("Items converted: " + result.size());
        return result;

    }
}
