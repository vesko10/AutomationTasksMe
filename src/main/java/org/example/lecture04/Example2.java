package org.example.lecture04;

import java.util.*;

public class Example2 {
    static int number;

    public static void main(String[] args) {
        //double x = 3.6;
       // double y = 3;
       // double z = 4.5;
        //System.out.println(findAverageNumber(x,y,z));
        //displayMiddleCharacter("text:");
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter number ");
        int number = input.nextInt();
        if(isEven(number))
        {
            System.out.println("Number is even");
        }
        else
        {
            System.out.println("Number is odd");
        }
    }


    public static boolean isEven(int num){
        if(num% 2 ==0){
            return true;
        }
        else{
            return false;
        }
    }

    static void displayMiddleCharacter(String text){
        int textLength = text.length();
        int remainder = 0;

      if (textLength % 2 == 0){
        remainder = textLength/2;
          int priorLetter=remainder-1;
          System.out.println(String.valueOf(text.charAt(priorLetter))+String.valueOf(text.charAt(remainder)));
      }else{
          remainder=textLength/2;
          System.out.println(text.charAt(remainder));
      }



    }


    static double findAverageNumber(double num1, double num2, double num3 ){
       double sum =  num1 + num2 + num3;
       double average = sum/3;
       return average;
    }
    public static double findSmallestNumber(double num1, double num2, double num3){
        double smallestNumber = num1;

        if (smallestNumber > num2){
            smallestNumber = num2;
        }
        if (smallestNumber > num3){
            smallestNumber = num3;
        }
        return smallestNumber;
    }

    public static void buildTriangle() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter value for first angle");
        float firstAngle = scanner.nextFloat();

        System.out.println("Enter value for second angle");
        float secondAngle = scanner.nextFloat();

        System.out.println("Enter value for third angle");
        float thirdAngle = scanner.nextFloat();
        /*System.out.println("angle1" + firstAngle);
        System.out.println("angle2" + secondAngle);
        System.out.println("angle3" + thirdAngle);*/

        float sum = firstAngle + secondAngle + thirdAngle;

        if (sum != 180) {
            System.out.println("The triangle cannot be build!");
        } else if (firstAngle < 90 && secondAngle < 90 && thirdAngle < 90) {
            System.out.println("The triangle is acute");
        } else if (firstAngle == 90 || secondAngle == 90 || thirdAngle == 90) {
            System.out.println("The triangle is Right-angled");
        } else if (firstAngle > 90 || secondAngle > 90 || thirdAngle > 90) {
            System.out.println("The triangle is Obtuse");
        } else if (firstAngle==secondAngle || firstAngle==thirdAngle || secondAngle==thirdAngle) {
            System.out.println("The triangle is Isosceles");
        } else if (firstAngle == 60 && secondAngle == 60 && thirdAngle == 60) {
            System.out.println("The triangle is Equilateral");
        } else {
            System.out.println("The Triangle is Multifaceted");
        }

    }

    public static void testArrayDeclarationElementInit(){
        String[] carBrands = {"Audi", "BMW", "Volvo", "Mercedes"};
        int[] evenNumbers = {2,4,6,8,10};
        double[] degrees = {0, 0.5, 1, 1.5, 2, 2.5};

        System.out.println(carBrands[0]);
        System.out.println(carBrands[3]);
        System.out.println(evenNumbers[3]);
        System.out.println(degrees[5]);

        System.out.println(carBrands.length);

    }

    public static void testArrayDeclaration() {
        String[] carBrands = new String[4];
        carBrands[0]="Audi";
        carBrands[1]="Mercedes";
        carBrands[2]="Volvo";
        carBrands[3]="BMW";
        System.out.println(carBrands[0]);
    }

    public static void testArrayElementValue() {
        String[] carBrands = {"Audi", "BMW", "Volvo", "Mercedes"};
        carBrands[2]="Mazda";
        System.out.println(carBrands[2]);
    }

    public static void testArrayLoop() {
        String[] carBrands = {"Audi", "BMW", "Volvo", "Mercedes"};
        for (int i=0; i< carBrands.length; i++){
            System.out.println(carBrands[i]);
        }

    }

    public static void testArrayForEachLoop() {
        String[] carBrands = {"Audi", "BMW", "Volvo", "Mercedes"};
        for (String brand : carBrands){
            System.out.println(brand);
        }

    }

    public static void testMultidimensionalArray() {
        int[][] arr = new int [3][3];
        arr[0][0]=1;
        arr[0][1]=2;
        arr[0][2]=3;
        arr[1][0]=4;
        arr[1][1]=5;
        arr[1][2]=6;
        arr[2][0]=7;
        arr[2][1]=8;
        arr[2][2]=9;

        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length; j++){
                System.out.println(arr[i][j]);
            }
        }



    }

    public static void testArrayListDeclaration() {
        //String[] carBrands = new String[4];
        ArrayList<String> carBrands = new ArrayList<>();
        //carBrands[0] ="Audi";
        carBrands.add("Audi");
        carBrands.add("Mazda");
        carBrands.add("BMW");

        System.out.println(carBrands);
        System.out.println(carBrands.get(0));
    }

    public static void testArrayListOperations() {
        ArrayList<String> carBrands = new ArrayList<>();
        carBrands.add("Audi");
        carBrands.add("BMW");
        carBrands.add("Mazda");
        carBrands.add("Ford");

        carBrands.set(1, "Mercedes");
        carBrands.remove(1);
        System.out.println(carBrands);
        System.out.println(carBrands.get(1));
        carBrands.add(1,"BMW");
        System.out.println(carBrands);
        System.out.println(carBrands.get(3));

        for(String brand: carBrands){
            System.out.println(brand);
        }

        Collections.sort(carBrands);
        System.out.println(carBrands);
    }

    public static void testLinkedListInit() {
        LinkedList<String> carBrands = new LinkedList<>();
        carBrands.add("Audi");
        carBrands.add("BMW");
        carBrands.add("Mazda");
        carBrands.add("Ford");
        System.out.println(carBrands);
        System.out.println(carBrands.get(0));
    }

    public static void testLinkedListOperations() {
        LinkedList<String> carBrands = new LinkedList<>();
        carBrands.add("Audi");
        carBrands.addFirst("BMW");
        carBrands.addLast("Ford");
        carBrands.add(2, "Mazda");

        System.out.println(carBrands);
        System.out.println(carBrands.getFirst());
        System.out.println(carBrands.getLast());

        //carBrands.remove(); //removes first element
       // System.out.println(carBrands);

       // carBrands.removeFirst(); //removes first element
       // System.out.println(carBrands);

      //  carBrands.removeLast(); //removes first element
      //  System.out.println(carBrands);

        carBrands.removeAll(carBrands);
        System.out.println(carBrands);
        System.out.println(carBrands.size());
    }

    public static void testQueue() {
        Queue<Integer> queue = new PriorityQueue<>();  //new LinkedList<>();
        queue.add(10);
        queue.add(20);
        queue.add(15);

        int numElements = queue.size();
        System.out.println(numElements);

        System.out.println("Elements:" + queue);

        for (int i=0; i<numElements; i++) {
            System.out.println("Removed element:" + queue.poll());
        }
    }

    public static void testStack(){
        Stack<String> elements = new Stack<>();
        elements.push("Test1");
        elements.push("Test2");
        elements.push("Test3");
        elements.push("Test4");


        System.out.println(elements);
        System.out.println("removed: " + elements.pop());
        System.out.println(elements);

        elements.push("Test5");
        System.out.println(elements);
    }

    public static void arrayElementsSum() {
        int[] elementsNum = {2,4,6,8,10};
        int sum = 0;

        for (int i=0; i< elementsNum.length;i++){
            sum+=elementsNum[i];                                   //sum = sum + elementsNum[i];
        }
        System.out.println("The sum of the elements from the array is " + "" + sum);
    }

    public static void multidimensionalMatrixTwoD() {
        int[][] arr=new int[4][3];
        arr[0][0]=1;
        arr[0][1]=2;
        arr[0][2]=3;
        arr[1][0]=4;
        arr[1][1]=5;
        arr[1][2]=6;
        arr[2][0]=7;
        arr[2][1]=8;
        arr[2][2]=9;
        arr[3][0]=10;
        arr[3][1]=11;
        arr[3][2]=12;

        for (int i=0; i<4; i++){
            for(int j=0;j<3; j++){
                System.out.println(arr[i][j]);
            }
        }

    }

    public static void dynamicInputArray() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter rows : ");
        int rows = input.nextInt();
        System.out.print("Please enter columns : ");
        int columns=input.nextInt();


        int twoDMatrix[][] = new int[rows][columns];

        System.out.println("Enter array: ");

        for (int i=0; i<rows;i++)
        {
            for (int j=0; j<columns; j++)
            {
                twoDMatrix[i][j]=input.nextInt();
            }
        }
    }

    public static void biggestElementArray() {
        int[] elements= {2,4,6,8,10};
        int max = elements[0];
        for (int i = 0; i< elements.length; i++) {
            if(elements[i]>max)
                max=elements[i];
        }
        System.out.println("The biggest elements from the array is: " + "" +  max);
    }

    public static void printDivisibleNumbers() {
        int[] arr = {12,15,32,42,55,75,122,132,150,180,200};

        for (int i=0; i< arr.length; i++){
            if((arr[i]%5)==0 && arr[i]<=150){
                System.out.println(arr[i]);
            }
        }
    }

    static void printFirstName(String firstName){
        System.out.println("My first name is: " +firstName);
    }

    static void printPersonAge(String firstName, int age){
        System.out.println(firstName + " is " +age + " years old");
    }


}



