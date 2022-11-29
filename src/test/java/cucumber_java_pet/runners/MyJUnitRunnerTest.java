package cucumber_java_pet.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"cucumber_java_pet"},
        features = "src/test/resources/cucumber_java_pet"
)
public class MyJUnitRunnerTest {

}
