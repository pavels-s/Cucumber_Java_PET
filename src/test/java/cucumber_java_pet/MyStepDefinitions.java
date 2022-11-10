package cucumber_java_pet;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;
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

    @Given("I am a guest customer")
    public void iMAGuestCustomer() {
        System.setProperty("webdriver.chrome.driver", "C:\\JavaGuru\\Soft\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://askomdch.com/store");
    }

    @And("I have a product in the cart")
    public void iHaveAProductInTheCart() throws InterruptedException {
        By addToCartBtn = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");
        Thread.sleep(5000);
        driver.findElement(addToCartBtn).click();
        Thread.sleep(3000);
        By viewCartLink = By.cssSelector("a[title='View cart']");
        driver.findElement(viewCartLink).click();
    }

    @And("I am on the checkout page")
    public void iAmOnTheCheckoutPage() {
        By proceedToCheckoutBtn = By.cssSelector(".checkout-button");
        driver.findElement(proceedToCheckoutBtn).click();
    }

    @When("I provide billing details")
    public void iProvideBillingDetails(List<Map<String,String>> billingDetails) {
        By billingFirstnameFld = By.id("billing_first_name");
        By billingLastnameFld = By.id("billing_last_name");
        By billingAddressOneFld = By.id("billing_address_1");
        By billingCityFld = By.id("billing_city");
        By billingStateDropdown = By.id("billing_state");
        By billingZipFld = By.id("billing_postcode");
        By billingEmailFld = By.id("billing_email");

        driver.findElement(billingFirstnameFld).clear();
        driver.findElement(billingFirstnameFld).sendKeys(billingDetails.get(0).get("firstname"));
        driver.findElement(billingLastnameFld).clear();
        driver.findElement(billingLastnameFld).sendKeys(billingDetails.get(0).get("lastname"));
        driver.findElement(billingAddressOneFld).clear();
        driver.findElement(billingAddressOneFld).sendKeys(billingDetails.get(0).get("address_line1"));
        driver.findElement(billingCityFld).clear();
        driver.findElement(billingCityFld).sendKeys(billingDetails.get(0).get("city"));
        Select select = new Select(driver.findElement(billingStateDropdown));
        select.selectByVisibleText(billingDetails.get(0).get("state"));
        driver.findElement(billingZipFld).clear();
        driver.findElement(billingZipFld).sendKeys(billingDetails.get(0).get("zip"));
        driver.findElement(billingEmailFld).clear();
        driver.findElement(billingEmailFld).sendKeys(billingDetails.get(0).get("email"));

    }

    @And("I place an order")
    public void iPlaceAnOrder() throws InterruptedException {
        By placeOrderBtn = By.id("place_order");
        driver.findElement(placeOrderBtn).click();
        Thread.sleep(5000);
    }

    @Then("order should be placed successfully")
    public void orderShouldBePlacedSuccessfully(){
        By noticeTxt = By.cssSelector(".woocommerce-notice");
        String actualNoticeMsg = driver.findElement(noticeTxt).getText();
        Assert.assertEquals("Thank you. Your order has been received.", actualNoticeMsg);
    }
}
