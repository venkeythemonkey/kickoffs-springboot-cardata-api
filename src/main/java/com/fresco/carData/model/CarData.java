package com.fresco.carData.model;


import jakarta.persistence.*;

@Entity
public class CarData {

    @Id // primary
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto generate
    private int id;

    private String model;

    private int manufacturedYear;

    private String company;

    private String bodyType;

    private float price;

    private String fuelType;

    private int seatingCapacity;

    //Constructors
    public CarData() {
    }

    public CarData(int id, String model, int manufacturedYear, String company, String bodyType, String fuelType, int seatingCapacity) {
        this.id = id;
        this.model = model;
        this.manufacturedYear = manufacturedYear;
        this.company = company;
        this.bodyType = bodyType;
        this.fuelType = fuelType;
        this.seatingCapacity = seatingCapacity;
    }

    public CarData(int id, String model, int manufacturedYear, String company, String bodyType, float price, String fuelType, int seatingCapacity) {
        this.id = id;
        this.model = model;
        this.manufacturedYear = manufacturedYear;
        this.company = company;
        this.bodyType = bodyType;
        this.price = price;
        this.fuelType = fuelType;
        this.seatingCapacity = seatingCapacity;
    }
//Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufacturedYear() {
        return manufacturedYear;
    }

    public void setManufacturedYear(int manufacturedYear) {
        this.manufacturedYear = manufacturedYear;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }



    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public String toString() {
        return "CarData{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", manufacturedYear=" + manufacturedYear +
                ", company='" + company + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", price=" + price +
                ", fuelType='" + fuelType + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                '}';
    }
}
