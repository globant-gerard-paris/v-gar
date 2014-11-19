package com.searshc.mygarage.apis.ncdb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.searshc.mygarage.apis.ncdb.response.order.OrderHeaderResponse;
import com.searshc.mygarage.apis.ncdb.response.order.OrderHistoryResponse;
import com.searshc.mygarage.apis.ncdb.response.order.OrderItemResponse;
import com.searshc.mygarage.apis.ncdb.response.vehicle.VehicleResponse;
import com.searshc.mygarage.apis.ncdb.response.vehicle.VehicleRetrievalResponse;
import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.OrderItem;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

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

    private Date createDateTime(Date date, Date time) {
        Calendar dateTime = Calendar.getInstance();
        dateTime.setTime(date);
        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTime(time);
        dateTime.add(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
        dateTime.add(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
        dateTime.add(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));
        return dateTime.getTime();
    }

    private Map<String, Order> createOrdersMap(List<OrderHeaderResponse> ordersHeader) {

        Map<String, Order> ordersMap = new HashMap<String, Order>();

        for (OrderHeaderResponse orderHeader : ordersHeader) {
            Order order = this.mapper.map(orderHeader, Order.class);
            order.setTransactionDateTime(this.createDateTime(orderHeader.getTransactionDate(), orderHeader.getTransactionLocalTime()));
            ordersMap.put(order.getOrderNumber(), order);
        }

        return ordersMap;
    }

    @Override
    public List<Order> getCarTransactionHistory(Long familyId, Long tangibleId) throws NCDBApiException {

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

        List<Order> orders = new ArrayList<Order>();

        if (response != null) {

            Map<String, Order> ordersMap = createOrdersMap(response.getOrdersHeader());

            for (OrderItemResponse orderItem : response.getOrderItems()) {
                if (ordersMap.containsKey(orderItem.getOrderNumber())) {
                    Order order = ordersMap.get(orderItem.getOrderNumber());
                    order.addOrderItems(this.mapper.map(orderItem, OrderItem.class));
                }
            }

            orders = new ArrayList<Order>(ordersMap.values());

            Collections.sort(orders, new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o2.getTransactionDateTime().compareTo(o1.getTransactionDateTime());
                }
            });

        }

        return orders;
    }

    @Override
    public List<FamilyVehicle> getVehicles(Long familyIdNumber) throws NCDBApiException {
        VehicleRetrievalResponse vehicleRetrievalResponse = this.getNCDBVehicles(familyIdNumber);
        return vehicleRetrievalResponse != null
                ? this.convert(vehicleRetrievalResponse.getVehicles()) : new ArrayList<FamilyVehicle>();

    }

    public List<FamilyVehicle> convert(final List<VehicleResponse> vehicleResponseList) {
        Validate.noNullElements(vehicleResponseList, "The VehicleResponse list cannot be null");
        List<FamilyVehicle> result = new ArrayList<FamilyVehicle>();
        for (VehicleResponse vehicle : vehicleResponseList) {
            try {
                result.add(this.mapper.map(vehicle, FamilyVehicle.class));
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
