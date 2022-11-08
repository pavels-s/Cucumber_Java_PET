package cucumber_java_pet;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Optional;

public class MyStepDefinitions {
    private static WebDriver driver;

    @Given("I am on the Store page")
    public void iAmOnTheStorePage() {
        System.setProperty("webdriver.chrome.driver", "C:\\JavaGuru\\Soft\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://askomdch.com/store");
    }
    @When("I add a {string} to the cart")
    public void iAddAToTheCart(String productName) throws InterruptedException {
        By addToCartBtn = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");
        Thread.sleep(5000);
        driver.findElement(addToCartBtn).click();
        Thread.sleep(3000);
        By viewCartLink = By.cssSelector("a[title='View cart']");
        driver.findElement(viewCartLink).click();
    }
    @Then("I should see {int} {string} int the cart")
    public void i_should_see_int_the_cart(Integer quantity, String productName) {
        By productNameField = By.cssSelector("td[class='product-name'] a");
        String actualProductName = driver.findElement(productNameField).getText();
        By productQuantityFld = By.cssSelector("input[type=\"number\"]");
        String actualQuantity = driver.findElement(productQuantityFld).getAttribute("value");
        Assert.assertEquals(productName, actualProductName);
        Integer.compare(quantity, Integer.parseInt(actualQuantity));
    }
}
