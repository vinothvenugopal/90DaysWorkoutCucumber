package cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/java/featurefiles", glue = "cucumber.testcases", monochrome = true)
public class Day6_Shein_Runner extends AbstractTestNGCucumberTests{

}
 