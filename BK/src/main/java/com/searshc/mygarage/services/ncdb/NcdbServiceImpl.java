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
import com.searshc.mygarage.entities.record.NcdbServiceRecord;
import com.searshc.mygarage.entities.record.RecommendedService;
import com.searshc.mygarage.entities.record.ServiceCenter;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.entities.record.ServiceRecordItem;
import com.searshc.mygarage.entities.record.Order;
import com.searshc.mygarage.entities.record.OrderItem;
import com.searshc.mygarage.entities.record.ServiceTranslation;
import com.searshc.mygarage.entities.Store;
import com.searshc.mygarage.entities.record.SuggestedTranslation;

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

    private ServiceCenter getServiceCenter(Integer storeNumber) {
        ServiceCenter serviceCenter = null;
        if (storeNumber != null) {
            Store store = this.storeRepository.findBySacStore(storeNumber.toString());
            if (store != null) {
                serviceCenter = this.mapper.map(store, ServiceCenter.class);
            }
        }
        return serviceCenter;
    }

    private Map<String, Order> createOrdersMap(List<OrderHeaderResponse> ordersHeader) {
        Map<String, Order> ordersMap = new HashMap<String, Order>();
        for (OrderHeaderResponse orderHeader : ordersHeader) {
            Order order = this.mapper.map(orderHeader, Order.class);
            order.setTransactionDateTime(VGUtils.
                    createDateTime(orderHeader.getTransactionDate(),
                            orderHeader.getTransactionLocalTime()));
            order.setServiceCenter(this.getServiceCenter(order.getStoreNumber()));
            ordersMap.put(order.getOrderNumber(), order);
        }
        return ordersMap;
    }

    private List<Order> processTransactions(Long familyId, Long tangibleId)
            throws NCDBApiException {
        return this.processTransactions(familyId, tangibleId, null);
    }

    private Order getLastOrder(Long familyId, Long tangibleId)
            throws NCDBApiException {
        List<Order> orders = this.processTransactions(familyId, tangibleId);
        return orders.isEmpty() ? null : orders.get(0);
    }

    private List<Order> processTransactions(Long familyId, Long tangibleId, RecordFilter recordFilter)
            throws NCDBApiException {
        OrderHistoryResponse orderHistoryResponse = this.ncdbApi.getVehicleHistory(familyId, tangibleId);
        List<Order> orders = null;
        if (orderHistoryResponse != null) {
            Map<String, Order> ordersMap = this.createOrdersMap(orderHistoryResponse.getOrdersHeader());
            for (OrderItemResponse orderItemResponse : orderHistoryResponse.getOrderItems()) {
                if (ordersMap.containsKey(orderItemResponse.getOrderNumber())) {
                    Order order = ordersMap.get(orderItemResponse.getOrderNumber());
                    OrderItem orderItem = this.mapper.map(orderItemResponse, OrderItem.class);
                    orderItem.setType(ServiceRecordType.UNKNOWN);
                    order.addOrderItems(orderItem);
                    if (recordFilter != null) {
                        recordFilter.filter(order, orderItem);
                    }
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
        return orders != null ? orders : new ArrayList<Order>();
    }

    @Override
    public List<ServiceRecord> getServiceRecords(Long familyId, Long tangibleId)
            throws NCDBApiException {
        RecordFilter ncdbRecordFilter = new RecordFilter();
        this.processTransactions(familyId, tangibleId, ncdbRecordFilter);
        List<ServiceRecord> serviceRecords
                = new ArrayList<ServiceRecord>(ncdbRecordFilter.serviceRecordsMap.values());
        Collections.sort(serviceRecords, new Comparator<ServiceRecord>() {
            @Override
            public int compare(ServiceRecord o1, ServiceRecord o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
        return serviceRecords;
    }

    @Override
    public RecommendedService getRecommendedServices(Long familyId, Long tangibleId)
            throws NCDBApiException {
        RecommendedService recommendedService = null;
        Order lastOrder = this.getLastOrder(familyId, tangibleId);
        if (lastOrder != null) {
            recommendedService = new RecommendedService(true, lastOrder, lastOrder.getServiceCenter());
            for (OrderItem item : lastOrder.getOrderItems()) {
                ServiceRecordItem sri = this.createServiceRecordItem(item);
                if (sri != null) {
                    recommendedService.addServiceRecordItem(sri);
                }
            }
        }
        return recommendedService != null
                && !recommendedService.getServiceRecordItems().isEmpty()
                        ? recommendedService : null;
    }

    private ServiceRecordItem createServiceRecordItem(OrderItem item) {
        SuggestedTranslation sgt
                = this.suggestedTranslationRepository.findBySku(item.getItemId());
        ServiceRecordItem sri = null;
        if (sgt != null) {
            item.setType(ServiceRecordType.RECOMMENDED_SERVICE);
            sri = mapper.map(sgt, ServiceRecordItem.class);
            sri.setCode(item.getItemId());
        }
        return sri;
    }

    private class RecordFilter {

        Map<String, ServiceRecord> serviceRecordsMap = new HashMap<String, ServiceRecord>();

        public void filter(Order order, OrderItem orderItem) {
            ServiceTranslation st
                    = serviceTranslationRepository.findByProductFlag(orderItem.getProductFlag());
            if (st != null) {
                orderItem.setType(ServiceRecordType.REAL_SERVICE);
                ServiceRecordItem sri = this.createServiceRecordItem(orderItem, st);
                if (serviceRecordsMap.containsKey(order.getOrderNumber())) {
                    serviceRecordsMap.get(order.getOrderNumber())
                            .addServiceRecordItem(sri);
                } else {
                    serviceRecordsMap.put(order.getOrderNumber(),
                            this.createServiceRecord(order, sri));
                }
            }
        }

        private NcdbServiceRecord createServiceRecord(Order order, ServiceRecordItem sri) {
            NcdbServiceRecord nsr = new NcdbServiceRecord(order, order.getServiceCenter());
            nsr.addServiceRecordItem(sri);
            return nsr;
        }

        private ServiceRecordItem createServiceRecordItem(OrderItem orderItem, ServiceTranslation st) {
            ServiceRecordItem sri = mapper.map(st, ServiceRecordItem.class);
            sri.setCode(String.valueOf(orderItem.getProductFlag()));
            return sri;
        }

    }

    @Override
    public int getHighestMileage(final Long familyId, final Long tangibleId)
            throws NCDBApiException {
        Order lastOrder = this.getLastOrder(familyId, tangibleId);
        return lastOrder == null ? -1 : lastOrder.getOdometerAmount().intValue();
    }

    @Override
    public int getLastUsedStoreId(final Long familyId, final Long tangibleId) throws NCDBApiException {
        Order lastOrder = this.getLastOrder(familyId, tangibleId);
        if (lastOrder == null) {
            String msg = new StringBuilder()
                    .append("Could not determine the last store. There are no transactions available for family: ")
                    .append(familyId)
                    .append(" Tangible: ")
                    .append(tangibleId).toString();
            log.warn(msg);
            return -1;
        }
        return lastOrder.getStoreNumber();
    }

}
