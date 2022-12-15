package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {

    @FindBy(id="txtUsername")
    public WebElement usernameTextField;

    @FindBy (xpath = "//input[@id = 'txtPassword']")
    public WebElement passwordTextField;

    @FindBy (css="input#btnLogin")
    public  WebElement loginButton;

    @FindBy(id = "spanMessage")
    public WebElement errorMessage;

    //    call the constructor to intialize all elements
    public LoginPage(){
        PageFactory.initElements(driver,this);
    }
}
