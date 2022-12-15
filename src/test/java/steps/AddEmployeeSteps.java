package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        //WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
        //pimOption.click();
        click(dashboard.pimOption);
    }

    @When("user clicks on Add Employee button")
    public void user_clicks_on_add_employee_button() {
        //WebElement addEmployeeOption = driver.findElement(By.id("menu_pim_addEmployee"));
        // addEmployeeOption.click();
        click(dashboard.addEmployeeOption);
    }

    @When("user enter firstname and lastname")
    public void user_enter_firstname_and_lastname() {
        //WebElement firstName = driver.findElement(By.id("firstName"));
        //firstName.sendKeys("Nomar");
        sendText(addEmployee.firstNameField, "Nomad");

        //WebElement lastName = driver.findElement(By.id("lastName"));
        //lastName.sendKeys("Sorua");
        sendText(addEmployee.lastNameField, "Smith");

    }

    @When("user enter {string} and {string}")
    public void user_enter_and(String firstName, String lastName) {
        sendText(addEmployee.firstNameField, firstName);
        sendText(addEmployee.lastNameField, lastName);

    }

    @When("user enter {string} and {string} for adding multiple employees")
    public void user_enter_and_for_adding_multiple_employees(String firstName, String lastName) {
        sendText(addEmployee.firstNameField, firstName);
        sendText(addEmployee.lastNameField, lastName);
    }

    @When("user adds multiple employees and verify they are added successfully")
    public void user_adds_multiple_employees_and_verify_they_are_added_successfully(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> employeeNames = dataTable.asMaps();
        for (Map<String, String> employee : employeeNames) {
            String firstName = employee.get("firstName");
            String middleName = employee.get("middleName");
            String lastName = employee.get("lastName");

            sendText(addEmployee.firstNameField, firstName);
            sendText(addEmployee.middleNameField, middleName);
            sendText(addEmployee.lastNameField, lastName);
            String idValue = addEmployee.empID.getAttribute("value");

            Thread.sleep(2000);
            click(addEmployee.saveButton);
            Thread.sleep(2000);

            //verify the employee have been added
            click(dashboard.empListOption);
            sendText(employeeList.empSearchIdField,idValue);
            click(employeeList.searchButton);
            Assert.assertTrue(idValue.equals(employeeList.resultID.getText()));

            click(dashboard.addEmployeeOption);


        }


    }

    @When("user adds multiple employees from excel using {string} and verify they are added successfully")
    public void user_adds_multiple_employees_from_excel_using_and_verify_they_are_added_successfully(String sheetName) throws InterruptedException {

        List<Map<String, String>> empFromExcel = ExcelReader.excelListIntoMap(Constants.TEST_DATA_FILEPATH, sheetName);

        Iterator<Map<String, String>> itr = empFromExcel.iterator();

        while(itr.hasNext()){

            Map<String, String> employee = itr.next();

            sendText(addEmployee.firstNameField, employee.get("firstName"));
            sendText(addEmployee.middleNameField, employee.get("middleName"));
            sendText(addEmployee.lastNameField,  employee.get("lastName"));
            String empID = addEmployee.empID.getAttribute("value");

            sendText(addEmployee.photograph, employee.get("photograph"));
            if(!addEmployee.checkBox.isSelected()){
                click(addEmployee.checkBox);
            }
            sendText(addEmployee.username, employee.get("username"));
            sendText(addEmployee.password, employee.get("password"));
            sendText(addEmployee.confirmPassword, employee.get("confirmPassword"));

            Thread.sleep(2000);
            click(addEmployee.saveButton);

            //verify
            click(dashboard.empListOption);
            sendText(employeeList.empSearchIdField, empID);
            click(employeeList.searchButton);

            String empInfo = driver.findElement(By.xpath("//table[@id = 'resultTable']/tbody/tr")).getText();
            String expectedEmpInfo = empID + " " + employee.get("firstName") + " " + employee.get("middleName") +" " + employee.get("lastName");
            Assert.assertTrue(empInfo.equals(expectedEmpInfo));


            Thread.sleep(2000);
            click(dashboard.addEmployeeOption);

        }
    }


    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        //WebElement saveButton = driver.findElement(By.id("btnSave"));
        //saveButton.click();
        click(addEmployee.saveButton);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added");


    }

}
