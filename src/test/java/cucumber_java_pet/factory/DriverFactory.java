package cucumber_java_pet.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver initializeDriver(String browser){
        switch (browser) {
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", "C:\\JavaGuru\\Soft\\chromedriver.exe");
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                System.setProperty("webdriver.geko.driver", "C:\\JavaGuru\\Soft\\geckodriver.exe");
                driver = new FirefoxDriver();
            }
            default -> throw new IllegalStateException("Invalid browser: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
