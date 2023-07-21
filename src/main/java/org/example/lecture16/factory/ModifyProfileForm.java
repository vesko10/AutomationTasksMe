package org.example.lecture16.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModifyProfileForm {
    private final WebDriver driver;
    @FindBy(tagName = "h4")
    private WebElement modifyYourProfileLabel;
    @FindBy(xpath = "//input[@formcontrolname='username']")
    private WebElement modifyUserNameField;
    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement modifyEmailField;
    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement modifyPasswordField;
    @FindBy(xpath = "//input[@formcontrolname='confirmPassword']")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//textarea[@formcontrolname='publicInfo']")
    private WebElement publicInfoEditField;
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement saveButton;

    public ModifyProfileForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public String getModifyYourProfileText() {
        return modifyYourProfileLabel.getText();
    }
    public void enterModifiedUserName(String modifiedUsername) {
        modifyUserNameField.clear();
        modifyUserNameField.sendKeys(modifiedUsername);
    }

    public void enterModifiedUserEmail() {
        String newModifiedUserEmail = "test1234898@gmail.com";
        modifyEmailField.clear();
        modifyEmailField.sendKeys(newModifiedUserEmail);
    }
    public void enterModifiedPassword() {
        String newModifiedPassword = "Abc123456";
        modifyPasswordField.sendKeys(newModifiedPassword);
    }

    public void confirmModifiedPassword() {
        String confirmPassword = "Abc123456";
        confirmPasswordField.sendKeys(confirmPassword);
    }
    public void enterModifiedPublicInfo() {
        publicInfoEditField.clear();
        publicInfoEditField.sendKeys("New modified Public Info");
    }
    public void clickSaveButton(){
        saveButton.click();
    }

//    public boolean isSaveButtonDisplayed(){
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        WebElement saveButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
//        return isSaveButtonDisplayed();
//    }
}
