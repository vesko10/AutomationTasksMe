package org.example.lecture09.homework;

public class American extends Person{
    public American(String name, String sex, String religion, boolean hasJob, String egn, String country) {
        super(name,sex,religion,"American",hasJob,"USA" ,egn,country);
    }

    public void danceRap(){
        System.out.println("We are dancing rap!");
    }
}
