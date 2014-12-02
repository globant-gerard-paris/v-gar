package com.searshc.mygarage.controllers.record;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.searshc.mygarage.dtos.record.RecordAssembly;
import com.searshc.mygarage.dtos.record.RecordDto;
import com.searshc.mygarage.entities.record.Record;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.entities.record.SuggestedService;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.orchestrators.RecordOrchestrator;
import com.searshc.mygarage.services.record.RecordService;

@RestController
@RequestMapping("/record")
public class RecordController {

    private RecordService recordService;

    private RecordOrchestrator recordOrchestrator;

    @Inject
    public RecordController(final RecordService recordService, final RecordOrchestrator recordOrchestrator) {
        this.recordService = notNull(recordService, "The Record Service cannot be null");
        this.recordOrchestrator = notNull(recordOrchestrator, "The RecordOrchestrator cannot be null");
    }

    @RequestMapping(value = "/{recordId}",
            method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> deleteRecords(@PathVariable("recordId") Long recordId) throws Exception {
        this.recordService.deleteRecord(recordId);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/familyvehicle/{familyVehicleId}/record",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> addRecord(@RequestBody RecordDto recordDto,
            @PathVariable("familyVehicleId") Long familyVehicleId)
            throws Exception {
        this.recordOrchestrator.addRecord(recordDto, familyVehicleId);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/familyvehicle/{familyVehicleId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<ServiceRecord>> getCarRecords(@PathVariable("familyVehicleId") Long familyVehicleId)
            throws NCDBApiException {

        List<ServiceRecord> serviceRecords
                = this.recordService.getServiceRecords(familyVehicleId);

        return new ResponseEntity<List<ServiceRecord>>(serviceRecords, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/suggestedservices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<SuggestedService>> getSuggestedServices() throws NCDBApiException {
        List<SuggestedService> suggestedServices = this.recordService.getSuggestedServices();
        return new ResponseEntity<List<SuggestedService>>(suggestedServices, HttpStatus.OK);
    }
}
