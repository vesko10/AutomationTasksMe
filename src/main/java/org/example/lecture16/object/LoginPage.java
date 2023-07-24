package org.example.lecture16.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private  WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
         this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isUrlLoaded(){
       return wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));
    }

    public String getSingInElementText(){
        WebElement signInFormTitle = driver.findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInFormTitle));
        return signInFormTitle.getText();

    }

    public void populateUsername(String username){
        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys(username);
    }

    public void populatePassword(String password){
        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(password);
    }
    public void clickSignIn(){
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();
    }

    public void clickRegisterLink(){
        WebElement registerLink = driver.findElement(By.linkText("Register"));
        registerLink.click();
    }
}
