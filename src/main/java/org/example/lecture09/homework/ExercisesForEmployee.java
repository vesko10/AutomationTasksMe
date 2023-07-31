package org.example.lecture09.homework;

public class ExercisesForEmployee {
    public static void main(String[] args) {
       /* Employee employee = new Employee(1, "Veselin", "Vlahov", 1563.25);
        System.out.println(employee.getName());
        System.out.println(employee.getAnnualSalary());
        System.out.println("New salary:" + employee.raiseSalary(10));
        System.out.println(employee.toString());

        Employee ivan = new Employee(1, "Veselin", "Ivanov", 2563.29);
        System.out.println(ivan.getName());
        System.out.println(ivan.getAnnualSalary());
        System.out.println(ivan.toString());

    }*/
        Bulgarian bulgarian = new Bulgarian("Veselin Vlahov","Male", "Orthodox", true, "8308273460", "Bulgaria");
        bulgarian.sayHello();

        Italian italian = new Italian("Nico Barella","Male", "Orthodox", true, "8508273460", "Italy");
        italian.sayHello();

        American american = new American("John Smith","Male", "Orthodox", true, "8208273460", "USA");
        american.sayHello();

        bulgarian.danceRuchenica();
        italian.danceTarantella();
        american.danceRap();


    }
}
