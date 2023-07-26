package lecture14;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jshell.execution.Util;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class ExerciseTests {
    private WebDriver driver;

    /**
     * Setup all browser drivers before the test suite execution
     * This setup will be executed only once for the whole test run
     */
    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    /**
     * Opens a new window in the browser before the execution of each test method
     * Example: If you have 2 methods: method1 & method2. The execution will be:
     * 1. setUpTest + method1
     * 2. setUpTest + method2
     */
    @BeforeMethod
    protected final void setUpTest() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();

        //Sets timeout to wait for a page to load completely. Works only with get() and navigate().to()
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        /*
        Sets timeout to wait for any element to appear in the DOM tree
        Example:
        If you execute findElement() the driver will be checking for the element to appear in the DOM tree for the duration of 5 seconds
        If element the element is found within the 5 seconds the test execution continues
        Otherwise if the element is not found NoSuchElementException will be thrown
        Important:
        If the element is presented in the DOM tree this doesn't guarantee that the element is visible on the page!
        The implicit wait applies for all elements during the driver's lifecycle
         */
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    /**
     * After each test closes the current browser window
     * Example: If you have 2 methods: method1 & method2. The execution will be:
     * 1. setUpTest + method1 + tearDownTest
     * 2. setUpTest + method2 + tearDownTest
     */
    @AfterMethod
    protected final void tearDownTest() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{{"DimitarTarkalanov", "Dimitar1.Tarkalanov1", "DimitarTarkalanov"}, //login with username
                {"testMail1@gmail.com", "Dimitar1.Tarkalanov1", "DimitarTarkalanov"}, //login with email
                {"testAdmin@gmail.com", "Admin1.User1", "AdminUser"}, //login with admin user
                {"manager@gmail.com", "Manager1.Use1", "ManagerUser"} //login with manager user
        };
    }

//    demo invocationCount = 10
    @Test(dataProvider = "getUsers")
    public void testLoginWithWaits(String user, String password, String name) {
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        /*
        Replaces
        String expectedLoginPageUrl = "http://training.skillo-bg.com:4300/users/login";
        String actualLoginPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualLoginPageUrl, expectedLoginPageUrl, "Login page URL is incorrect!");
        */
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        /*
        Replaces
        Assert.assertTrue(signinelement.isdisplayed(), "the 'sign in' text is not displayed!");
         */
        WebElement signInElement = driver.findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));

        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys(user);

        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(password);

        /*
        Replaces
        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        Assert.assertTrue(signInButton.isEnabled(), "The Sign In button is disabled!");
        signInButton.click();
        */
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();

        /*
        Replaces
        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        Assert.assertTrue(profileLink.isDisplayed(), "The profile link is not displayed!");
        */
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-profile")));
        profileLink.click();

        /*
        Replaces
        String actualProfilePageUrl = driver.getCurrentUrl();
        String expectedProfilePage = "http://training.skillo-bg.com:4300/users/3905";
        Assert.assertEquals(actualProfilePageUrl, expectedProfilePage, "The profile page url is incorrect!");
         */
        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));

        /*
        Replaces
        WebElement userNameElement = driver.findElement(By.tagName("h2"));
        String actualUserName = userNameElement.getText();
        String expectedUserName = "DimitarTarkalanov";
        Assert.assertEquals(actualUserName, expectedUserName, "The user name is incorrect!");
         */
        Boolean isTextDisplayed = wait.until(ExpectedConditions.textToBe(By.tagName("h2"), name));
        Assert.assertTrue(isTextDisplayed, "The username is not displayed!");
    }

    @Test
    public void testRegistration() {
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement signInElement = driver.findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));

        WebElement registerLink = driver.findElement(By.linkText("Register"));
        registerLink.click();

        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/register"));

        WebElement signUpLabel = driver.findElement(By.xpath("//*[text()='Sign up']"));
        wait.until(ExpectedConditions.visibilityOf(signUpLabel));

        WebElement userNameField = driver.findElement(By.name("username"));
        String username = generateRandomAlphabeticString(5, 8);
        userNameField.sendKeys(username);

        WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        String email = generateRandomEmail(5, 10);
        emailField.sendKeys(email);

        WebElement dateElement = driver.findElement(By.xpath("//input[@formcontrolname='birthDate']"));
        dateElement.sendKeys("10022000");

        WebElement passwordField = driver.findElement(By.xpath("//input[@id='defaultRegisterFormPassword']"));
        passwordField.sendKeys("Bbc123456");

        WebElement passwordConfirmationField = driver.findElement(By.xpath("//input[@id='defaultRegisterPhonePassword']"));
        passwordConfirmationField.sendKeys("Aa123456");

        WebElement publicInfoField = driver.findElement(By.xpath("//textarea[@placeholder='Public info']"));
        publicInfoField.sendKeys("Test");

        WebElement signInButton = driver.findElement(By.xpath("//button[@id='sign-in-button']"));
        signInButton.click();

        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-profile")));
        profileLink.click();

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));

        WebElement userNameLabelInProfile = driver.findElement(By.tagName("h2"));
        Assert.assertTrue(userNameLabelInProfile.isDisplayed());

    }

    private String generateRandomEmail(int minLengthInclusive, int maxLengthInclusive) {
        return generateRandomAlphabeticString(minLengthInclusive, maxLengthInclusive) + "@gmail.com";
    }

    private String generateRandomAlphabeticString(int minLengthInclusive, int maxLengthInclusive) {
        return RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthInclusive);
    }

//    public String randomIdentifier() {
//        Random rand = new Random();
//        Set<String> words = new HashSet<String>();
//        while(((HashSet<?>) words).size() < 10000)
//            words.add(Long.toString(Math.abs(rand.nextLong() % 3656158440062976L), 36));
//        return randomIdentifier();
//        }


//    public static String generateRandomName(){
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        String randomString="";
//        int length = 5;
//
//        Random rand = new Random();
//
//        char[] text = new char[length];
//
//        for(int i=0; i< length; i++){
//            text[i]=characters.charAt(rand.nextInt();
//        }
//    }


//    public String getSaltString() {
//        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        StringBuilder salt = new StringBuilder();
//        Random rnd = new Random();
//        while (salt.length() < 18) { // length of the random string.
//            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
//            salt.append(SALTCHARS.charAt(index));
//        }
//        String saltStr = salt.toString();
//        return saltStr;
//
//    }

//    public static String generateEmail() {
//        return UUID.randomUUID().toString() + "@gmail.com";
//    }
}
