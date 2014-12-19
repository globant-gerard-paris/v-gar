package com.searshc.mygarage.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class VehicleComponentStatusUtils {

	private Set<String> brakesSKUCodes = new HashSet<String>();
	private Set<String> tiresSKUCodes = new HashSet<String>();
	private Set<String> oilSKUCodes = new HashSet<String>();
	
	private Set<String> brakesProductFlags = new HashSet<String>();
	private Set<String> tiresProductFlags = new HashSet<String>();
	private Set<String> oilProductFlags = new HashSet<String>();
	
	public VehicleComponentStatusUtils() {
		brakesSKUCodes.add("02801300000"); //Complete Brake Evaluation
		
		tiresSKUCodes.add("02806422000");//Tire Pressure Monitoring System Evaluation
		tiresSKUCodes.add("02801315000"); //Suggested New Tires
		
		oilSKUCodes.add("02806672000"); //Adjust Low Oil Level
		
		brakesProductFlags.add("44"); //Brake Service
		brakesProductFlags.add("60"); //Brake Eval
		
		tiresProductFlags.add("41");//Tire Service
		
		oilProductFlags.add("49");//Oil Change Service
	}
	
}
