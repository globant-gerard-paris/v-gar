package com.searshc.mygarage.controllers;

import com.searshc.mygarage.entities.record.RecommendedService;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.services.record.RecordService;
import javax.inject.Inject;
import static org.apache.commons.lang3.Validate.notNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommended")
public class RecommendedControllerOnlyForTest {

    private RecordService recordService;

    @Inject
    public RecommendedControllerOnlyForTest(final RecordService recordService) {
        this.recordService = notNull(recordService, "The Record Service cannot be null");
    }

    @RequestMapping(value = "/family/{familyId}/vehicle/{tangibleId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RecommendedService> getCarRecords(@PathVariable("familyId") Long familyId,
            @PathVariable("tangibleId") Long tangibleId) throws NCDBApiException {

        RecommendedService recommendedService = this.recordService.getRecommendedServices(familyId, tangibleId);

        return new ResponseEntity<RecommendedService>(recommendedService, null, HttpStatus.OK);
    }

}
