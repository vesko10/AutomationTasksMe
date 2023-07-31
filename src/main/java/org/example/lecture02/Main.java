package org.example.lecture02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       /*  Scanner input = new Scanner (System.in);
        System.out.print("Input your first name: ");
        String fname = input.next();
        System.out.print("Input your second name: ");
        String sname = input.next();
        System.out.print("Input your last name: ");
        String lname = input.next();
        System.out.println();
        System.out.println("Hello"+ " " + fname+" "+sname+" "+lname);*/
        triangleTask();

    }

    public static void printName() {
        String firstName = "Veselin";
        String middleName = "Atanasov";
        String lastName = "Vlahov";
        System.out.println("My name is: " + firstName + " " + middleName + " " + lastName);
    }

    public static void printTrianglePerimeter() {
        float a = 3.5F;
        float b = 5.5F;
        float c = 5.5F;

        float perimeter = a + b + c;

        System.out.println("Triangle Perimeter is: " + perimeter);


    }

    public static void printTriangleArea() {
        float b = 5.1F;
        float h = 2.4F;

        float triangleArea = (b * h) / 2;

        System.out.println("The area of triangle is: " + triangleArea);
    }

    public static void printPineTree() {
        char asterisk = '*';
        System.out.println("     " + asterisk);
        System.out.println("    " + asterisk + " " + asterisk);
        System.out.println("   " + asterisk + " " + asterisk + " " + asterisk);

    }

    public static void printTriangleOfPerimeterInput() {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Please enter side A");
        float a = myObj.nextFloat();

        System.out.println("Please enter side B");
        float b = myObj.nextFloat();

        System.out.println("Please enter side C");
        float c = myObj.nextFloat();

        float perimeter = a + b + c;

        System.out.println("Perimeter of Triangle is: " + perimeter);
    }

    public static void printTriangleOfAreaInput() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please enter side A");
        float a = myObj.nextFloat();

        System.out.println("Please enter side H");
        float h = myObj.nextFloat();

        float area = (a * h) / 2;

        System.out.println("Area of the triangle is: " + area);
    }

    public static void convertMinutesToYearsDays() {
        byte minutesInHour = 60;
        byte hoursInDay = 24;
        short daysInYear = 365;
        int minutesInYear = minutesInHour * hoursInDay * daysInYear;

        Scanner input = new Scanner(System.in);

        System.out.print("Input the number of minutes: ");

        long min = input.nextLong();

        long years = (min / minutesInYear);
        long days = (min / minutesInHour / hoursInDay) % daysInYear;

        System.out.println(min + " minutes is approximately " + years + " years and " + days + " days");
    }

    public static void logicalOperatorDemo() {
        boolean b1 = true;
        boolean b2 = false;

        System.out.println("b1 && b2 " + (b1 && b2));
    }

    public static void ternaryOperators() {
        int num1, num2;
        num1 = 25;

        num2 = (num1 == 10) ? 100 : 200;

        System.out.println("num2 " + num2);
    }

    public static void ifStatementExample() {
        int num = 70;
        if (num > 100) {
            System.out.println("number is less than 100");
            if (num < 150) {
                System.out.println("number is less than 150");
            }
        }
    }

    public static void ifElseStatement() {
        int num = 70;
        if (num < 50) {
            System.out.println("number is less than 50");
        } else {
            System.out.println("number is greater or equal to 150");
        }
    }

    public static void IfElseIFStatement() {
        int num = 123;
        if (num < 100 && num >= 1) {
            System.out.println("it is two digits number");
        } else if (num < 1000 && num >= 100) {
            System.out.println("it is three digits number");
        } else if (num < 10000 && num >= 1000) {
            System.out.println("it is four digits number");
        } else {
            System.out.println("it is not between that");
        }
    }

    public static void switchCaseExample1() {
        int i = 3;
        switch (i) {
            case 1:
                System.out.println("Case 1");
                break;
            case 2:
                System.out.println("Case 2");
                break;
            case 3:
                System.out.println("Case 3");
                break;
            default:
                System.out.println("Default");
        }

    }

    public static void testForLoop() {
        for (int i = 10; i > 1; i--) {
            System.out.println("The value of i is " + i);
        }

    }

    public static void testForArray() {
        int arr[] = {2, 11, 45, 9};
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void ageCheck() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your age: ");
        int age = input.nextInt();
        if (age >= 16)
            System.out.print("You are eligible to work: ");
        else {
            System.out.println("You are not eligible to work: ");
        }

    }

    public static void calculateRevenue() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter unit price: ");
        float unitprice = input.nextInt();
        System.out.print("Please enter quantity: ");
        int quantity = input.nextInt();
        double revenue = 0;
        double discountRate = 0;
        double discountAmount = 0;
        if (quantity < 100)
            revenue = unitprice * quantity;
        else if (quantity >= 100 && quantity <= 120) {
            discountRate = (double) 15 / 100;
            revenue = unitprice * quantity;
            discountAmount = revenue * discountRate;
            revenue = revenue - discountAmount;
        } else if (quantity > 120) {
            discountRate = (double) 20 / 100;
            revenue = unitprice * quantity;
            discountAmount = revenue * discountRate;
            revenue = revenue - discountAmount;
        }
        System.out.println("The revenue from sale: " + revenue + "$");

        System.out.println("Discount: " + discountAmount + "$(" + discountRate * 100 + "%)");


    }

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
}

