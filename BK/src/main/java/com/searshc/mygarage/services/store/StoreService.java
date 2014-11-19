package com.searshc.mygarage.services.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.util.VGUtils;

/**
 *
 * The {@link StoreService} have the responsibility to retrieve stores of the
 * system.
 *
 * @author Jero
 *
 */
@Service
public class StoreService {

    private SimpleJdbcCall simpleJdbcCall;
    private static final long DEFAULT_STORES_TO_RETRIEVE = 8;
    private static final String DEFAULT_SCOPE_SEARCH = "1000";

    public StoreService() {
        super();
    }

    @Inject
    public StoreService(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("GEODIST");
    }

    /**
     *
     * Retrieve the Stores near of given latitude, longitude and distance in
     * miles that will limit the result.
     *
     * @param latitude The origin latitude that will be use to perform the
     * search of stores near.
     * @param longitude The origin longitude that will be use to perform the
     * search of stores near.
     * @param distance The distance in miles that limit the search of stores.
     * @return
     */
    public List<Object> getStoresNear(final String latitude, final String longitude, final Long limiteStores, final String distance) {

        float latFlot = VGUtils.parseSafeFloat(latitude);
        float longFlot = VGUtils.parseSafeFloat(longitude);

        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("mylat", latFlot);
        inParamMap.put("mylong", longFlot);
        inParamMap.put("dist", (DEFAULT_SCOPE_SEARCH == null ? DEFAULT_SCOPE_SEARCH : distance));
        inParamMap.put("limitStore", (limiteStores == null ? DEFAULT_STORES_TO_RETRIEVE : limiteStores));
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);

        return (simpleJdbcCallResult != null) ? new ArrayList<Object>(simpleJdbcCallResult.values())
                : null;
    }
;
}
