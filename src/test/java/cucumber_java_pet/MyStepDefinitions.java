package cucumber_java_pet;

import cucumber_java_pet.factory.DriverFactory;
import cucumber_java_pet.pages.CartPage;
import cucumber_java_pet.pages.CheckoutPage;
import cucumber_java_pet.pages.StorePage;
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
        driver = DriverFactory.getDriver();
        new StorePage(driver).load("https://askomdch.com/store");
    }
    @When("I add a {string} to the cart")
    public void iAddAToTheCart(String productName) {
        new StorePage(driver).addToCart(productName);
    }
    @Then("I should see {int} {string} int the cart")
    public void i_should_see_int_the_cart(int quantity, String productName) {
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(productName, cartPage.getProductName());
        Assert.assertEquals(quantity, cartPage.getProductQuantity());
        //Integer.compare(quantity, cartPage.getProductQuantity());
    }

    @Given("I am a guest customer")
    public void iMAGuestCustomer() {
        driver = DriverFactory.getDriver();
        new StorePage(driver).load("https://askomdch.com/store");
    }

    @And("I have a product in the cart")
    public void iHaveAProductInTheCart() {
        new StorePage(driver).addToCart("Blue Shoes");
    }

    @And("I am on the checkout page")
    public void iAmOnTheCheckoutPage() {
        new CartPage(driver).checkout();
    }

    @When("I provide billing details")
    public void iProvideBillingDetails(List<Map<String,String>> billingDetails) {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.setBillingDetails(billingDetails.get(0).get("firstname"),
                billingDetails.get(0).get("lastname"),
                billingDetails.get(0).get("address_line1"),
                billingDetails.get(0).get("city"),
                billingDetails.get(0).get("state"),
                billingDetails.get(0).get("zip"),
                billingDetails.get(0).get("email"));
    }

    @And("I place an order")
    public void iPlaceAnOrder() throws InterruptedException {
        new CheckoutPage(driver).placeOrder();
    }

    @Then("order should be placed successfully")
    public void orderShouldBePlacedSuccessfully(){
        Assert.assertEquals("Thank you. Your order has been received.", new CheckoutPage(driver).getNotice());
    }
}
