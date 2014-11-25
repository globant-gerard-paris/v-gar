package com.searshc.mygarage.services;

import com.searshc.mygarage.apis.ncdb.NCDBApiImpl;

import com.searshc.mygarage.entities.record.Order;

import com.searshc.mygarage.entities.FamilyVehicle;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Assert;
import static org.mockito.Mockito.*;

/**
 *
 * @author rammel.maestre
 */
//@RunWith(MockitoJUnitRunner.class)
public class NcdbServiceImplTest {
    /*
     @InjectMocks
     private NcdbServiceImpl ncdbServiceImpl;

     @Mock
     private NCDBApiImpl nCDBApi;

     @Test
     public void listVehiclesSuccess() {
     when(nCDBApi.getVehicles(anyInt()))
     .thenReturn(new ArrayList<Vehicle>());
     List<Vehicle> result = ncdbServiceImpl.listVehicles(anyInt());
     Assert.assertNotNull(result);
     }

     @Test
     public void getTransactionsSuccess() {
     when(nCDBApi.getCarTransactionHistory(anyInt(), anyInt()))
     .thenReturn(new ArrayList<Order>());
     List<Order> result = ncdbServiceImpl.getTransactions(anyInt(), anyInt());
     Assert.assertNotNull(result);
     }*/

}
