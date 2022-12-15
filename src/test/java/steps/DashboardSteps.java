package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DashboardSteps extends CommonMethods {

    @Then("user verifies the dashboard")
    public void user_verifies_the_dashboard() {
        System.out.println("Batch 14 is happy now");
    }


    @Then("user verifies all the dashboard Tabs")
    public void user_verifies_all_the_dashboard_tabs(DataTable dataTable) {

        List<String> expectedTabs = dataTable.asList();

        List<String> actualTabs = new ArrayList<>();

        for(WebElement tab: dashboard.dashboardTabs) {

            actualTabs.add(tab.getText());
        }

        System.out.println(actualTabs);
        System.out.println(expectedTabs);

            Assert.assertTrue(actualTabs.equals(expectedTabs));

    }
    }



