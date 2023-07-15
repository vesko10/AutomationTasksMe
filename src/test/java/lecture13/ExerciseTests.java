package lecture13;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ExerciseTests {
    @Test(invocationCount = 5)
    public void testLogin() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

//        String expectedLoginPageUrl = "http://training.skillo-bg.com:4300/users/login";
//        String actualLoginPageUrl = driver.getCurrentUrl();
//        Assert.assertEquals(actualLoginPageUrl, expectedLoginPageUrl, "Login page URL is incorrect!");

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));


        WebElement signInElement = driver.findElement(By.xpath("//p[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));
//      Assert.assertTrue(signInElement.isDisplayed(), "The 'Sign in' text is not displayed!");

        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys("testMail1@gmail.com");

        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys("Dimitar1.Tarkalanov1");

        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        Assert.assertTrue(signInButton.isEnabled(), "The Sign In button is disabled!");
        signInButton.click();

        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        Assert.assertTrue(profileLink.isDisplayed(), "The profile link is not displayed!");

        profileLink.click();

//        String actualProfilePageUrl = driver.getCurrentUrl();
//        String expectedProfilePage = "http://training.skillo-bg.com:4300/users/3905";
//        Assert.assertEquals(actualProfilePageUrl, expectedProfilePage, "The profile page url is incorrect!");

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));

//        WebElement userNameElement = driver.findElement(By.tagName("h2"));
//        String actualUserName = userNameElement.getText();
//        String expectedUserName = "DimitarTarkalanov";
        Boolean IsUserNameDisplayed = wait.until(ExpectedConditions.textToBe(By.tagName("h2"),"DimitarTarkalanov"));
        Assert.assertTrue(IsUserNameDisplayed, "The user name is not displayed!");
//        Assert.assertEquals(actualUserName, expectedUserName, "The user name is incorrect!");

        driver.close();
    }
}
