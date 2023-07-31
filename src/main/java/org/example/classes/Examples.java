package org.example.classes;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class Examples {

    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        commonCommands(driver);

    }

    private static void testMyFirstWebDriver(ChromeDriver driver){
        driver.get("http://training.skillo-bg.com:4300/posts/all");
    }

    private static void testFindElement(ChromeDriver driver){
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));

        WebElement homeLink =driver.findElement(By.linkText("Home"));
    }

    private static void testFindElements(ChromeDriver driver){
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));

        List<WebElement> images = driver.findElements(By.xpath("//img[@src='https://i.imgur.com/oBi5kct.jpg']"));
    }

    private static void testClick(ChromeDriver driver){
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

    }

    private static void enterUserName(ChromeDriver driver){
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();
        WebElement userName = driver.findElement(By.id("defaultLoginFormUsername"));
        userName.sendKeys("Veselin");


    }

    private static void clearUserName(ChromeDriver driver){
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();
        WebElement userName = driver.findElement(By.id("defaultLoginFormUsername"));
        userName.sendKeys("Veselin");
        userName.clear();
        userName.sendKeys("Petar");

    }

    private static void commonCommands(ChromeDriver driver){
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        String titleOfPage = driver.getTitle();
        System.out.println("Title is: " + titleOfPage);

        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();
        WebElement rememberMe = driver.findElement(By.xpath("//input[@formcontrolname='rememberMe']"));
        rememberMe.click();
        System.out.println("Check box selected: " + rememberMe.isSelected());

        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        String buttonText = signInButton.getText();
        System.out.println("The text next to the element is: " + buttonText);

        System.out.println("The element is displayed: " + signInButton.isDisplayed());


    }

    private static void testDropDown(ChromeDriver driver) {
        driver.get("https://www.mobile.bg/pcqi/mobile.cqi");

        Select dropdownMarka = new Select(driver.findElement(By.name("marka")));
        dropdownMarka.selectByVisibleText("Volvo");
    }
}