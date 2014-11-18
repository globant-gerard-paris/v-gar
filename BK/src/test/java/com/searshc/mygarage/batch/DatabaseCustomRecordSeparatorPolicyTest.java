package com.searshc.mygarage.batch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.searshc.mygarage.AbstractIntegrationTest;
import com.searshc.mygarage.batch.CustomRecordSeparatorPolicy;

public class DatabaseCustomRecordSeparatorPolicyTest extends AbstractIntegrationTest {

    static CustomRecordSeparatorPolicy customRecordSeparatorPolicy;
    private String delimiter = ";";
    private int maxLineTokenDefault = 31;

    @BeforeClass
    public static void init() {
        customRecordSeparatorPolicy = new CustomRecordSeparatorPolicy();
    }

    @Test
    public void testSuccess() {
        String text = "2611;Free;SAC;9072;Northeast;540;New Jersey;832 RTE 1 SOUTH               ;ISELIN       ;NJ;8830;(732)283-7025;No;No;No;40,559689;-74,303924;John Albizu;8;19;8;19;8;19;8;19;8;19;8;19;10;16;Brake Service or brake check/evaluation;Oil change;Alignment;Transmission fluid exchange;Coolant exchange;Tire rotation;Wheel balancing;Tire mounting and balancing;Wheel lock removal;Power steering fluid exchange;Fuel system cleaning;Shock or Strut replacement;Wheel bearing service;Belt replacement;Differential service;Steering or suspension check/evaluation;Steering component replacement;Battery check;Battery replacement;Multi-point under car & under hood courtesy check;Flat repair;Air filter replacement;Cabin air filter replacement;Car top carrier installation;Trailer Hitch Installation (Bolt-on only);Wiper blade installation;Exhaust Service;Air Conditioning repair and replace;Diagnostics;Fuel pumps service and installation;In tank fuel pump service and installation;Transmission service;;;Head Gasket Service;Water Pump Service and Replacement;Radiator Service and Repair;Timing Belt Service and Repair;Spark Plugs and other ignition service;Oxygen Sensors Service and Repair;";
        String result = customRecordSeparatorPolicy.postProcess(text);
        assertTrue(StringUtils.countOccurrencesOf(result, delimiter) == maxLineTokenDefault);
    }

    @Test
    public void testSuccessWithEmptyValues() {
        String text = ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Brake Service or brake check/evaluation;Oil change;Alignment;Transmission fluid exchange;Coolant exchange;Tire rotation;Wheel balancing;Tire mounting and balancing;Wheel lock removal;Power steering fluid exchange;Fuel system cleaning;Shock or Strut replacement;Wheel bearing service;Belt replacement;Differential service;Steering or suspension check/evaluation;Steering component replacement;Battery check;Battery replacement;Multi-point under car & under hood courtesy check;Flat repair;Air filter replacement;Cabin air filter replacement;Car top carrier installation;;Wiper blade installation;Exhaust Service;Air Conditioning repair and replace;Diagnostics;Fuel pumps service and installation;In tank fuel pump service and installation;;;Valve Cover Gasket Service;Head Gasket Service;Water Pump Service and Replacement;Radiator Service and Repair;;Spark Plugs and other ignition service;;Oxygen Sensors Service and Repair;;";
        String result = customRecordSeparatorPolicy.postProcess(text);
        assertEquals(StringUtils.countOccurrencesOf(result, delimiter), maxLineTokenDefault);
    }

    @Test
    public void testErrorWithIncmpleteToken() {
        String text = ";;;;;;;;;;";
        boolean isError = false;
        String msgError = "";
        try {
            customRecordSeparatorPolicy.postProcess(text);
        } catch (Exception e) {
            isError = true;
            msgError = e.getMessage();
        }
        assertTrue(isError);
        Assert.hasText(msgError, "The line [;;;;;;;;;;] must have 31, but instead have 10, check & fix this line and try again.");
    }
}
