package org.example.lecture08.employee;

public class Car extends Vehicle {

int numberOfDoors;
private boolean doorsAreClosed = true;

    public void setDoorsAreClosed(boolean doorsAreClosed) {
        this.doorsAreClosed = doorsAreClosed;
    }

    public Car(String brand, String model, double length, double width, double weight, int horsePower, int maxSpeed, int numberOfDoors) {
        super(brand, model, length, width, weight, horsePower, maxSpeed);
        this.numberOfDoors = numberOfDoors;
        this.doorsAreClosed = doorsAreClosed;


    }

    public void closeAllDoors(){
        if(this.doorsAreClosed == true){
            System.out.println("Doors are closed");
        } else {
            System.out.println("Doors are opened");
        }
    }
}