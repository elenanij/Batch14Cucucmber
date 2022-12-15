package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.concurrent.TimeUnit;

public class LoginSteps extends CommonMethods {

   // WebDriver driver;

    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {

        openBrowserAndLaunchApplication();
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        //driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login%22");
       // driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
       // driver.manage().window().maximize();

    }

    @When("user enters valid username and valid password")
    public void user_enters_valid_username_and_valid_password() {

       sendText(login.usernameTextField, ConfigReader.getPropertyValue("username"));
        sendText(login.passwordTextField, ConfigReader.getPropertyValue("password"));
    }

    @When("user enters ess username and valid password")
    public void user_enters_ess_username_and_valid_password() {

        sendText(login.usernameTextField, "asmahuma321");
        sendText(login.passwordTextField, "Hum@nhrm123");
    }

    @When("user enters invalid username and valid password")
    public void user_enters_invalid_username_and_valid_password() {
        sendText(login.usernameTextField, "admin123");
        sendText(login.passwordTextField, "Hum@nhrm");
    }

    @Then("error message is displayed")
    public void error_message_is_displayed() {
        System.out.println("Error message displayed");
    }


    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        click(login.loginButton);
    }

    @When("user enters different {string} and {string} and verify the {string} for it")
    public void user_enters_different_and_and_verify_the_for_it(String username, String password, String errorMessage) throws InterruptedException {
        sendText(login.usernameTextField, username);
        sendText(login.passwordTextField, password);
        click(login.loginButton);
        Thread.sleep(1000);

        String errorActual = login.errorMessage.getText();
        Assert.assertEquals(errorActual, errorMessage);

    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {

        //int i = 10/0;
        //WebElement welcomeMessage = driver.findElement(By.xpath("//a[text() = 'Welcome Admin']"));
        if(dashboard.welcomeMessage.isDisplayed()) {

            System.out.println("Test case is passed");
        }

        else {

            System.out.println("Test is failed");
        }

    }
}
