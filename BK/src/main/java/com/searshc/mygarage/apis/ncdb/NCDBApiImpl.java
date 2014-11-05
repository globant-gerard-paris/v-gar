package com.searshc.mygarage.apis.ncdb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.OrderItem;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.apis.ncdb.response.EsbMsgRequest;
import com.searshc.mygarage.apis.ncdb.response.MdsHeader;
import com.searshc.mygarage.apis.ncdb.response.Query;
import com.searshc.mygarage.apis.ncdb.response.order.OrderHeaderResponse;
import com.searshc.mygarage.apis.ncdb.response.order.OrderHistoryResponse;
import com.searshc.mygarage.apis.ncdb.response.order.OrderItemResponse;
import com.searshc.mygarage.apis.ncdb.response.vehicle.VehicleResponse;
import com.searshc.mygarage.apis.ncdb.response.vehicle.VehicleRetrievalResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.web.client.RestTemplate;

@Component
public class NCDBApiImpl implements NCDBApi {

    private final String VEHICLE_RETRIEVAL_SERVICE_NAME = "RTVEH";

    private final String ORDER_HISTORY_INQUIRY_SERVICE_NAME = "ROFID";

    private String serviceUrl = "http://10.129.217.205:1181/ncdb/HttpListener";

    RestTemplate restTemplate = new RestTemplate();

    private Map<String, Order> createOrdersMap(List<OrderHeaderResponse> ordersHeader) {
        Map<String, Order> ordersMap = new HashMap<String, Order>();

        Mapper mapper = new DozerBeanMapper();

        for (OrderHeaderResponse orderHeader : ordersHeader) {
            Order order = mapper.map(orderHeader, Order.class);
            ordersMap.put(order.getOrderNumber(), order);
        }

        return ordersMap;
    }

    @Override
    public List<Order> getCarTransactionHistory(Integer familyIdNumber, Integer tangibleId) {

        MdsHeader header = new MdsHeader(ORDER_HISTORY_INQUIRY_SERVICE_NAME);
        header.setRequestorUserId("06091");
        header.setMessageOriginationTime(new SimpleDateFormat("yyyyyMMddHHmmssSSS").format(new Date()));
        header.setSequenceNumber("001");

        Query query = new Query(familyIdNumber);
        query.setTangibleIdNumber(tangibleId);

        EsbMsgRequest request = new EsbMsgRequest(header, query);

        OrderHistoryResponse response = null;

        try {
            response = this.restTemplate.postForObject(this.serviceUrl,
                    request, OrderHistoryResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<Order> orders = new ArrayList<Order>();

        if (response != null) {

            Map<String, Order> ordersMap = createOrdersMap(response.getOrdersHeader());

            Mapper mapper = new DozerBeanMapper();

            for (OrderItemResponse orderItem : response.getOrderItems()) {
                if (ordersMap.containsKey(orderItem.getOrderNumber())) {
                    Order order = ordersMap.get(orderItem.getOrderNumber());
                    order.addOrderItems(mapper.map(orderItem, OrderItem.class));
                }
            }

            orders = new ArrayList<Order>(ordersMap.values());

        }

        return orders;
    }

    @Override
    public List<Vehicle> getVehicles(Integer familyIdNumber) {

        MdsHeader header = new MdsHeader(VEHICLE_RETRIEVAL_SERVICE_NAME);
        header.setRequestorUserId("06091");
        header.setMessageOriginationTime(new SimpleDateFormat("yyyyyMMddHHmmssSSS").format(new Date()));
        header.setSequenceNumber("001");

        Query query = new Query(familyIdNumber);

        EsbMsgRequest request = new EsbMsgRequest(header, query);

        VehicleRetrievalResponse response = null;

        try {
            response = this.restTemplate.postForObject(this.serviceUrl,
                    request, VehicleRetrievalResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<Vehicle> vehicles = new ArrayList<Vehicle>();

        if (response != null) {

            Mapper mapper = new DozerBeanMapper();

            for (VehicleResponse vehicle : response.getVehicles()) {
                vehicles.add(mapper.map(vehicle, Vehicle.class));
            }

        }

        return vehicles;

    }
}
