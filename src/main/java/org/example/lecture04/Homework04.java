package org.example.lecture04;

import java.util.Scanner;

public class Homework04 {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Input number: ");
        int day = in.nextInt();

        System.out.println(checkDayName(day));

    }


//zadacha 1
    public static String checkDayName (int day) {
        String nameOfTheDay = "";
        switch (day) {
            case 1:
                nameOfTheDay = "Monday";
                break;
            case 2:
                nameOfTheDay = "Tuesday";
                break;
            case 3:
                nameOfTheDay = "Wednesday";
                break;
            case 4:
                nameOfTheDay = "Thursday";
                break;
            case 5:
                nameOfTheDay = "Friday";
                break;
            case 6:
                nameOfTheDay = "Saturday";
            break;
            case 7:
                nameOfTheDay = "Sunday";
            break;
            default:
                nameOfTheDay = "Invalid day range";
        }

        return nameOfTheDay;
    }

    //zadacha 2
    public static void ageCheck() {
        Scanner input = new Scanner (System.in);
        System.out.print("Please enter your age: ");
        int age = input.nextInt();
        if(age>=16)
            System.out.print("You are eligible to work: ");
        else {
            System.out.println("You are not eligible to work: ");
        }

    }

    //Zadacha 3
    public static void calculateRevenue(){
        Scanner input = new Scanner (System.in);
        System.out.print("Please enter unit price: ");
        float unitprice = input.nextInt();
        System.out.print("Please enter quantity: ");
        int quantity = input.nextInt();
        double revenue=0;
        double discountRate=0;
        double discountAmount=0;
        if (quantity<100)
            revenue=unitprice*quantity;
        else if (quantity>=100 && quantity<=120) {
            discountRate = (double)15 / 100;
            revenue = unitprice * quantity;
            discountAmount = revenue * discountRate;
            revenue = revenue - discountAmount;
        } else if (quantity>120) {
            discountRate= (double)20/100;
            revenue=unitprice * quantity;
            discountAmount=revenue * discountRate;
            revenue= revenue - discountAmount;
        }
        System.out.println("The revenue from sale: " +revenue +"$");

        System.out.println("Discount: " +discountAmount+"$("+discountRate*100+"%)");


    }

    //zadacha za Triangle
    public static void triangleTask() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter first angle: ");
        int angle1 = input.nextInt();
        System.out.print("Please enter second angle: ");
        int angle2 = input.nextInt();
        System.out.print("Please enter third angle: ");
        int angle3 = input.nextInt();
        int sumOfAngles = angle1 + angle2 + angle3;
        if (sumOfAngles == 180 && angle1 > 0 && angle2 > 0 && angle3 > 0) {
            if (angle1 < 90 && angle2 < 90 && angle3 < 90) {
                System.out.println("This is Acute-angled Triangle");

            } else if (angle1 == 90 || angle2 == 90 || angle3 == 90) {
                System.out.println("This is Right-angled Triangle");
            }
            else {
                System.out.println("This is obtuse-angled Triangle ");
            }
        }
        else {
            System.out.println("The Triangle is not possible based on your input");
        }

    }

    public static void printLargestNumber() {
        int[] arr = {2, 11, 45, 9};
        //int[] arr = {-3, -5, -2, -4};
        //int[] arr = {-23, -1, -4, -5, 0};

        int largestNumber = arr[0];
        int i;

        // Traverse array elements from second and
        // compare every element with current max
        for (i = 1; i < arr.length; i++) {
            if (arr[i] > largestNumber) largestNumber = arr[i];
        }

        System.out.println("The largest number is: " + largestNumber);
    }

    public static void planVacation() {
        String vacationType;
        double budget;
        double dailyBudget;
        int people;
        int days;
        String bulgariaDestinationMessage = "Available destination: Bulgaria";
        String nonBulgariaDestinationMessage = "Available destination: Outside Bulgaria";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter vacation type:");
        vacationType = scanner.nextLine();

        System.out.println("Please enter number of days:");
        days = scanner.nextInt();

        System.out.println("Please enter number of people:");
        people = scanner.nextInt();

        System.out.println("Please enter your budget:");
        budget = scanner.nextDouble();

        dailyBudget = budget / (days * people);

        switch (vacationType) {
            case "Beach":
                if (dailyBudget < 50) {
                    System.out.println(bulgariaDestinationMessage);
                } else {
                    System.out.println(nonBulgariaDestinationMessage);
                }
                break;
            case "Mountain":
                if (dailyBudget < 30) {
                    System.out.println(bulgariaDestinationMessage);
                } else {
                    System.out.println(nonBulgariaDestinationMessage);
                }
                break;
            default:
                System.out.println("There is no information about this type of vacation!");
        }
    }

    public static void printDivisibleNumbers() {
        int[] list = {12, 15, 32, 42, 55, 75, 122, 132, 150, 180, 200};
        int num;

        for (int i = 0; i < list.length; i++) {
            num = list[i];
            if ((num % 5) == 0) {
                System.out.println(num);
                if (num > 150) {
                    break;
                }
            }
        }
    }

}




