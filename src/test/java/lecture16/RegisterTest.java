package lecture16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.lecture16.factory.ProfilePage;
import org.example.lecture16.factory.Header;
import org.example.lecture16.factory.HomePage;
import org.example.lecture16.factory.LoginPage;
import org.example.lecture16.factory.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class RegisterTest {
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

    @DataProvider(name = "dataForRegistration")
    public Object[][] dataForRegistration() {
        return new Object[][]{{"Bbd123456", "Bbd123456", "Test"},
        };
    }

    @Test(dataProvider = "dataForRegistration")
    public void testRegisterUser(String password1, String password2, String publicInfo) {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");
        String signInText = loginPage.getSingInElementText();
        Assert.assertEquals(signInText, "Sign in");

        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        Assert.assertTrue(registrationPage.isUrlForRegistrationLoaded(), "The Registration URL is not correct!");
        String signUpText = registrationPage.getSingUpElementText();
        Assert.assertEquals(signUpText, "Sign up");

        registrationPage.populateUsername();
        registrationPage.populateEmail();
        registrationPage.populateDateOfBirth();
        registrationPage.populatePassword(password1);
        registrationPage.populatePassword2(password2);
        registrationPage.populatePublicInfo(publicInfo);
        registrationPage.clickSignInButton();

        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");

        header.clickProfileLink();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        String actualUsername = profilePage.getUsername();
        Assert.assertFalse(actualUsername.isEmpty());
    }
}
