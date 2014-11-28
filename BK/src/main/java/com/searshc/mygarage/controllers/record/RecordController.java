package com.searshc.mygarage.controllers.record;

import com.searshc.mygarage.entities.record.ServiceRecord;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.searshc.mygarage.dtos.record.RecordAssembly;
import com.searshc.mygarage.dtos.record.RecordDto;

import com.searshc.mygarage.entities.record.Record;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.services.ncdb.NcdbService;
import com.searshc.mygarage.services.record.RecordService;

import org.springframework.web.bind.annotation.RestController;

import com.searshc.mygarage.services.vehicle.FamilyVehicleService;

@RestController
@RequestMapping("/record")
public class RecordController {

    private static final Log log = LogFactory.getLog(RecordController.class);

    private RecordService recordService;
    private FamilyVehicleService familyVehicleService;
    private NcdbService ncdbService;

    @Inject
    public RecordController(final RecordService recordService, final FamilyVehicleService familyVehicleService, final NcdbService ncdbService) {
        this.recordService = notNull(recordService, "The Record Service cannot be null");
        this.familyVehicleService = notNull(familyVehicleService, "The FamilyVehicle Service cannot be null");
        this.ncdbService = notNull(ncdbService, "The NCDB Service cannot be null");
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
        Record record = RecordAssembly.toRecord(recordDto);
        this.recordService.save(record);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/vehicle/{familyVehicleId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<ServiceRecord>> getCarRecords(@PathVariable("familyVehicleId") Long familyVehicleId)
            throws NCDBApiException {

        List<ServiceRecord> serviceRecords
                = this.recordService.getServiceRecords(familyVehicleId);

        return new ResponseEntity<List<ServiceRecord>>(serviceRecords, null, HttpStatus.OK);
    }
}
