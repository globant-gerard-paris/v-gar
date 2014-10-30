package com.searshc.mygarage.apis.ncdb.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ESBMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class EsbMsgRequest extends EsbMsg {

    @XmlElement(name = "Query")
    private Query query;

    public EsbMsgRequest() {
    }

    public EsbMsgRequest(MdsHeader mdsHeader, Query query) {
        this.mdsHeader = mdsHeader;
        this.query = query;
    }

    /**
     * @return the query
     */
    public Query getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(Query query) {
        this.query = query;
    }

}
