package org.example.lecture07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Car car = new Car("V6523VH", "Audi");
       try {
           testThrowsException();
       }catch (FileNotFoundException e){
           System.out.println("Caught FileNotFound exception");
       }
           }

   // static void testCheckedExceptions() throws FileNotFoundException {

       //File file = new File("src/lecture07/testFile.txt");
        //FileReader fr = new FileReader(file);
    //}

    static void testUncheckedException(){
        int[] num = {1,2,3,4};
        System.out.println(num[5]);
    }

    static void testTryCatch(String path){
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found! Please check the file name");
        }
    }

    static void testTryCatchMultipleExceptions(int number, int factor){
        try {
            if (number%factor==0){
                System.out.println(factor + " is a factor of " + number);
            }
        } catch(ArithmeticException e){
            System.out.println("There is an arithmetic exception");
        } catch (NumberFormatException e){
            System.out.println("There is a format exception");
        }
    }

    static void testThrowsException() throws FileNotFoundException {
        File file = new File("src/lecture07/testFile");
        FileReader fr = new FileReader(file);
    }
}
