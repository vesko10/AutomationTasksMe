package lecture15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Homework15 {
    private WebDriver driver;

    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTest() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    protected final void tearDownTest() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @Test
    public void testAddElement() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement addRemoveElementsLink = driver.findElement(By.xpath("//*[contains(text(),'Add/Remove Elements')]"));
        addRemoveElementsLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/add_remove_elements/"));

        WebElement addRemoveElementsTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(addRemoveElementsTitle));

        WebElement addElementButton = driver.findElement(By.xpath("//button[@onclick='addElement()']"));
        addElementButton.click();

        WebElement deleteButton = driver.findElement(By.xpath("//button[@onclick='deleteElement()']"));
        Assert.assertTrue(deleteButton.isDisplayed());
        Assert.assertTrue(addElementButton.isDisplayed());
        deleteButton.click();


        Assert.assertTrue(addElementButton.isDisplayed());

    }

    @Test
    public void testBasicAuth() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement basicAuthLink = driver.findElement(By.xpath("//*[contains(text(),'Basic Auth')]"));
        basicAuthLink.click();

        String name = "admin";
        String password = "admin";

        //Interact with PromptBox
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(name);
        alert.sendKeys(password);
        alert.accept();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/basic_auth"));

        WebElement basicAuthTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(basicAuthTitle));

        String actualText = driver.findElement(By.xpath("//div[@class='example']/p")).getText();
        String expectedText = "Congratulations! You must have the proper credentials.";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void testCheckBoxes() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement chechboxesLink = driver.findElement(By.xpath("//*[contains(text(),'Checkboxes')]"));
        chechboxesLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/checkboxes"));

        WebElement checkboxesTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(checkboxesTitle));

        WebElement checkbox2 = driver.findElement(By.xpath("(//form[@id='checkboxes']/input)[2]"));
        WebElement checkbox1 = driver.findElement(By.xpath("(//form[@id='checkboxes']/input)[1]"));
        Assert.assertTrue(checkbox2.isSelected());
        Assert.assertFalse(checkbox1.isSelected());

        checkbox2.click();
        Assert.assertFalse(checkbox1.isSelected());
        Assert.assertFalse(checkbox2.isSelected());

        checkbox1.click();
        Assert.assertTrue(checkbox1.isSelected());
        Assert.assertFalse(checkbox2.isSelected());

        checkbox2.click();
        Assert.assertTrue(checkbox1.isSelected());
        Assert.assertTrue(checkbox2.isSelected());

        checkbox1.click();
        Assert.assertFalse(checkbox1.isSelected());
        Assert.assertTrue(checkbox2.isSelected());

    }

    @Test
    public void testContextMenu() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement contextMenuLink = driver.findElement(By.xpath("//*[contains(text(),'Context Menu')]"));
        contextMenuLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/context_menu"));

        WebElement contextMenuTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(contextMenuTitle));

        String actualText1 = driver.findElement(By.xpath("(//div[@class='example']/p)[1]")).getText();
        String expectedText1 = "Context menu items are custom additions that appear in the right-click menu.";
        Assert.assertEquals(actualText1, expectedText1);

        String actualText2 = driver.findElement(By.xpath("(//div[@class='example']/p)[2]")).getText();
        String expectedText2 = "Right-click in the box below to see one called 'the-internet'. When you click it, it will trigger a JavaScript alert.";
        Assert.assertEquals(actualText2, expectedText2);

        Actions actions = new Actions(driver);
        WebElement elementLocator = driver.findElement(By.id("hot-spot"));
        actions.contextClick(elementLocator).perform();

        Alert alert = driver.switchTo().alert();

        String actualAlertText = alert.getText();
        Assert.assertEquals(actualAlertText, "You selected a context menu");
        alert.accept();

        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/context_menu"));

        wait.until(ExpectedConditions.visibilityOf(contextMenuTitle));

        Assert.assertEquals(actualText1, expectedText1);
        Assert.assertEquals(actualText2, expectedText2);
    }

    @Test
    public void testDisappearingElementsWithHomeButtonCheck() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement disappearingElementsLink = driver.findElement(By.xpath("//*[contains(text(),'Disappearing Elements')]"));
        disappearingElementsLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/disappearing_elements"));

        WebElement disappearingElementsTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(disappearingElementsTitle));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://the-internet.herokuapp.com/disappearing_elements");

        String actualText = driver.findElement(By.xpath("//div[@class='example']/p")).getText();
        String expectedText = "This example demonstrates when elements on a page change by disappearing/reappearing on each page load.";
        Assert.assertEquals(actualText, expectedText);

        WebElement homeButton = driver.findElement(By.xpath("//*[contains(text(),'Home')]"));
        Assert.assertTrue(homeButton.isDisplayed());

        WebElement aboutButton = driver.findElement(By.xpath("//*[contains(text(),'About')]"));
        Assert.assertTrue(aboutButton.isDisplayed());

        WebElement contactUsButton = driver.findElement(By.xpath("//*[contains(text(),'Contact Us')]"));
        Assert.assertTrue(contactUsButton.isDisplayed());

        WebElement portfolioButton = driver.findElement(By.xpath("//*[contains(text(),'Portfolio')]"));
        Assert.assertTrue(portfolioButton.isDisplayed());

//        WebElement galleryButton = driver.findElement(By.xpath("//*[contains(text(),'Gallery')]"));
//        Assert.assertTrue(galleryButton.isDisplayed());

        homeButton.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/"));

        WebElement welcomeToTheInternetTitle = driver.findElement(By.tagName("h1"));
        wait.until(ExpectedConditions.visibilityOf(welcomeToTheInternetTitle));

        WebElement availableExamples = driver.findElement(By.tagName("h2"));
        wait.until(ExpectedConditions.visibilityOf(availableExamples));
    }


    @Test
    public void testAboutDisappearingElement() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement disappearingElementsLink = driver.findElement(By.xpath("//*[contains(text(),'Disappearing Elements')]"));
        disappearingElementsLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/disappearing_elements"));

        WebElement disappearingElementsTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(disappearingElementsTitle));

        WebElement aboutButton = driver.findElement(By.xpath("//*[contains(text(),'About')]"));
        Assert.assertTrue(aboutButton.isDisplayed());

        aboutButton.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/about/"));

        WebElement notFoundTitle = driver.findElement(By.tagName("h1"));
        wait.until(ExpectedConditions.visibilityOf(notFoundTitle));

    }

    @Test
    public void testContactUsDisappearingElement() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement disappearingElementsLink = driver.findElement(By.xpath("//*[contains(text(),'Disappearing Elements')]"));
        disappearingElementsLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/disappearing_elements"));

        WebElement disappearingElementsTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(disappearingElementsTitle));

        WebElement contactUsButton = driver.findElement(By.xpath("//*[contains(text(),'Contact Us')]"));
        Assert.assertTrue(contactUsButton.isDisplayed());

        contactUsButton.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/contact-us/"));

        WebElement notFoundTitle = driver.findElement(By.tagName("h1"));
        wait.until(ExpectedConditions.visibilityOf(notFoundTitle));
    }

    @Test
    public void testPortfolioDisappearingElement() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement disappearingElementsLink = driver.findElement(By.xpath("//*[contains(text(),'Disappearing Elements')]"));
        disappearingElementsLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/disappearing_elements"));

        WebElement disappearingElementsTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(disappearingElementsTitle));

        WebElement portfolioButton = driver.findElement(By.xpath("//*[contains(text(),'Portfolio')]"));
        Assert.assertTrue(portfolioButton.isDisplayed());

        portfolioButton.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/portfolio/"));

        WebElement notFoundTitle = driver.findElement(By.tagName("h1"));
        wait.until(ExpectedConditions.visibilityOf(notFoundTitle));
    }

    @Test
    public void testDragAndDrop() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement disappearingElementsLink = driver.findElement(By.xpath("//*[contains(text(),'Drag and Drop')]"));
        disappearingElementsLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/drag_and_drop"));

        WebElement disappearingElementsTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(disappearingElementsTitle));

        WebElement elementA = driver.findElement(By.xpath("//div[@id='column-a']"));
        Assert.assertTrue(elementA.isDisplayed());

        WebElement elementB = driver.findElement(By.xpath("//div[@id='column-b']"));
        Assert.assertTrue(elementB.isDisplayed());

        Actions act = new Actions(driver);
        act.dragAndDrop(elementB, elementA).build().perform();


    }

    @Test
    public void testDropDown() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement dropDownLink = driver.findElement(By.xpath("//*[contains(text(),'Dropdown')]"));
        dropDownLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/dropdown"));

        WebElement DropDownTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(DropDownTitle));

        Select drpSelectAnOption = new Select(driver.findElement(By.id("dropdown")));
        drpSelectAnOption.selectByVisibleText("Option 1");
        drpSelectAnOption.selectByIndex(2);
        drpSelectAnOption.selectByIndex(1);
        drpSelectAnOption.selectByIndex(2);

        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/dropdown"));
  }
}