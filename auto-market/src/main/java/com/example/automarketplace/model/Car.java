package com.example.automarketplace.model;

public class Car {
    private Long id;
    private String make;
    private String model;
    private int year;
    private int price;
    private String imageUrl;
    private String location;
    private Integer mileage; // пробіг

    public Car() {}

    public Car(Long id, String make, String model, int year, int price, String imageUrl, String location, Integer mileage) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.imageUrl = imageUrl;
        this.location = location;
        this.mileage = mileage;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Integer getMileage() { return mileage; }
    public void setMileage(Integer mileage) { this.mileage = mileage; }
}
