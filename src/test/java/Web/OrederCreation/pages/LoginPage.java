package Web.OrederCreation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {


    @FindBy(name = "email")
//    private WebElement emailInput;
    public WebElement phoneNumberField;

    @FindBy(css = "input[aria-labelledby='continue-announce']")
    public WebElement continueButton;

    @FindBy(id = "ap_password")
    public WebElement password;

    @FindBy(id = "signInSubmit")
    public WebElement signinButton;


}
