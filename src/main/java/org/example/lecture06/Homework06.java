package org.example.lecture06;

public class Homework06 {
    public static void main(String[] args){
        String palindrome= "7aa7";
        System.out.println("The word is palindrome: " + isWordPalindorme(palindrome));

    }

    static boolean isWordPalindorme(String palindrome){
        boolean isPalindrome = false;
        String reveredWord = "";

        for (int i= palindrome.length()-1;i >=0; i--){
           reveredWord= reveredWord + palindrome.charAt(i);

        }
      if (palindrome.equals(reveredWord)){
          isPalindrome= true;

      }
      return isPalindrome;
    }

    public static double findSmallestNumber(double num1, double num2, double num3) {
        double smallestNumber = num1;

        if (smallestNumber > num2) {
            smallestNumber = num2;
        }

        if (smallestNumber > num3) {
            smallestNumber = num3;
        }

        return smallestNumber;
    }

    public static double computeAverageNumber(double num1, double num2, double num3) {
        return (num1 + num2 + num3) / 3;
    }


    public static void displayMiddleeChar(String value) {
        int position;
        int length;

        //If the length of the string is even there will be two middle characters.
        if (value.length() % 2 == 0) {
            position = value.length() / 2 - 1;
            length = 2;

        } else {
            //If the length of the string is odd there will be one middle character.
            position = value.length() / 2;
            length = 1;
        }

        System.out.println(value.substring(position, position + length));
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }
}
