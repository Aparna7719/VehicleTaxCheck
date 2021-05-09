package StepDefinitions;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(features = ".", tags = {"@cartaxchk"}, dryRun = false,
            plugin= "com.cucumber.listener.ExtentCucumberFormatter:target/report/tsr.html")

    public class Run
    {
        public static String xmlPath= new File("src/main/resources/Data/extent-config.xml").getAbsolutePath();


        public void generateReport()
        {
            Reporter.loadXMLConfig(xmlPath);
        }
    }


