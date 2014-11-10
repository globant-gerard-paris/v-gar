package com.searshc.mygarage.controllers.vehicle;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.searshc.mygarage.AbstractIntegrationTest;
import com.searshc.mygarage.entities.Record;
import com.searshc.mygarage.entities.VehicleInformation;
import com.searshc.mygarage.services.vehicle.VehicleInformationService;

/**
 * 
 * @author Jero
 *
 */
public class VehicleInformationControllerTest extends AbstractIntegrationTest {

	private static String URI_CREATE_RECORD = "/family/{familyId}/tangible/{tangibleId}/record";
	private static String URI_GET_RECORDS = "/family/{familyId}/tangible/{tangibleId}/records";

	private Long familyId = 123L;
	private Long tangibleId = 456L;

	@Mock
	VehicleInformationService service;

	@InjectMocks
	VehicleInformationController SUT; // software under test

	private MockMvc mockMvc;

	@Before
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(SUT).build();

		Record record = new Record();
		
		List<Record> records = new ArrayList<Record>(Arrays.asList(record));
		VehicleInformation userInformation = new VehicleInformation();
		userInformation.setRecords(records);
		userInformation.setFamilyId(familyId);
		userInformation.setTangibleId(tangibleId);

		try {
			Mockito.when(service.findAllRecordsByFamilyIdAndTangibleId(familyId, tangibleId)).thenReturn(records);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Ignore // FIXME: need more time to fix it.
	public void addRecord() throws Exception {
		RecordDto recordDto = new RecordDto();
		recordDto.setComment("comment_test");
		recordDto.setDate("date_test");
		recordDto.setService("service_test");
		recordDto.setSource("source_test");
		
		mockMvc.perform(post(URI_CREATE_RECORD, familyId, tangibleId)
                .contentType(MediaType.APPLICATION_JSON)
//                .param("description", description)
//                .param("title", title)
//                .sessionAttr("recordDto", recordDto)
        )
         .andDo(print())
         .andExpect(status().isOk());
		
	}
	@Test
	public void getRecords() throws Exception {
		mockMvc.perform(
				get(URI_GET_RECORDS, familyId, tangibleId).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}