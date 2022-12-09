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
        tags = "@tc1103",
        monochrome = true,
        plugin = {"pretty"}

)
public class SmokeRunner {

}
