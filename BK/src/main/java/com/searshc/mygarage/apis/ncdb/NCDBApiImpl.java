package com.searshc.mygarage.apis.ncdb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.searshc.mygarage.entities.UserVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

@Component
public class NCDBApiImpl implements NCDBApi {

    private static final Log log = LogFactory.getLog(NCDBApiImpl.class);

    @Value("${ncdb.api.vehicle.retrieval.service.name}")
    private String vehicleRetrievalServiceName;

    @Value("${ncdb.api.order.history.inquiry.service.name}")
    private String orderHistoryInquiryServiceName;

    @Value("${ncdb.api.endpoint}")
    private String serviceUrl;

    private final String requestorUserId = "06091";

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyyMMddHHmmssSSS");

    private final RestTemplate restTemplate = new RestTemplate();

    private final Mapper mapper = new DozerBeanMapper();

    @Override
    public OrderHistoryResponse getVehicleHistory(Long familyId, Long tangibleId) throws NCDBApiException {

        MdsHeader header = new MdsHeader(this.orderHistoryInquiryServiceName);
        header.setRequestorUserId(this.requestorUserId);
        header.setMessageOriginationTime(this.simpleDateFormat.format(new Date()));
        header.setSequenceNumber("001");

        EsbMsgRequest request = new EsbMsgRequest(header, new Query(familyId, tangibleId));

        OrderHistoryResponse response = null;

        try {

            response = this.restTemplate.postForObject(this.serviceUrl,
                    request, OrderHistoryResponse.class);

        } catch (Exception ex) {

            String message = new StringBuilder()
                    .append("Could not get Transactions from NCDB for familyIdNumber: ")
                    .append(familyId)
                    .append(" and tangibleIdNumber: ")
                    .append(tangibleId).toString();

            log.error(message, ex);

            throw new NCDBApiException(message);
        }

        return response;

    }

    @Override
    public List<UserVehicle> getVehicles(Long familyIdNumber) throws NCDBApiException {
        VehicleRetrievalResponse vehicleRetrievalResponse = this.getNCDBVehicles(familyIdNumber);
        return vehicleRetrievalResponse != null
                ? this.convert(vehicleRetrievalResponse.getVehicles()) : new ArrayList<UserVehicle>();

    }

    public List<UserVehicle> convert(final List<VehicleResponse> vehicleResponseList) {
        Validate.noNullElements(vehicleResponseList, "The VehicleResponse list cannot be null");
        List<UserVehicle> result = new ArrayList<UserVehicle>();
        for (VehicleResponse vehicle : vehicleResponseList) {
            try {
                result.add(this.mapper.map(vehicle, UserVehicle.class));
            } catch (MappingException e) {
                log.error("Could convert VehicleResponse to Vehicle object. TangibleId: " + vehicle.getTangibleIdNumber(), e);
            }
        }
        return result;
    }

    public VehicleRetrievalResponse getNCDBVehicles(final Long familyIdNumber) throws NCDBApiException {
        MdsHeader header = new MdsHeader(this.vehicleRetrievalServiceName);

        header.setRequestorUserId(this.requestorUserId);
        header.setMessageOriginationTime(this.simpleDateFormat.format(new Date()));
        header.setSequenceNumber("001");

        EsbMsgRequest request = new EsbMsgRequest(header, new Query(familyIdNumber));

        VehicleRetrievalResponse response = null;
        log.info("Looking NCDB vehicles for familyId " + familyIdNumber + " at " + serviceUrl);
        try {
            response = this.restTemplate.postForObject(this.serviceUrl,
                    request, VehicleRetrievalResponse.class);
        } catch (Exception e) {
            String message = new StringBuilder()
                    .append("Could not get Vehicles from NCDB for familyIdNumber: ")
                    .append(familyIdNumber).toString();
            log.error(message, e);
            throw new NCDBApiException(message);
        }
        log.info(response.getVehicles().size() + " vehicles were found");
        return response;
    }
}
