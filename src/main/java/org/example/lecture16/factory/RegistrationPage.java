package org.example.lecture16.factory;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//*[text()='Sign up']")
    private WebElement signUpFormTitle;
    @FindBy(name = "username")
    private WebElement userNameField;
    @FindBy(css = "[type='email']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@formcontrolname='birthDate']")
    private WebElement dateElement;
    @FindBy(xpath = "//input[@id='defaultRegisterFormPassword']")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@id='defaultRegisterPhonePassword']")
    private WebElement passwordConfirmationField;
    @FindBy(xpath = "//textarea[@placeholder='Public info']")
    private WebElement publicInfoField;
    @FindBy(xpath = "//button[@id='sign-in-button']")
    private WebElement signInButton;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public boolean isUrlForRegistrationLoaded(){
        return wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/register"));
    }

    public String getSingUpElementText(){
        wait.until(ExpectedConditions.visibilityOf(signUpFormTitle));
        return signUpFormTitle.getText();
    }

    public void populateUsername(){
        String username = generateRandomAlphabeticString(5, 8);
        userNameField.sendKeys(username);
    }

    private String generateRandomAlphabeticString(int minLengthInclusive, int maxLengthInclusive) {
        return RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthInclusive);
    }

    public void populateEmail(){
        String email = generateRandomEmail(5, 10);
        emailField.sendKeys(email);
    }

    private String generateRandomEmail(int minLengthInclusive, int maxLengthInclusive) {
        return generateRandomAlphabeticString(minLengthInclusive, maxLengthInclusive) + "@gmail.com";
    }

    public void populateDateOfBirth(){
        dateElement.sendKeys("10022000");
    }

    public void populatePassword(String password1){
        passwordField.sendKeys(password1);
    }

    public void populatePassword2(String password2){
        passwordConfirmationField.sendKeys(password2);
    }
    public void populatePublicInfo(String publicInfo){
        publicInfoField.sendKeys(publicInfo);
    }

    public void clickSignInButton(){
        signInButton.click();
    }
}
