package org.example.lecture07;

public class Employee {

    private String firstName;

    private String lastName;

    private int birtYear;

    public Employee(String firstName, String lastName, int birtYear) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.birtYear=birtYear;
    }

    public void printInfo(){
        System.out.println(firstName+lastName+birtYear);
    }
}
