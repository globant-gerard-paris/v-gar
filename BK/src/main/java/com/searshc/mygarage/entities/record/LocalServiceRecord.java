package com.searshc.mygarage.entities.record;

import com.searshc.mygarage.util.ServiceRecordType;
import java.util.Date;

public class LocalServiceRecord extends ServiceRecord {

    private Long id;
    private String source;
    private String comment;

    public LocalServiceRecord() {
        this.type = ServiceRecordType.LOCAL_SERVICE;
    }

    public LocalServiceRecord(final Long id,final String source,final int mileage,final Date date,
            final ServiceRecordItem serviceRecordItem, final String comment) {
        super(mileage, date, ServiceRecordType.LOCAL_SERVICE, serviceRecordItem);
        this.id = id;
        this.source = source;
        this.comment = comment;
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
    
    /**
     * @return the comment.
     */
	public String getComment() {
		return comment;
	}

	/**
	 * 
	 * @param comment to set.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}
