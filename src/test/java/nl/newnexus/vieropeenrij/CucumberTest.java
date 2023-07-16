package nl.newnexus.vieropeenrij;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"nl.newnexus.vieropeenrij"},
        features = "classpath:features",
        plugin = {"pretty", "json:target/cucumber-report.json"})
public class CucumberTest {

}