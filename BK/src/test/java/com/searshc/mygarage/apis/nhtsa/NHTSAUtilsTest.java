package com.searshc.mygarage.apis.nhtsa;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;

public class NHTSAUtilsTest {

    @Test
    public void convertNHTSADateToDateTimeTest() {
        String nhtsDate = "/Date(951368400000-0500)/";
        DateTime dateExpected = new DateTime(2000, 02, 24, 10, 00, 00, 000, DateTimeZone.forOffsetHours(+5));
        DateTime actualDate = NHTSAUtils.convertNHTSADateToDateTime(nhtsDate);
        Assert.assertEquals(dateExpected, actualDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void converNHTSADateToDateTimeIncorrectTimezoneFormat() {
        String nhtsDate = "/Date(951368400000-05000)/";
        NHTSAUtils.convertNHTSADateToDateTime(nhtsDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void converNHTSADateToDateTimeEmptyDate() {
        NHTSAUtils.convertNHTSADateToDateTime("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void converNHTSADateToDateTimeNullDate() {
        NHTSAUtils.convertNHTSADateToDateTime(null);
    }

}
