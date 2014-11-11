package com.searshc.mygarage.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class AdditionalVehicleTest {
	
	private final String MAKE = "Toyota";
	private final String MODEL = "Corolla";
	private final int YEAR = 2014;
	private final String COLOR = "BLACK";
	private final int MILEAGE = 2000;
	
	private AdditionalVehicle additionalVehicle;
	
	@Before
	public void setUp() {
		this.additionalVehicle = new AdditionalVehicle(MAKE, MODEL, YEAR, COLOR, MILEAGE);
	}
	
	@Test
	public void additionalVehicleCreationHappyPathNoArgsConstructor() {
		AdditionalVehicle additionalVehicle = new AdditionalVehicle();
		assertTrue(additionalVehicle.getId() == 0);
		assertEquals(additionalVehicle.getMake(), "");
		assertEquals(additionalVehicle.getModel(), "");
		assertEquals(additionalVehicle.getYear(), 0);
		assertEquals(additionalVehicle.getColor(), "");
		assertEquals(additionalVehicle.getMileage(), 0);
		
		additionalVehicle.setMake(MAKE);
		additionalVehicle.setModel(MODEL);
		additionalVehicle.setYear(YEAR);
		additionalVehicle.setColor(COLOR);
		additionalVehicle.setMileage(MILEAGE);
		
		assertEquals(additionalVehicle.getMake(), MAKE);
		assertEquals(additionalVehicle.getModel(), MODEL);
		assertEquals(additionalVehicle.getYear(), YEAR);
		assertEquals(additionalVehicle.getColor(), COLOR);
		assertEquals(additionalVehicle.getMileage(), MILEAGE);
	}
	
	@Test
	public void additionalVehicleCreationHappyPathArgsContructor() {
		AdditionalVehicle additionalVehicle = new AdditionalVehicle(MAKE, MODEL, YEAR, COLOR, MILEAGE);
		assertEquals(additionalVehicle.getMake(), MAKE);
		assertEquals(additionalVehicle.getModel(), MODEL);
		assertEquals(additionalVehicle.getYear(), YEAR);
		assertEquals(additionalVehicle.getColor(), COLOR);
		assertEquals(additionalVehicle.getMileage(), MILEAGE);
	}
	
	@Test(expected = NullPointerException.class)
	public void additionalVehicleCreationNullMake() {
		new AdditionalVehicle(null, MODEL, YEAR, COLOR, MILEAGE);
	}
	
	@Test(expected = NullPointerException.class)
	public void additionalVehicleCreationNullModel() {
		new AdditionalVehicle(MAKE, null, YEAR, COLOR, MILEAGE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void additionalVehicleCreationInvalidYear() {
		new AdditionalVehicle(MAKE, MODEL, -1, COLOR, MILEAGE);
	}
	
	@Test(expected = NullPointerException.class)
	public void additionalVehicleCreationNullColor() {
		new AdditionalVehicle(MAKE, MODEL, YEAR, null, MILEAGE);
	}

	@Test(expected = NullPointerException.class)
	public void setMakeNull() {
		this.additionalVehicle.setMake(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void setModelNull() {
		this.additionalVehicle.setModel(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setYearInvalid() {
		this.additionalVehicle.setYear(-1);
	}
	
	@Test(expected = NullPointerException.class)
	public void setColorNull() {
		this.additionalVehicle.setColor(null);
	}
}
