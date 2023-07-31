package org.example.lecture09.homework;

import java.io.InvalidClassException;
import java.time.LocalDate;

public class Person {
    private final String name;
    private final String sex;
    private final String religion;
    private final String language;
    private boolean hasJob;
    private final String nationality;
    private String egn;
    private String birthDate;
    private int age;
    private String country;

    public Person(String name, String sex, String religion, String language, boolean hasJob, String nationality, String egn, String country) {
        this.name = name;
        this.sex = sex;
        this.religion = religion;
        this.language = language;
        this.hasJob = hasJob;
        this.nationality = nationality;
        setEgn(egn);
        setBirthDate(egn);
        this.country = country;
    }

    public void setAge(String egn) {
        String birthYear = "19" + egn.substring(0, 2);
        int currentYear = LocalDate.now().getYear();
        this.age = currentYear - Integer.parseInt(birthYear);
    }

    private void setBirthDate(String egn) {
        String month = egn.substring(2, 4);
        String date = egn.substring(4, 6);
        this.birthDate = month + "-" + date;
    }

    private void setEgn(String egn) {
        if (egn.length() == 10 && containsOnlyDigits(egn)) {
            if (containsOnlyDigits(egn)) {
                this.egn = egn;
            } else {
                throw new RuntimeException("The provided EGN is not valid!");
            }
        }

    }

    public void checkGender() {
        String gender = null;
        try {
            if ((!gender.equals("male")) || (!gender.equals("female"))) {
                throw new InvalidClassException("Gender can be only male or female");
            }
        } catch (InvalidClassException e) {
            System.err.println(e);
        }
    }


    private boolean containsOnlyDigits(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void sayHello() {
        System.out.println("Hello!");
    }

    public void celebrateEaster() {
        switch (religion) {
            case "Orthodox":
                System.out.println("My religion is Orthodox!");
                break;
            case "Catholic":
                System.out.println("My religion is Catholic!");
                break;
            default:
                System.out.println("Islam!");

        }

    }

    public boolean canTakeLoan(){

        if( hasJob= true ){
            System.out.println("You are eligible for a loan!");
        }
        else {
            System.out.println("You are not eligible for a loan!");
        }
        return false;
    }

}

