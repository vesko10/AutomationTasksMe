package lecture15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
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

        WebElement dropDownTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(dropDownTitle));


        Select drpSelectAnOption = new Select(driver.findElement(By.id("dropdown")));
        drpSelectAnOption.selectByVisibleText("Option 1");
        drpSelectAnOption.selectByIndex(2);
        drpSelectAnOption.selectByIndex(1);
        drpSelectAnOption.selectByIndex(2);

        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/dropdown"));
    }

    @Test
    public void testDynamicContent() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement dynamicContentLink = driver.findElement(By.xpath("//*[contains(text(),'Dynamic Content')]"));
        dynamicContentLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/dynamic_content"));

        WebElement dynamicContentTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(dynamicContentTitle));

        String actualText1 = driver.findElement(By.xpath("(//div[@class='example']/p)[1]")).getText();
        String expectedText1 = "This example demonstrates the ever-evolving nature of content by loading new text and images on each page refresh.";
        Assert.assertEquals(actualText1, expectedText1);

        String actualText2 = driver.findElement(By.xpath("(//div[@class='example']/p)[2]")).getText();
        String expectedText2 = "To make some of the content static append ?with_content=static or click here.";
        Assert.assertEquals(actualText2, expectedText2);

        WebElement clickHereLink = driver.findElement(By.xpath("//*[contains(text(),'click here')]"));
        clickHereLink.click();

        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/dynamic_content"));
        wait.until(ExpectedConditions.visibilityOf(dynamicContentTitle));
        Assert.assertEquals(actualText1, expectedText1);
        Assert.assertEquals(actualText2, expectedText2);

        WebElement picture1 = driver.findElement(By.xpath("(//img[@src='/img/avatars/Original-Facebook-Geek-Profile-Avatar-6.jpg'])[1]"));
        Assert.assertTrue(picture1.isDisplayed());

        WebElement picture2 = driver.findElement(By.xpath("(//img[@src='/img/avatars/Original-Facebook-Geek-Profile-Avatar-6.jpg'])[1]"));
        Assert.assertTrue(picture2.isDisplayed());

        WebElement picture3 = driver.findElement(By.xpath("(//img[@src='/img/avatars/Original-Facebook-Geek-Profile-Avatar-6.jpg'])[1]"));
        Assert.assertTrue(picture3.isDisplayed());

        WebElement text1 = driver.findElement(By.xpath("(//div[@class='large-10 columns'])[1]"));
        Assert.assertTrue(text1.isDisplayed());

        WebElement text2 = driver.findElement(By.xpath("(//div[@class='large-10 columns'])[2]"));
        Assert.assertTrue(text2.isDisplayed());

        WebElement text3 = driver.findElement(By.xpath("(//div[@class='large-10 columns'])[3]"));
        Assert.assertTrue(text3.isDisplayed());
    }

    @Test
    public void testFloatingMenu() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement floatingMenuLink = driver.findElement(By.xpath("//*[contains(text(),'Floating Menu')]"));
        floatingMenuLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/floating_menu"));

        WebElement dynamicContentTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(dynamicContentTitle));

        WebElement paragraph1 = driver.findElement(By.xpath("(//div[@class='scroll large-10 columns large-centered']/p)[1]"));
        Assert.assertTrue(paragraph1.isDisplayed());

        WebElement paragraph2 = driver.findElement(By.xpath("(//div[@class='scroll large-10 columns large-centered']/p)[2]"));
        Assert.assertTrue(paragraph2.isDisplayed());

        WebElement paragraph3 = driver.findElement(By.xpath("(//div[@class='scroll large-10 columns large-centered']/p)[3]"));
        Assert.assertTrue(paragraph3.isDisplayed());

        WebElement paragraph4 = driver.findElement(By.xpath("(//div[@class='scroll large-10 columns large-centered']/p)[4]"));
        Assert.assertTrue(paragraph4.isDisplayed());

        WebElement paragraph5 = driver.findElement(By.xpath("(//div[@class='scroll large-10 columns large-centered']/p)[5]"));
        Assert.assertTrue(paragraph5.isDisplayed());

        WebElement paragraph6 = driver.findElement(By.xpath("(//div[@class='scroll large-10 columns large-centered']/p)[6]"));
        Assert.assertTrue(paragraph6.isDisplayed());

        WebElement paragraph7 = driver.findElement(By.xpath("(//div[@class='scroll large-10 columns large-centered']/p)[7]"));
        Assert.assertTrue(paragraph7.isDisplayed());

        WebElement paragraph8 = driver.findElement(By.xpath("(//div[@class='scroll large-10 columns large-centered']/p)[8]"));
        Assert.assertTrue(paragraph8.isDisplayed());

        WebElement homeButton = driver.findElement(By.xpath("//*[contains(text(),'Home')]"));
        Assert.assertTrue(homeButton.isDisplayed());

        WebElement newsButton = driver.findElement(By.xpath("//*[contains(text(),'News')]"));
        Assert.assertTrue(newsButton.isDisplayed());

        WebElement contactButton = driver.findElement(By.xpath("//*[contains(text(),'Contact')]"));
        Assert.assertTrue(contactButton.isDisplayed());

        WebElement aboutButton = driver.findElement(By.xpath("//*[contains(text(),'About')]"));
        Assert.assertTrue(aboutButton.isDisplayed());

        homeButton.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/floating_menu#home"));

        newsButton.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/floating_menu#news"));

        contactButton.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/floating_menu#contact"));

        aboutButton.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/floating_menu#about"));

        // scroll down the page by  1000 pixel vertical in order to check visibility of all buttons
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        Assert.assertTrue(homeButton.isDisplayed());
        Assert.assertTrue(newsButton.isDisplayed());
        Assert.assertTrue(contactButton.isDisplayed());
        Assert.assertTrue(aboutButton.isDisplayed());

    }

    @Test
    public void testHoversMenuCheck() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement hoversLink = driver.findElement(By.xpath("//*[contains(text(),'Hovers')]"));
        hoversLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/hovers"));

        WebElement dynamicContentTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(dynamicContentTitle));

        WebElement subtext = driver.findElement(By.xpath("//div[@class='example']/p"));
        Assert.assertTrue(subtext.isDisplayed());

        WebElement element1 = driver.findElement(By.xpath("(//div[@class='figure'])[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element1).perform();
        String tooltipText1 = driver.findElement(By.xpath("(//div[@class='figcaption'])[1]")).getText();
        Assert.assertEquals(tooltipText1, "name: user1\n" +
                "View profile");

        WebElement element2 = driver.findElement(By.xpath("(//div[@class='figure'])[2]"));
        actions.moveToElement(element2).perform();
        String tooltipText2 = driver.findElement(By.xpath("(//div[@class='figcaption'])[2]")).getText();
        Assert.assertEquals(tooltipText2, "name: user2\n" +
                "View profile");

        WebElement element3 = driver.findElement(By.xpath("(//div[@class='figure'])[3]"));
        actions.moveToElement(element3).perform();
        String tooltipText3 = driver.findElement(By.xpath("(//div[@class='figcaption'])[3]")).getText();
        Assert.assertEquals(tooltipText3, "name: user3\n" +
                "View profile");
    }

    @Test
    public void testHoversMenuFirstElementClick() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement hoversLink = driver.findElement(By.xpath("//*[contains(text(),'Hovers')]"));
        hoversLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/hovers"));

        WebElement dynamicContentTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(dynamicContentTitle));

        WebElement element1 = driver.findElement(By.xpath("(//div[@class='figure'])[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element1).perform();
        WebElement clickHereLink1 = driver.findElement(By.xpath("(//*[contains(text(),'View profile')])[1]"));
        clickHereLink1.click();

        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/users/1"));
        WebElement notFoundTitle = driver.findElement(By.tagName("h1"));
        wait.until(ExpectedConditions.visibilityOf(notFoundTitle));

    }

    @Test
    public void testHoversMenuSecondElementClick() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement hoversLink = driver.findElement(By.xpath("//*[contains(text(),'Hovers')]"));
        hoversLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/hovers"));

        WebElement dynamicContentTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(dynamicContentTitle));

        WebElement element2 = driver.findElement(By.xpath("(//div[@class='figure'])[2]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element2).perform();
        WebElement clickHereLink2 = driver.findElement(By.xpath("(//*[contains(text(),'View profile')])[2]"));
        clickHereLink2.click();

        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/users/2"));
        WebElement notFoundTitle = driver.findElement(By.tagName("h1"));
        wait.until(ExpectedConditions.visibilityOf(notFoundTitle));

    }

    @Test
    public void testHoversMenuThirdElementClick() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement hoversLink = driver.findElement(By.xpath("//*[contains(text(),'Hovers')]"));
        hoversLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/hovers"));

        WebElement dynamicContentTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(dynamicContentTitle));

        WebElement element3 = driver.findElement(By.xpath("(//div[@class='figure'])[3]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element3).perform();
        WebElement clickHereLink3 = driver.findElement(By.xpath("(//*[contains(text(),'View profile')])[3]"));
        clickHereLink3.click();

        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/users/3"));
        WebElement notFoundTitle = driver.findElement(By.tagName("h1"));
        wait.until(ExpectedConditions.visibilityOf(notFoundTitle));
    }

    @Test
    public void testMultipleWindows() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement multipleWindowsLink = driver.findElement(By.xpath("//*[contains(text(),'Multiple Windows')]"));
        multipleWindowsLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/windows"));

        WebElement openingANewWindowTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(openingANewWindowTitle));

        WebElement clickHereLink = driver.findElement(By.xpath("//*[contains(text(),'Click Here')]"));
        clickHereLink.click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://the-internet.herokuapp.com/windows");

        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        String secondWindow = windows.get(1);
        driver.switchTo().window(secondWindow);
        driver.manage().window().maximize();
        currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://the-internet.herokuapp.com/windows/new");
        WebElement headline = driver.findElement(By.tagName("h3"));
        String actualHeadlineText = headline.getText();
        Assert.assertEquals(actualHeadlineText, "New Window");
    }

    @Test
    public void testRedirectLinkCheck() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement redirectLink = driver.findElement(By.xpath("//*[contains(text(),'Redirect Link')]"));
        redirectLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/redirector"));

        WebElement redirectionTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(redirectionTitle));

        WebElement paragraphText = driver.findElement(By.xpath("//div[@class='example']/p"));
        wait.until(ExpectedConditions.visibilityOf(paragraphText));

        WebElement hereLink = driver.findElement(By.id("redirect"));
        Assert.assertTrue(hereLink.isDisplayed());

        hereLink.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/status_codes"));

        WebElement statusCodes = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(statusCodes));

        WebElement paragraphText2 = driver.findElement(By.xpath("//div[@class='example']/p"));
        Assert.assertTrue(paragraphText2.isDisplayed());

        WebElement statusCode200 = driver.findElement(By.xpath("(//li/a)[1]"));
        Assert.assertTrue(statusCode200.isDisplayed());

        WebElement statusCode301 = driver.findElement(By.xpath("(//li/a)[2]"));
        Assert.assertTrue(statusCode301.isDisplayed());

        WebElement statusCode404 = driver.findElement(By.xpath("(//li/a)[3]"));
        Assert.assertTrue(statusCode404.isDisplayed());

        WebElement statusCode500 = driver.findElement(By.xpath("(//li/a)[4]"));
        Assert.assertTrue(statusCode500.isDisplayed());
    }

    @Test
    public void testStatusCode200() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement redirectLink = driver.findElement(By.xpath("//*[contains(text(),'Redirect Link')]"));
        redirectLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/redirector"));

        WebElement redirectionTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(redirectionTitle));

        WebElement hereLink = driver.findElement(By.id("redirect"));
        Assert.assertTrue(hereLink.isDisplayed());

        hereLink.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/status_codes"));

        WebElement statusCode200 = driver.findElement(By.xpath("(//li/a)[1]"));
        Assert.assertTrue(statusCode200.isDisplayed());

        statusCode200.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/status_codes/200"));

        WebElement statusCodes = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(statusCodes));

        WebElement paragraphText = driver.findElement(By.xpath("//div[@class='example']/p"));
        Assert.assertTrue(paragraphText.isDisplayed());

        WebElement hereLinkCode200 = driver.findElement(By.xpath("//*[contains(text(),'here')]"));
        hereLinkCode200.click();

        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/status_codes"));

        WebElement statusCodesTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(statusCodesTitle));

    }

    @Test
    public void testStatusCode301() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement redirectLink = driver.findElement(By.xpath("//*[contains(text(),'Redirect Link')]"));
        redirectLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/redirector"));

        WebElement redirectionTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(redirectionTitle));

        WebElement hereLink = driver.findElement(By.id("redirect"));
        Assert.assertTrue(hereLink.isDisplayed());

        hereLink.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/status_codes"));

        WebElement statusCode301 = driver.findElement(By.xpath("(//li/a)[2]"));
        Assert.assertTrue(statusCode301.isDisplayed());

        statusCode301.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/status_codes/301"));

        WebElement statusCodes = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(statusCodes));

        WebElement paragraphText = driver.findElement(By.xpath("//div[@class='example']/p"));
        Assert.assertTrue(paragraphText.isDisplayed());

        WebElement hereLinkCode301 = driver.findElement(By.xpath("//*[contains(text(),'here')]"));
        hereLinkCode301.click();

        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/status_codes"));

        WebElement statusCodesTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(statusCodesTitle));
    }

    @Test
    public void testStatusCode404() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement redirectLink = driver.findElement(By.xpath("//*[contains(text(),'Redirect Link')]"));
        redirectLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/redirector"));

        WebElement redirectionTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(redirectionTitle));

        WebElement hereLink = driver.findElement(By.id("redirect"));
        Assert.assertTrue(hereLink.isDisplayed());

        hereLink.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/status_codes"));

        WebElement statusCode404 = driver.findElement(By.xpath("(//li/a)[3]"));
        Assert.assertTrue(statusCode404.isDisplayed());

        statusCode404.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/status_codes/404"));

        WebElement statusCodes = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(statusCodes));

        WebElement paragraphText = driver.findElement(By.xpath("//div[@class='example']/p"));
        Assert.assertTrue(paragraphText.isDisplayed());

        WebElement hereLinkCode404 = driver.findElement(By.xpath("//*[contains(text(),'here')]"));
        hereLinkCode404.click();

        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/status_codes"));

        WebElement statusCodesTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(statusCodesTitle));
    }

    @Test
    public void testStatusCode500() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement redirectLink = driver.findElement(By.xpath("//*[contains(text(),'Redirect Link')]"));
        redirectLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/redirector"));

        WebElement redirectionTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(redirectionTitle));

        WebElement hereLink = driver.findElement(By.id("redirect"));
        Assert.assertTrue(hereLink.isDisplayed());

        hereLink.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/status_codes"));

        WebElement statusCode500 = driver.findElement(By.xpath("(//li/a)[4]"));
        Assert.assertTrue(statusCode500.isDisplayed());

        statusCode500.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/status_codes/500"));

        WebElement statusCodes = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(statusCodes));

        WebElement paragraphText = driver.findElement(By.xpath("//div[@class='example']/p"));
        Assert.assertTrue(paragraphText.isDisplayed());

        WebElement hereLinkCode500 = driver.findElement(By.xpath("//*[contains(text(),'here')]"));
        hereLinkCode500.click();

        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/status_codes"));

        WebElement statusCodesTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(statusCodesTitle));
    }

    @Test
    public void testDynamicControls() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement dynamicControlsLink = driver.findElement(By.xpath("//*[contains(text(),'Dynamic Controls')]"));
        dynamicControlsLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/dynamic_controls"));

        WebElement dynamicControlsTitle = driver.findElement(By.tagName("h4"));
        wait.until(ExpectedConditions.visibilityOf(dynamicControlsTitle));

        WebElement paragraphText = driver.findElement(By.xpath("//div[@class='example']/p"));
        Assert.assertTrue(paragraphText.isDisplayed());

        WebElement removeAddText = driver.findElement(By.xpath("//*[contains(text(),'Remove/add')]"));
        Assert.assertTrue(removeAddText.isDisplayed());

        WebElement enableDisable = driver.findElement(By.xpath("//*[contains(text(),'Enable/disable')]"));
        Assert.assertTrue(enableDisable.isDisplayed());

        WebElement aCheckBox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        Assert.assertTrue(aCheckBox.isDisplayed());
        Assert.assertFalse(aCheckBox.isSelected());

        WebElement disabledField = driver.findElement(By.xpath("//input[@type='text']"));
        Assert.assertTrue(disabledField.isDisplayed());
        Assert.assertFalse(disabledField.isEnabled());

        WebElement removeButton = driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']"));
        Assert.assertTrue(removeButton.isDisplayed());

        WebElement enabledButton = driver.findElement(By.xpath("//button[@onclick='swapInput()']"));
        Assert.assertTrue(enabledButton.isDisplayed());

        aCheckBox.click();
        Assert.assertTrue(aCheckBox.isSelected());

        //WebElement messageAfterEnabledButtonClick = driver.findElement(By.id("message"));
        // Assert.assertTrue(messageAfterEnabledButtonClick.isDisplayed());
        //wait.until(ExpectedConditions.visibilityOf(messageAfterEnabledButtonClick));
        enabledButton.click();
        String actualText1 = driver.findElement(By.id("message")).getText();
        Assert.assertEquals(actualText1, "It's enabled!");

        enabledButton.click();
        String actualText2 = driver.findElement(By.id("message")).getText();
        Assert.assertEquals(actualText2, "It's disabled!");

//        enabledButton.click();
//        WebElement messageAfterDisableButtonClick = driver.findElement(By.id("message"));
//        Assert.assertTrue(messageAfterDisableButtonClick.isDisplayed());
//        wait.until(ExpectedConditions.visibilityOf(messageAfterDisableButtonClick));

        removeButton.click();
        String actualText3 = driver.findElement(By.id("message")).getText();
        Assert.assertEquals(actualText3, "It's gone!");

        removeButton.click();
        String actualText4 = driver.findElement(By.id("message")).getText();
        Assert.assertEquals(actualText4, "It's back!");
        WebElement aCheckBoxAfterAdding = driver.findElement(By.id("checkbox"));
        Assert.assertTrue(aCheckBoxAfterAdding.isDisplayed());
        Assert.assertFalse(aCheckBoxAfterAdding.isSelected());
    }

    @Test
    public void testDynamicLoading() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement dynamicLoadingLink = driver.findElement(By.xpath("//*[contains(text(),'Dynamic Loading')]"));
        dynamicLoadingLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/dynamic_loading"));

        WebElement dynamicLoadedTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(dynamicLoadedTitle));

        WebElement paragraphText1 = driver.findElement(By.xpath("(//div[@class='example']/p)[1]"));
        Assert.assertTrue(paragraphText1.isDisplayed());

        WebElement paragraphText2 = driver.findElement(By.xpath("(//div[@class='example']/p)[2]"));
        Assert.assertTrue(paragraphText2.isDisplayed());

        WebElement exampleLink1 = driver.findElement(By.xpath("//*[contains(text(),'Example 1: Element on page that is hidden')]"));
        Assert.assertTrue(exampleLink1.isDisplayed());

        WebElement exampleLink2 = driver.findElement(By.xpath("//*[contains(text(),'Example 2: Element rendered after the fact')]"));
        Assert.assertTrue(exampleLink2.isDisplayed());

        exampleLink1.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/dynamic_loading/1"));
        WebElement dynamicallyLoadedPageElementsTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(dynamicallyLoadedPageElementsTitle));
        WebElement example1Title1 = driver.findElement(By.tagName("h4"));
        wait.until(ExpectedConditions.visibilityOf(example1Title1));

        WebElement startButton = driver.findElement(By.xpath("//*[contains(text(),'Start')]"));
        startButton.click();
        WebElement textAfterButtonClick = driver.findElement(By.xpath("//*[contains(text(),'Hello World!')]"));
        wait.until(ExpectedConditions.visibilityOf(textAfterButtonClick));
        Assert.assertTrue(textAfterButtonClick.isDisplayed());

    }

    @Test
    public void testDynamicLoadingWithExample2() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement dynamicLoadingLink = driver.findElement(By.xpath("//*[contains(text(),'Dynamic Loading')]"));
        dynamicLoadingLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/dynamic_loading"));

        WebElement dynamicLoadedTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(dynamicLoadedTitle));

        WebElement paragraphText1 = driver.findElement(By.xpath("(//div[@class='example']/p)[1]"));
        Assert.assertTrue(paragraphText1.isDisplayed());

        WebElement paragraphText2 = driver.findElement(By.xpath("(//div[@class='example']/p)[2]"));
        Assert.assertTrue(paragraphText2.isDisplayed());

        WebElement exampleLink1 = driver.findElement(By.xpath("//*[contains(text(),'Example 1: Element on page that is hidden')]"));
        Assert.assertTrue(exampleLink1.isDisplayed());

        WebElement exampleLink2 = driver.findElement(By.xpath("//*[contains(text(),'Example 2: Element rendered after the fact')]"));
        Assert.assertTrue(exampleLink2.isDisplayed());
        exampleLink2.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/dynamic_loading/2"));
        WebElement dynamicallyLoadedPageElementsTitle = driver.findElement(By.tagName("h3"));
        wait.until(ExpectedConditions.visibilityOf(dynamicallyLoadedPageElementsTitle));
        WebElement example1Title2 = driver.findElement(By.tagName("h4"));
        wait.until(ExpectedConditions.visibilityOf(example1Title2));

        WebElement startButton = driver.findElement(By.xpath("//*[contains(text(),'Start')]"));
        startButton.click();
        WebElement textAfterButtonClick = driver.findElement(By.id("finish"));
        wait.until(ExpectedConditions.visibilityOf(textAfterButtonClick));
        Assert.assertTrue(textAfterButtonClick.isDisplayed());
    }
}