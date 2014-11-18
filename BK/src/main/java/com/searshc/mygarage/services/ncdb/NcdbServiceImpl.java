package com.searshc.mygarage.services.ncdb;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scala.collection.mutable.StringBuilder;

import com.searshc.mygarage.apis.ncdb.NCDBApi;
import com.searshc.mygarage.apis.ncdb.response.order.OrderHeaderResponse;
import com.searshc.mygarage.apis.ncdb.response.order.OrderHistoryResponse;
import com.searshc.mygarage.apis.ncdb.response.order.OrderItemResponse;
import com.searshc.mygarage.dtos.NcdbServiceRecord;
import com.searshc.mygarage.dtos.RecommendedService;
import com.searshc.mygarage.dtos.ServiceCenter;
import com.searshc.mygarage.dtos.ServiceRecord;
import com.searshc.mygarage.dtos.ServiceRecordItem;
import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.OrderItem;
import com.searshc.mygarage.entities.ServiceTranslation;
import com.searshc.mygarage.entities.Store;
import com.searshc.mygarage.entities.SuggestedTranslation;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.repositories.ServiceTranslationRepository;
import com.searshc.mygarage.repositories.StoreRepository;
import com.searshc.mygarage.repositories.SuggestedTranslationRepository;
import com.searshc.mygarage.util.ServiceRecordType;
import com.searshc.mygarage.util.VGUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

@Service
public class NcdbServiceImpl implements NcdbService {

	private static final Log log = LogFactory.getLog(NcdbServiceImpl.class);
	
    private NCDBApi ncdbApi;

    private StoreRepository storeRepository;

    private ServiceTranslationRepository serviceTranslationRepository;

    private SuggestedTranslationRepository suggestedTranslationRepository;

    private final Mapper mapper = new DozerBeanMapper();

    @Autowired
    public NcdbServiceImpl(NCDBApi ncdbApi, StoreRepository storeRepository,
            ServiceTranslationRepository serviceTranslationRepository,
            SuggestedTranslationRepository suggestedTranslationRepository) {
        this.ncdbApi = ncdbApi;
        this.storeRepository = storeRepository;
        this.serviceTranslationRepository = serviceTranslationRepository;
        this.suggestedTranslationRepository = suggestedTranslationRepository;
    }

    @Override
    public List<FamilyVehicle> listVehicles(Long familyId) throws NCDBApiException {
        return this.ncdbApi.getVehicles(familyId);
    }

    private Map<String, Order> createOrdersMap(List<OrderHeaderResponse> ordersHeader) {

        Map<String, Order> ordersMap = new HashMap<String, Order>();

        for (OrderHeaderResponse orderHeader : ordersHeader) {
            Order order = this.mapper.map(orderHeader, Order.class);
            order.setTransactionDateTime(VGUtils.
                    createDateTime(orderHeader.getTransactionDate(),
                            orderHeader.getTransactionLocalTime()));
            ordersMap.put(order.getOrderNumber(), order);
        }

        return ordersMap;
    }

    private OrderItem createOrderItem(OrderItemResponse orderItemResponse) {
        OrderItem item = this.mapper.map(orderItemResponse, OrderItem.class);
        item.setType(ServiceRecordType.UNKNOWN);
        SuggestedTranslation sgt
                = this.suggestedTranslationRepository.findBySku(item.getItemId());
        if (sgt != null) {
            item.setItemDescription(sgt.getDescription());
            item.setType(ServiceRecordType.RECOMMENDED_SERVICE);
        } else {
            ServiceTranslation st
                    = this.serviceTranslationRepository.findByProductFlag(item.getProductFlag());
            if (st != null) {
                item.setItemDescription(st.getDescription());
                item.setType(ServiceRecordType.REAL_SERVICE);
            }
        }
        return item;
    }

    private ServiceRecordItem createServiceRecordItem(OrderItem orderItem) {
        ServiceRecordItem sri = new ServiceRecordItem();
        sri.setDescription(orderItem.getItemDescription());
        return sri;
    }

    private ServiceRecord createServiceRecord(Order order, OrderItem orderItem) {

        ServiceCenter serviceCenter = null;
        if (order.getStoreNumber() != null) {
            Store store = this.storeRepository.findBySacStore(order.getStoreNumber().toString());
            if (store != null) {
                serviceCenter = this.mapper.map(store, ServiceCenter.class);
            }
        }

        ServiceRecord serviceRecord = null;

        if (orderItem.getType().equals(ServiceRecordType.RECOMMENDED_SERVICE)) {
            serviceRecord = new RecommendedService(true, order, serviceCenter);
        } else if (orderItem.getType().equals(ServiceRecordType.REAL_SERVICE)) {
            serviceRecord = new NcdbServiceRecord(order, serviceCenter);
        }

        if (serviceRecord != null) {
            serviceRecord.addServiceRecordItem(this.createServiceRecordItem(orderItem));
        }

        return serviceRecord;
    }

    private List<ServiceRecord> processTransactions(Long familyId, Long tangibleId, ServiceRecordType filter)
            throws NCDBApiException {

        OrderHistoryResponse orderHistoryResponse = this.ncdbApi.getVehiculeHistory(familyId, tangibleId);

        List<ServiceRecord> serviceRecords = new ArrayList<ServiceRecord>();

        if (orderHistoryResponse != null) {

            Map<String, Order> ordersMap = this.createOrdersMap(orderHistoryResponse.getOrdersHeader());
            Map<String, ServiceRecord> serviceRecordsMap = new HashMap<String, ServiceRecord>();

            for (OrderItemResponse orderItem : orderHistoryResponse.getOrderItems()) {
                if (ordersMap.containsKey(orderItem.getOrderNumber())) {
                    Order order = ordersMap.get(orderItem.getOrderNumber());
                    OrderItem item = this.createOrderItem(orderItem);
                    if (item.getType().equals(filter)) {
                        if (serviceRecordsMap.containsKey(order.getOrderNumber())) {
                            serviceRecordsMap.get(order.getOrderNumber())
                                    .addServiceRecordItem(this.createServiceRecordItem(item));
                        } else {
                            ServiceRecord sr = this.createServiceRecord(order, item);
                            if (sr != null) {
                                serviceRecordsMap.put(order.getOrderNumber(), sr);
                            }
                        }
                    }
                    //Restore original description
                    item.setItemDescription(orderItem.getItemDescription());
                    order.addOrderItems(item);
                }
            }
            serviceRecords = new ArrayList<ServiceRecord>(serviceRecordsMap.values());
        }

        Collections.sort(serviceRecords, new Comparator<ServiceRecord>() {
            @Override
            public int compare(ServiceRecord o1, ServiceRecord o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        return serviceRecords;
    }

    @Override
    public List<ServiceRecord> getServiceRecords(Long familyId, Long tangibleId)
            throws NCDBApiException {

        return this.processTransactions(familyId, tangibleId, ServiceRecordType.REAL_SERVICE);

    }

    @Override
    public RecommendedService getRecommendedServices(Long familyId, Long tangibleId)
            throws NCDBApiException {

        List<ServiceRecord> records = this.processTransactions(familyId, tangibleId,
                ServiceRecordType.RECOMMENDED_SERVICE);

        return records.size() > 0 ? (RecommendedService) records.get(0) : null;

    }
    
    @Override
    public int getHighestMileage(final Long familyId, final Long tangibleId) throws NCDBApiException {
    	List<Order> orders;

    	orders = this.getTransactions(familyId, tangibleId);

    	int highestMileage = -1;
    	if(orders != null && orders.size() > 0) {
    		for(Order order : orders) {
        		if(order.getOdometerAmount().intValue() > highestMileage) {
        			highestMileage = order.getOdometerAmount().intValue();
        		}
        	}
    	}
    	
    	return highestMileage;
    }
    
    public int getLastUsedStoreId(final Long familyId, final Long tangibleId) throws NCDBApiException {
    	List<Order> orders = this.getTransactions(familyId, tangibleId);
    	if(orders == null || orders.size() == 0) {
    		String msg = new StringBuilder()
    			.append("Could not determine the last store. There are no transactions available for family: ")
    			.append(familyId)
    			.append(" Tangible: ")
    			.append(tangibleId).toString();
    		log.warn(msg);
    		return -1;
    	}
    	Order lastOrder = null;
    	for(Order order : orders) {
    		if(lastOrder == null || order.getTransactionDateTime().after(lastOrder.getTransactionDateTime())) {
    			lastOrder = order;
    		}
    	}
    	return lastOrder!= null ? lastOrder.getStoreNumber() : -1;
    }

}
