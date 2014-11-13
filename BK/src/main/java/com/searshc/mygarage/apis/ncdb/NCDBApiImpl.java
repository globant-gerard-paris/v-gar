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
import com.searshc.mygarage.entities.Vehicle;
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

    private RestTemplate restTemplate = new RestTemplate();

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

        MdsHeader header = new MdsHeader(orderHistoryInquiryServiceName);
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
    public List<Vehicle> getVehicles(Integer familyIdNumber) throws NCDBApiException {
    	VehicleRetrievalResponse vehicleRetrievalResponse = this.getNCDBVehicles(familyIdNumber);
    	return vehicleRetrievalResponse != null ?
    			this.convert(vehicleRetrievalResponse.getVehicles()) : new ArrayList<Vehicle>();

    }
    
    public List<Vehicle> convert(final List<VehicleResponse> vehicleResponseList) {
    	Validate.noNullElements(vehicleResponseList, "The VehicleResponse list cannot be null");
    	List<Vehicle> result = new ArrayList<Vehicle>();
    	Mapper mapper = new DozerBeanMapper();
    	for (VehicleResponse vehicle : vehicleResponseList) {
    		try {
				result.add(mapper.map(vehicle, Vehicle.class));
			}
			catch (MappingException e) {
				log.error("Could convert VehicleResponse to Vehicle object. TangibleId: " + vehicle.getTangibleIdNumber(), e);
			}
    	}
    	return result;
    }
    
    public VehicleRetrievalResponse getNCDBVehicles(final Integer familyIdNumber) throws NCDBApiException {
    	 MdsHeader header = new MdsHeader(vehicleRetrievalServiceName);
         header.setRequestorUserId("06091");
         header.setMessageOriginationTime(new SimpleDateFormat("yyyyyMMddHHmmssSSS").format(new Date()));
         header.setSequenceNumber("001");

         Query query = new Query(familyIdNumber);

         EsbMsgRequest request = new EsbMsgRequest(header, query);

         VehicleRetrievalResponse response = null;

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
         return response;
    }
}
