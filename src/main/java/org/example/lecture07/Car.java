package org.example.lecture07;

public class Car extends Vehicle {
    private String brand;

    public Car(String regNo, String brand) {
        super(regNo);
        this.brand = brand;
    }

    public  Car(String brand){
        this.brand=brand;
    }
}
