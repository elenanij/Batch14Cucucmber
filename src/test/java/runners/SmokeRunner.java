package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        //features we use to provide the path of all feature files
        features = "src/test/resources/features/",
        glue = "steps",
        dryRun = false,
        tags = "@tc1101",
        monochrome = true,
       //pretty keyword prints the steps in the console to increase readability
        //to generate the reports we need plugin of runner class
        //when we generate any report, this should be under target folder

        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"}

)

public class SmokeRunner {

}


