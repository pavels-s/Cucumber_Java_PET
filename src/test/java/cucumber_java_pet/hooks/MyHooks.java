package cucumber_java_pet.hooks;

import cucumber_java_pet.factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class MyHooks {
    private WebDriver driver;

    @Before
    public void before(){
        driver = DriverFactory.initializeDriver(System.getProperty("browser", "chrome"));
    }

    @After
    public void after(){
        driver.quit();
    }
}
