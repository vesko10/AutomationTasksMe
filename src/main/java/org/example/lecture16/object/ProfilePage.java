package org.example.lecture16.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users"));
    }

    public String getUsername(){
        WebElement username = driver.findElement(By.tagName("h2"));
        return username.getText();
    }

    public void clickEditProfileButton(){
        WebElement editProfileButton = driver.findElement(By.xpath("//i[@class='fas fa-user-edit ng-star-inserted'][last()]"));
        editProfileButton.click();
    }

    public String getUModifiedsername(){
        WebElement modifiedUsername = driver.findElement(By.tagName("h2"));
        return modifiedUsername.getText();
    }
}
