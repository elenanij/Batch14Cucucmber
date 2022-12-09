package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.ConfigReader;

public class EmployeeSearchSteps extends CommonMethods {

    @When("user clicks on EmployeeList option")
    public void user_clicks_on_employee_list_option() {
        WebElement empListOption = driver.findElement(By.id("menu_pim_viewEmployeeList"));
        click(empListOption);
    }

    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
       WebElement empIdField = driver.findElement(By.id("empsearch_id"));
       //sendText(empIdField, "45154A");
       sendText(empIdField, ConfigReader.getPropertyValue("empID"));
    }

    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() {
        WebElement empNameField = driver.findElement(By.id("empsearch_employee_name_empName"));
        //sendText(empNameField, "dawggy");
        sendText(empNameField, ConfigReader.getPropertyValue("empName"));
    }

    @When("user clicks the search button")
    public void user_clicks_the_search_button() {
        WebElement searchButton = driver.findElement(By.id("searchBtn"));
        click(searchButton);
    }
    @Then("user sees employee information is displayed")
    public void user_sees_employee_information_is_displayed() {
        System.out.println("Employee information can be seen");
    }
}
