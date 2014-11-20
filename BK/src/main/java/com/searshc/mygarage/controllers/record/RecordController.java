package com.searshc.mygarage.controllers.record;

import com.searshc.mygarage.dtos.ServiceRecord;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
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
import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.Record;
import com.searshc.mygarage.entities.UserVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.services.record.RecordService;
import com.searshc.mygarage.services.vehicle.UserVehicleService;

@RequestMapping("/record")
public class RecordController {

    private RecordService recordService;
    private UserVehicleService userVehicleService;

    @Inject
    public RecordController(final RecordService recordService, final UserVehicleService userVehicleService) {
        this.recordService = notNull(recordService, "The Record Service cannot be null");
        this.userVehicleService = notNull(userVehicleService, "The UserVehicle Service cannot be null");
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/uservehicle/{userVehicleId}/records", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Record>> getRecords(
            @PathVariable("userVehicleId") Long userVehicleId) throws Exception {
        List<Record> records = this.recordService.getLocalRecordsByUserVehicleId(userVehicleId);
        if (CollectionUtils.isNotEmpty(records)) {
            return new ResponseEntity<List<Record>>(records, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Record>>(Collections.EMPTY_LIST, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{recordId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> deleteRecords(
            @PathVariable("recordId") Long recordId) throws Exception {
        this.recordService.deleteRecord(recordId);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/uservehicle/{userVehicleId}/record", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> addRecord(@RequestBody RecordDto recordDto,
            @PathVariable("userVehicleId") Long userVehicleId)
            throws Exception {
        Record record = RecordAssembly.toRecord(recordDto);
        this.recordService.save(record);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/uservehicle/{vehicleId}/ncdb/transactions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<ServiceRecord>> getCarTransactionsHistory(@PathVariable("vehicleId") Long vehicleId) throws NCDBApiException {
        List<ServiceRecord> serviceRecords = null;
        UserVehicle userVehicle = this.userVehicleService.getItem(vehicleId);
        if (userVehicle.getTangibleId() != null && userVehicle.getFamilyId() != null) {
            serviceRecords = this.recordService.getServiceRecords(userVehicle.getFamilyId(), userVehicle.getTangibleId());
        }
        return new ResponseEntity<List<ServiceRecord>>(serviceRecords, null, HttpStatus.OK);
    }
}
