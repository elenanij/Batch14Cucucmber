package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {

    @FindBy(id = "firstName")
    public WebElement firstNameField;

    @FindBy(id = "lastName")
    public WebElement lastNameField;

    @FindBy(id = "middleName")
    public WebElement middleNameField;

    @FindBy(xpath = "//input[@type = 'checkbox']")
    public WebElement checkBox;

    @FindBy(id = "photofile")
    public WebElement photograph;

    @FindBy(id = "user_name")
    public WebElement username;

    @FindBy(id = "user_password")
    public WebElement password;

    @FindBy(id = "re_password")
    public WebElement confirmPassword;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    @FindBy(name = "employeeId")
    public WebElement empID;


    public AddEmployeePage() {

        PageFactory.initElements(driver, this);
    }

}
