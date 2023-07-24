package org.example.lecture16.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private  WebDriverWait wait;
    @FindBy(className = "h4")
    private WebElement signInFormTitle;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement userNameField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordField;
    @FindBy(id = "sign-in-button")
    private WebElement signInButton;
    @FindBy(linkText = "Register")
    private WebElement registerLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
         this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public boolean isUrlLoaded(){
       return wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));
    }

    public String getSingInElementText(){
        wait.until(ExpectedConditions.visibilityOf(signInFormTitle));
        return signInFormTitle.getText();
    }

    public void populateUsername(String username){
        userNameField.sendKeys(username);
    }

    public void populatePassword(String password){
        passwordField.sendKeys(password);
    }
    public void clickSignIn(){
        signInButton.click();
    }
    public void clickRegisterLink(){
        registerLink.click();
    }
}
