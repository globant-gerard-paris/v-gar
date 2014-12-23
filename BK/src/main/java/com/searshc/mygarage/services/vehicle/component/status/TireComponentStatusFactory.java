package com.searshc.mygarage.services.vehicle.component.status;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.searshc.mygarage.entities.record.ServiceRecord;

@Component
public class TireComponentStatusFactory extends AbstractComponentStatusFactory {

    private static final String COMPONENT_NAME = "Tire Rotation";

    @Value("#{'${carprofile.infographics.vehicle.component.tire.local.service.codes}'.split(',')}")
    private Set<String> localServiceCategories;
    @Value("#{'${carprofile.infographics.vehicle.component.tire.ncdb.service.codes}'.split(',')}")
    private Set<String> remoteServiceCategories;

    private static final int OK_PERIOD_MONTHS_QUANTITY = 4;
    private static final int APPROACHING_DUE_MONTHS_QUANTITY = 5;

    @Override
    public String getComponentName() {
        return COMPONENT_NAME;
    }

    @Override
    public Set<String> getLocalServiceCategories() {
        return localServiceCategories;
    }

    @Override
    public Set<String> getRemoteServiceCategories() {
        return remoteServiceCategories;
    }

    @Override
    public String determineStatusName(final ServiceRecord serviceRecord) {
        if (serviceRecord == null) {
            return TireStatus.LOW.name();
        }
        DateTime today = DateTime.now();
        DateTime okStartDate = today.minusMonths(OK_PERIOD_MONTHS_QUANTITY);
        DateTime dueApproachingStartDate = today.minusMonths(APPROACHING_DUE_MONTHS_QUANTITY);

        DateTime serviceRecordDate = new DateTime(serviceRecord.getDate());

        if (serviceRecordDate.isAfter(okStartDate)) {
            return TireStatus.FULL.name();
        } else {
            if (serviceRecordDate.isAfter(dueApproachingStartDate)) {
                return TireStatus.MEDIUM.name();
            } else {
                return TireStatus.LOW.name();
            }
        }
    }

    @Override
    public String determineStatusDescription(final String statusName) {
        return TireStatus.valueOf(statusName).description;
    }

    @Override
    public String getComponent() {
        return "TIRES";
    }

    public enum TireStatus {

        FULL("OK"),
        MEDIUM("Maintenance approaching due"),
        LOW("Suggest tire rotation"),
        EMPTY("");

        private String description;

        private TireStatus(String description) {
            this.description = description;
        }

        public static TireStatus getBrakeStatusByDescription(final String description) {
            Validate.isTrue(!StringUtils.isEmpty(description), "The description cannot be null or empty");
            for (TireStatus status : TireStatus.values()) {
                if (status.description.equalsIgnoreCase(description)) {
                    return status;
                }
            }
            return null;
        }

        /**
         * @return the description
         */
        public String getDescription() {
            return description;
        }

    }

}
