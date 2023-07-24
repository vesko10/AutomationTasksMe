package org.example.lecture16.object;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isUrlForRegistrationLoaded(){
        return wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/register"));
    }

    public String getSingUpElementText(){
        WebElement signUpFormTitle = driver.findElement(By.xpath("//*[text()='Sign up']"));
        wait.until(ExpectedConditions.visibilityOf(signUpFormTitle));
        return signUpFormTitle.getText();
    }

    public void populateUsername(){
        WebElement userNameField = driver.findElement(By.name("username"));
        String username = generateRandomAlphabeticString(5, 8);
        userNameField.sendKeys(username);
    }

    private String generateRandomAlphabeticString(int minLengthInclusive, int maxLengthInclusive) {
        return RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthInclusive);
    }

    public void populateEmail(){
        WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        String email = generateRandomEmail(5, 10);
        emailField.sendKeys(email);
    }

    private String generateRandomEmail(int minLengthInclusive, int maxLengthInclusive) {
        return generateRandomAlphabeticString(minLengthInclusive, maxLengthInclusive) + "@gmail.com";
    }

    public void populateDateOfBirth(){
        WebElement dateElement = driver.findElement(By.xpath("//input[@formcontrolname='birthDate']"));
        dateElement.sendKeys("10022000");
    }

    public void populatePassword(String password1){
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='defaultRegisterFormPassword']"));
        passwordField.sendKeys(password1);
    }

    public void populatePassword2(String password2){
        WebElement passwordConfirmationField = driver.findElement(By.xpath("//input[@id='defaultRegisterPhonePassword']"));
        passwordConfirmationField.sendKeys(password2);
    }
    public void populatePublicInfo(String publicInfo){
        WebElement publicInfoField = driver.findElement(By.xpath("//textarea[@placeholder='Public info']"));
        publicInfoField.sendKeys(publicInfo);
    }

    public void clickSignInButton(){
        WebElement signInButton = driver.findElement(By.xpath("//button[@id='sign-in-button']"));
        signInButton.click();
    }

}
