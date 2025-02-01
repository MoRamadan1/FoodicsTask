package Web.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class utilites {

    public static WebDriver driver;
    private static boolean isInitiated = false;


    public static WebDriver getDriver() {
        return driver;
    }

    @FindBy(xpath = "//a[@id='nav-link-accountList']")
    public WebElement signinPageButton;

    @FindBy(name = "email")
//    private WebElement emailInput;
    public WebElement phoneNumberField;

    @FindBy(css = "input[aria-labelledby='continue-announce']")
    public WebElement continueButton;

    @FindBy(id = "ap_password")
    public WebElement password;

    @FindBy(id = "signInSubmit")
    public WebElement signinButton;

    public void InitiateTestCase() {
        if (!isInitiated) {
            isInitiated = true;
            System.out.println("INit");

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            //Launch website :
            driver.get("https://www.amazon.eg/?language=en_AE");

            //Initialize elements with PageFactory
            PageFactory.initElements(driver, this);

        }
    }

    @AfterMethod
    public void closeBrowser() {
        isInitiated = false;
//        driver.quit();//to stop the session
    }


    @BeforeMethod
    public void Login() {
        InitiateTestCase();
        System.out.println("login");
        signinPageButton.click();
        phoneNumberField.sendKeys("01151007809");
        continueButton.click();
        password.sendKeys("TempP@ss123");
        signinButton.click();
    }
}
