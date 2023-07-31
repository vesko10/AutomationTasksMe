package org.example.lecture16.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModifyProfileForm {
    private final WebDriver driver;

    public ModifyProfileForm(WebDriver driver) {
        this.driver = driver;
    }

    public String getModifyYourProfileText() {
        WebElement modifyYourProfileLabel = driver.findElement(By.tagName("h4"));
        return modifyYourProfileLabel.getText();
    }

    public void enterModifiedUserName(String modifiedUsername) {
        WebElement modifyUserNameField = driver.findElement(By.xpath("//input[@formcontrolname='username']"));
        modifyUserNameField.clear();
        modifyUserNameField.sendKeys(modifiedUsername);
    }

    public void enterModifiedUserEmail() {
        String newModifiedUserEmail = "test1234898@gmail.com";
        WebElement modifyEmailField = driver.findElement(By.xpath("//input[@formcontrolname='email']"));
        modifyEmailField.clear();
        modifyEmailField.sendKeys(newModifiedUserEmail);
    }

    public void enterModifiedPassword() {
        String newModifiedPassword = "Abc123456";
        WebElement modifyPasswordField = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
        modifyPasswordField.sendKeys(newModifiedPassword);
    }

    public void confirmModifiedPassword() {
        String confirmPassword = "Abc123456";
        WebElement confirmPasswordField = driver.findElement(By.xpath("//input[@formcontrolname='confirmPassword']"));
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void enterModifiedPublicInfo() {
        WebElement publicInfoEditField = driver.findElement(By.xpath("//textarea[@formcontrolname='publicInfo']"));
        publicInfoEditField.clear();
        publicInfoEditField.sendKeys("New modified Public Info");
    }

    public void clickSaveButton(){
        WebElement saveButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        saveButton.click();
    }

//    public boolean isSaveButtonDisplayed(){
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        WebElement saveButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
//        return isSaveButtonDisplayed();
//    }
}
