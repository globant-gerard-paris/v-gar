package com.searshc.mygarage.apis.ncdb.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ESBMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class EsbMsg {

    @XmlElement(name = "MDSHeader")
    private MdsHeader mdsHeader;

    @XmlElement(name = "Query")
    private Query query;

    public EsbMsg() {
    }

    public EsbMsg(MdsHeader mdsHeader, Query query) {
        this.mdsHeader = mdsHeader;
        this.query = query;
    }

    /**
     * @return the mdsHeader
     */
    public MdsHeader getMdsHeader() {
        return mdsHeader;
    }

    /**
     * @param mdsHeader the mdsHeader to set
     */
    public void setMdsHeader(MdsHeader mdsHeader) {
        this.mdsHeader = mdsHeader;
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
