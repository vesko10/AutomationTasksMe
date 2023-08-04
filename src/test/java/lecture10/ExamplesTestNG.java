package lecture10;

import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.*;

public class ExamplesTestNG {

    @Test(groups = "smoke set")
    public void test1(){
        int a=5;
        int b=3;
        int sum = a+b;
        System.out.println("Sum of A and B is: " + sum);
    }

    public Object[][] generateeNumberss() {
        return new Object[][]{{2, 3, 5},
                {5, 7, 9}};
    }
    @Test(dataProvider = "generateeNumberss")
    public void testDataParameters(int a, int b, int expectedSum) {
        int sum = a + b;
        Assert.assertEquals(expectedSum, sum);
    }

    public Object[][] generateeNumbersss() {
        return new Object[][]{{20, 3, 17},
                {15, 7, 8}};
    }
    @Test(dataProvider = "generateeNumbersss")
    public void testDataParameterss(int a, int b, int expectedSum) {
        int sum = a - b;
        Assert.assertEquals(expectedSum, sum);
    }

    public Object[][] generateeNumberssss() {
        return new Object[][]{{2, 3, 6},
                {5, 7, 35}};
    }
    @Test(dataProvider = "generateeNumbersss")
    public void testDataParametersss(int a, int b, int expectedSum) {
        int sum = a * b;
        Assert.assertEquals(expectedSum, sum);
    }

    public Object[][] generateeNumbersssss() {
        return new Object[][]{{12, 3, 4},
                {15, 5, 3}};
    }
    @Test(dataProvider = "generateeNumberssss")
    public void testDataParameterssss(int a, int b, int expectedSum) {
        int sum = a / b;
        Assert.assertEquals(expectedSum, sum);
    }

    public Object[][] generateeNumberssssss() {
        return new Object[][]{{12, 3, 0},
                {15, 5, 0}};
    }
    @Test(dataProvider = "generateeNumbersssss")
    public void testDataParametersssss(int a, int b, int expectedSum) {
        int sum = a % b;
        Assert.assertEquals(expectedSum, sum);
    }

    @Test(groups = "smoke set")
    public void test2(){
        int a=5;
        int b=3;
        int sum = a-b;
        System.out.println("A subtracted from B is: " + sum);
    }

    @Test(groups = "smoke set")
    public void test3(){
        int a=5;
        int b=3;
        int multi = a*b;
        System.out.println("Multiplication of A and B is: " + multi);
    }

    @Test(groups = "regression set")
    public void test4(){
        int a=6;
        int b=3;
        int division = a/b;
        System.out.println("Division of A and B is: " + division);
    }

    @Test(groups = "regression set")
    public void test5(){
        int a=4;
        int b=3;
        int result = a%b;
        System.out.println("Result of A % B is: " + result);
    }

    public Object[][] generateNumbers() {
        return new Object[][]{{2, 3, 5}, {5, 7, 12}};
    }
    @Test(dataProvider = "generateNumbers")
    public void testDataProviderMultipleParameters(int a, int b, int expectedSum) {
        int sum = a + b;

        Assert.assertEquals(sum, expectedSum);
    }

    public Object[][] generateNumberss() {
        return new Object[][]{{4, 3, 1}, {5, 7, -2}};
    }
    @Test(dataProvider = "generateeNumberss")
    public void testDataProviderMultipleParameterss(int a, int b, int expectedSum) {
        int expectedResult = a - b;

        Assert.assertEquals(expectedResult, expectedSum);
    }

    public Object[][] generateNumbersss() {
        return new Object[][]{{4, 3, 12}, {5, 7, 35}};
    }
    @Test(dataProvider = "generateNumbersss")
    public void testDataProviderMultipleParametersss(int a, int b, int expectedSum) {
        int expectedMultiplication = a * b;

        Assert.assertEquals(expectedMultiplication, expectedSum);
    }

    public Object[][] generateNumberssss() {
        return new Object[][]{{6, 3, 2}, {15, 5, 3}};
    }
    @Test(dataProvider = "generateNumberssss")
    public void testDataProviderMultipleParameterssss(int a, int b, int expectedSum) {
        int expectedDivision = a / b;

        Assert.assertEquals(expectedDivision, expectedSum);
    }

    public Object[][] generateNumbersssss() {
        return new Object[][]{{6, 3, 0}, {15, 5, 0}};
    }
    @Test(dataProvider = "generateNumbersssss")
    public void testDataProviderMultipleParametersssss(int a, int b, int expectedSum) {
        int expectedModule = a % b;


        Assert.assertEquals(expectedModule, expectedSum);

    }

    //Annotation practice (Life cycle of the TestNG)
    @BeforeSuite
    public void testBeforeSuiteee(){
        System.out.println("Before Suite");
    }

    @BeforeTest
    public void testBeforeTestttt(){
        System.out.println("Before test");
    }

    @BeforeClass
    public void testBeforeClasssss(){
        System.out.println("Before class");
    }

    @Test
    public void testNamee1(){
        System.out.println("In test1 method");
    }


    @AfterClass
    public void testAfterClasss(){
        System.out.println("After class");
    }

    @AfterTest
    public void testAfterTestt(){
        System.out.println("After test");
    }

    @AfterSuite
    public void testAfterSuitee(){
        System.out.println("After Suite");
    }



}