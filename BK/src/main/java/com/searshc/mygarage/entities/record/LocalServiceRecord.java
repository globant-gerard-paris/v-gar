package com.searshc.mygarage.entities.record;

import com.searshc.mygarage.util.ServiceRecordType;
import java.util.Date;

public class LocalServiceRecord extends ServiceRecord {

    private Long id;
    private String source;

    public LocalServiceRecord() {
        this.type = ServiceRecordType.LOCAL_SERVICE;
    }

    public LocalServiceRecord(Long id, String source, int mileage, Date date,
            ServiceRecordItem serviceRecordItem) {
        super(mileage, date, ServiceRecordType.LOCAL_SERVICE, serviceRecordItem);
        this.id = id;
        this.source = source;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

}
