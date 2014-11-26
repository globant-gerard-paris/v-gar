package com.searshc.mygarage.services;

import com.searshc.mygarage.apis.ncdb.NCDBApiImpl;
import com.searshc.mygarage.apis.ncdb.response.EsbMsgRequest;
import com.searshc.mygarage.apis.ncdb.response.vehicle.VehicleRetrievalResponse;
import com.searshc.mygarage.apis.ncdb.response.order.OrderHistoryResponse;
import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.UserVehicle;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import org.junit.Assert;
import static org.mockito.Mockito.*;

/**
 *
 * @author rammel.maestre
 */
//@RunWith(MockitoJUnitRunner.class)
public class NCDBApiImplTest {
    /*
     @InjectMocks
     private NCDBApiImpl nCDBApiImpl;

     @Mock
     private RestTemplate restTemplate;

     @Test
     public void getVehiclesSuccess() {

     when(this.restTemplate.postForObject(anyString(),
     any(EsbMsgRequest.class), eq(VehicleRetrievalResponse.class)))
     .thenReturn(new VehicleRetrievalResponse());

     List<Vehicle> vehicles = this.nCDBApiImpl.getVehicles(anyInt());

     Assert.assertNotNull(vehicles);

     }

     @Test
     public void getCarTransactionHistorySuccess() {

     when(this.restTemplate.postForObject(anyString(),
     any(EsbMsgRequest.class), eq(OrderHistoryResponse.class)))
     .thenReturn(new OrderHistoryResponse());

     List<Order> orders = this.nCDBApiImpl.getCarTransactionHistory(anyInt(), anyInt());

     Assert.assertNotNull(orders);

     }*/
}
