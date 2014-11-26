package com.searshc.mygarage.controllers.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.searshc.mygarage.AbstractIntegrationTest;
import com.searshc.mygarage.entities.Store;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.services.user.UserService;

/**
 *
 * @author Jero
 *
 */
public class UserInformationControllerTest {// extends AbstractIntegrationTest {

    /*private static String URI_UPDATE_USER_STORE = "/user/{userId}/store/{storeId}";
     private static String URI_FIND_USER_STORE = "/user/{userId}/store";

     private Long userId = 123L;
     private Long storeId = 123L;

     @Mock
     UserInformationService service;

     @InjectMocks
     UserInformationController SUT; // software under test

     private MockMvc mockMvc;

     @Before
     public void beforeEach() {
     MockitoAnnotations.initMocks(this);
     this.mockMvc = MockMvcBuilders.standaloneSetup(SUT).build();

     Store store = new Store();
     UserInformation userInformation = new UserInformation();
     userInformation.setStore(store);
     userInformation.setUserId(userId);

     Mockito.when(service.findByUserId(userId)).thenReturn(userInformation);
     }

     @Test
     public void setStoreFavoriteSuccess() throws Exception {
     mockMvc.perform(
     put(URI_UPDATE_USER_STORE, userId, storeId).accept(MediaType.APPLICATION_JSON))
     .andDo(print()).andExpect(status().isOk());
     }

     @Test
     public void findUserOInformationSuccess() throws Exception {
     mockMvc.perform(
     get(URI_FIND_USER_STORE, userId, storeId).accept(MediaType.APPLICATION_JSON))
     .andDo(print()).andExpect(status().isOk());

     }*/
}
