package com.searshc.mygarage.dtos.carprofile;

/**
 *
 * @author rammel.maestre
 */
public class VehicleDTO {

    private String name;
    private int year;
    private String make;
    private String model;
    private String engine;
    private String type;
    private String color;

    private double mileage;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the engine
     */
    public String getEngine() {
        return engine;
    }

    /**
     * @param engine the engine to set
     */
    public void setEngine(String engine) {
        this.engine = engine;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the mileage
     */
    public double getMileage() {
        return mileage;
    }

    /**
     * @param mileage the mileage to set
     */
    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

}
