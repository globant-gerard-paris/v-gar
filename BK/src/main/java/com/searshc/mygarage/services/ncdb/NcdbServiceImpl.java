package com.searshc.mygarage.services.ncdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import scala.collection.mutable.StringBuilder;

import com.searshc.mygarage.apis.ncdb.NCDBApi;
import com.searshc.mygarage.apis.ncdb.response.order.OrderHeaderResponse;
import com.searshc.mygarage.apis.ncdb.response.order.OrderHistoryResponse;
import com.searshc.mygarage.apis.ncdb.response.order.OrderItemResponse;
import com.searshc.mygarage.dtos.VehicleGenericDescriptionDTO;
import com.searshc.mygarage.entities.record.NcdbServiceRecord;
import com.searshc.mygarage.entities.record.RecommendedService;
import com.searshc.mygarage.entities.record.ServiceCenter;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.entities.record.ServiceRecordItem;
import com.searshc.mygarage.entities.record.Order;
import com.searshc.mygarage.entities.record.OrderItem;
import com.searshc.mygarage.entities.record.ServiceTranslation;
import com.searshc.mygarage.entities.Store;
import com.searshc.mygarage.entities.record.SuggestedService;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.record.RealServiceRecordItem;
import com.searshc.mygarage.entities.record.RecommendedServiceRecordItem;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.repositories.RecommendedServiceBlockedRepository;
import com.searshc.mygarage.repositories.RecordRepository;
import com.searshc.mygarage.repositories.ServiceTranslationRepository;
import com.searshc.mygarage.repositories.StoreRepository;
import com.searshc.mygarage.repositories.SuggestedServiceRepository;
import com.searshc.mygarage.util.VGUtils;

import java.util.Date;

@Service
public class NcdbServiceImpl implements NcdbService {

    private static final Log log = LogFactory.getLog(NcdbServiceImpl.class);

    private NCDBApi ncdbApi;

    private StoreRepository storeRepository;

    private ServiceTranslationRepository serviceTranslationRepository;

    private SuggestedServiceRepository suggestedServiceRepository;

    private RecordRepository recordRepository;

    private RecommendedServiceBlockedRepository recommendedServiceBlockedRepository;

    private final Mapper mapper = new DozerBeanMapper();

    @Inject
    public NcdbServiceImpl(NCDBApi ncdbApi, StoreRepository storeRepository,
            ServiceTranslationRepository serviceTranslationRepository,
            SuggestedServiceRepository suggestedServiceRepository,
            RecordRepository recordRepository,
            RecommendedServiceBlockedRepository recommendedServiceBlockedRepository) {
        this.ncdbApi = ncdbApi;
        this.storeRepository = storeRepository;
        this.serviceTranslationRepository = serviceTranslationRepository;
        this.suggestedServiceRepository = suggestedServiceRepository;
        this.recordRepository = recordRepository;
        this.recommendedServiceBlockedRepository = recommendedServiceBlockedRepository;
    }

    @Override
    public List<FamilyVehicle> listVehicles(Long familyId) {
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

    private Order getLastOrder(List<Order> orders)
            throws NCDBApiException {
        return orders.isEmpty() ? null : orders.get(0);
    }

    private List<Order> processTransactions(Long familyId, Long tangibleId, RecordFilter recordFilter) {
        OrderHistoryResponse orderHistoryResponse = (familyId != null && tangibleId != null)
                ? this.ncdbApi.getVehicleHistory(familyId, tangibleId) : null;
        List<Order> orders = null;
        if (orderHistoryResponse != null) {
            Map<String, Order> ordersMap = this.createOrdersMap(orderHistoryResponse.getOrdersHeader());
            for (OrderItemResponse orderItemResponse : orderHistoryResponse.getOrderItems()) {
                if (ordersMap.containsKey(orderItemResponse.getOrderNumber())) {
                    Order order = ordersMap.get(orderItemResponse.getOrderNumber());
                    OrderItem orderItem = this.mapper.map(orderItemResponse, OrderItem.class);
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
    public List<ServiceRecord> getServiceRecords(Long familyId, Long tangibleId) {
        RecordFilter ncdbRecordFilter = new RecordFilter();
        List<Order> orders = this.processTransactions(familyId, tangibleId, ncdbRecordFilter);
        List<ServiceRecord> serviceRecords
                = new ArrayList<ServiceRecord>(ncdbRecordFilter.serviceRecordsMap.values());
        Order lastOrder = this.getLastOrder(orders);
        if (lastOrder != null) {
            lastOrder.setRecommendedService(this.createRecommendedServices(lastOrder, false));
        }
        Collections.sort(serviceRecords, new Comparator<ServiceRecord>() {
            @Override
            public int compare(ServiceRecord o1, ServiceRecord o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
        return serviceRecords;
    }

    private RecommendedService createRecommendedServices(Order lastOrder, boolean includeOrder) {
        RecommendedService recommendedService = null;
        if (lastOrder != null) {
            recommendedService = new RecommendedService(includeOrder ? lastOrder : new Order(), lastOrder.getServiceCenter());
            for (OrderItem item : lastOrder.getOrderItems()) {
                ServiceRecordItem sri = this.createRecommendedServiceRecordItem(item);
                if (sri != null && this.checkIsNotLocalServiceRecord(lastOrder.getTransactionDateTime(), sri.getCode())
                        && this.checkIsNotRecommendedServiceBlocked(lastOrder, sri.getCode())) {
                    recommendedService.addServiceRecordItem(sri);
                } else {
                    ServiceRecordItem prev = this.createNcdbServiceRecordItem(item);
                    if (prev != null) {
                        recommendedService.addPreviousServiceRecordItem(prev);
                    }
                }
            }
        }
        return recommendedService != null
                && !recommendedService.getServiceRecordItems().isEmpty()
                        ? recommendedService : null;
    }

    @Override
    public RecommendedService getRecommendedServices(Long familyId, Long tangibleId) {
        Order lastOrder = this.getLastOrder(familyId, tangibleId);
        return this.createRecommendedServices(lastOrder, true);
    }

    private boolean checkIsNotLocalServiceRecord(Date date, String sku) {
        return this.recordRepository.countByDateAndSku(date, sku) == 0;
    }

    private boolean checkIsNotRecommendedServiceBlocked(Order order, String sku) {
        return this.recommendedServiceBlockedRepository
                .countByOrderNumberAndFamilyIdNumberAndTangibleIdNumberAndSku(order.getOrderNumber(),
                        order.getFamilyIdNumber(), order.getTangibleIdNumber(), sku) == 0;
    }

    private ServiceRecordItem createRecommendedServiceRecordItem(OrderItem item) {
        SuggestedService sgt
                = this.suggestedServiceRepository.findBySku(item.getItemId());
        ServiceRecordItem sri = null;
        if (sgt != null) {
            sri = mapper.map(sgt, RecommendedServiceRecordItem.class);
            sri.setCode(item.getItemId());
        }
        return sri;
    }

    private ServiceRecordItem createNcdbServiceRecordItem(OrderItem orderItem) {
        ServiceTranslation st
                = serviceTranslationRepository.findByProductFlag(orderItem.getProductFlag());
        ServiceRecordItem sri = null;
        if (st != null) {
            sri = mapper.map(st, RealServiceRecordItem.class);
            sri.setCode(String.valueOf(orderItem.getProductFlag()));
        }
        return sri;
    }

    private class RecordFilter {

        Map<String, ServiceRecord> serviceRecordsMap = new HashMap<String, ServiceRecord>();

        public void filter(Order order, OrderItem orderItem) {
            ServiceRecordItem sri = createNcdbServiceRecordItem(orderItem);
            if (sri != null) {
                if (serviceRecordsMap.containsKey(order.getOrderNumber())) {
                    serviceRecordsMap.get(order.getOrderNumber())
                            .addServiceRecordItem(sri);
                } else {
                    serviceRecordsMap.put(order.getOrderNumber(),
                            new NcdbServiceRecord(order, order.getServiceCenter(), sri));
                }
            }
        }
    }

    @Override
    public int getHighestMileage(final Long familyId, final Long tangibleId)
            throws NCDBApiException {
        Order lastOrder = this.getLastOrder(familyId, tangibleId);
        return lastOrder == null ? -1 : lastOrder.getOdometerAmount();
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

    @Override
    public List<VehicleGenericDescriptionDTO> getVehicleByVINNumber(final String vinNumber) {
        return this.ncdbApi.getVehicleByVINNumber(vinNumber);
    }

    @Override
    public List<VehicleGenericDescriptionDTO> getVehicleByLicensePlate(final String licensePlate) throws NCDBApiException {
        return this.ncdbApi.getVehicleByLicensePlate(licensePlate);
    }

}
