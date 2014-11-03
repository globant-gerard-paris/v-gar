package com.searshc.mygarage.controllers.store;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

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
import com.searshc.mygarage.controllers.store.StoreController;
import com.searshc.mygarage.entities.store.Store;
import com.searshc.mygarage.repositories.StoreRepository;
import com.searshc.mygarage.services.store.StoreService;

/**
 * 
 * @author Jero
 *
 */
public class StoreControllerTest extends AbstractIntegrationTest {

	private static String URI_STORES_LAT_LANG = "/stores/lt/{myLat}/lg/{myLong}?limit=10&distance=2000";
	private static String URI_STORES = "/stores";
	private static String URI_STORE = "/store/{storeId}";
	private long storeId = 786L;
	private String lat = "35.17380";
	private String lng = "-77.60742";
	private String miles = "2000";
	private Long limiteStores = 8L;

	@Mock
	StoreService storeService;

	@Mock
	StoreRepository storeRepository;

	@InjectMocks
	StoreController controllerUnderTest;

	private MockMvc mockMvc;

	@Before
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).build();

		Store store = new Store();
		store.setCity("BuenosAires");
		Store stores[] = { store };
		Mockito.when(storeRepository.findAll()).thenReturn(Arrays.asList(stores));
		Mockito.when(storeRepository.findOne(storeId)).thenReturn(store);
		Mockito.when(storeService.getStoresNear(lat, lng, limiteStores, miles)).thenReturn(
				new ArrayList<Object>());
	}

	@Test
	public void testFindOneSuccess() throws Exception {
		mockMvc.perform(get(URI_STORE, storeId).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.city").value("BuenosAires"));
	}

	@Test
	public void testFinAllSuccess() throws Exception {
		mockMvc.perform(get(URI_STORES).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].city").value("BuenosAires"));
	}

	@Test
	public void testGivenLatLangSuccess() throws Exception {
		mockMvc.perform(
				get(URI_STORES_LAT_LANG, lat, lng, limiteStores, miles).accept(
						MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

}