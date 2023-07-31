package org.example.lecture08.employee;

public class Mainn {
    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println("Animal actions");
        animal.animalSound();

        System.out.println("Dog actions");
        Animal dog = new Dog();
        dog.animalSound();

        System.out.println("Pig actions");
        Animal pig = new Pig();
        pig.animalSound();



    }
    }
