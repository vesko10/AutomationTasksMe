package lecture16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.lecture16.factory.Header;
import org.example.lecture16.factory.HomePage;
import org.example.lecture16.factory.LoginPage;
import org.example.lecture16.factory.ProfilePage;
import org.example.lecture16.factory.ModifyProfileForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class EditProfileTest {
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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    protected final void tearDownTest() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @DataProvider(name = "getUser")
    public Object[][] getUser() {
        return new Object[][]{{"test123489@gmail.com", "Abc123", "vesko201", "vesko15"}, //login with email
        };
    }

    @Test(dataProvider = "getUser")
    public void testEditProfile(String user, String password, String name, String modifiedUsername) {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isUrlLoaded(),"The Login URL is not correct!");
        String signInText = loginPage.getSingInElementText();
        Assert.assertEquals(signInText,"Sign in");
        loginPage.populateUsername(user);
        loginPage.populatePassword(password);
        loginPage.clickSignIn();
        Assert.assertTrue(homePage.isUrlLoaded(),"The Home URL is not correct!");

        header.clickProfileLink();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(),"The Profile URL is not correct!");
        String actualUsername = profilePage.getUsername();
        Assert.assertEquals(actualUsername,name,"Username is incorrect!");

        profilePage.clickEditProfileButton();


        ModifyProfileForm modifyProfileForm = new ModifyProfileForm(driver);
        String modifyYourProfileText = modifyProfileForm.getModifyYourProfileText();
        Assert.assertEquals(modifyYourProfileText,"Modify Your Profile");

        modifyProfileForm.enterModifiedUserName(modifiedUsername);
        modifyProfileForm.enterModifiedUserEmail();
        modifyProfileForm.enterModifiedPassword();
        modifyProfileForm.confirmModifiedPassword();
        modifyProfileForm.enterModifiedPublicInfo();

        modifyProfileForm.clickSaveButton();
//        Assert.assertFalse(modifyProfileForm.isSaveButtonDisplayed());

        String actualModifiedUsername = profilePage.getUModifiedsername();
        Assert.assertEquals(actualModifiedUsername,modifiedUsername,"Username is incorrect!");
    }
}

