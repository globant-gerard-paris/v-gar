package com.searshc.mygarage.services.record;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;

import com.searshc.mygarage.apis.ncdb.NCDBApi;
import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.Record;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.repositories.RecordRepository;

public class RecordService extends GenericService<Record, Long, RecordRepository> {

    private NCDBApi ncdbApi;

    @Inject
    public RecordService(final NCDBApi ncdbApi) {
        this.ncdbApi = Validate.notNull(ncdbApi, "The NCDB Api cannot be null");
    }

    public List<Record> getRecordsByUserVehicleId(final Long userVehicleId) {
        return repository.getRecordsByUserVehicleId(userVehicleId);
    }

    public void deleteRecord(final Long recordId) {
        repository.delete(recordId);
    }

    public List<Order> getTransactions(final Long familyIdNumber, final Long tangibleId) throws NCDBApiException {
        //TODO: query the ncdb service
        //TODO: query the local database
        //TODO: merge both results
        return this.ncdbApi.getCarTransactionHistory(familyIdNumber, tangibleId);
    }
}
