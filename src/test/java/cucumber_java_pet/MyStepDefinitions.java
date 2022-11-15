package cucumber_java_pet;

import cucumber_java_pet.domain_objects.BillingDetails;
import cucumber_java_pet.domain_objects.Product;
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
    private BillingDetails billingDetails;

    @Given("I am on the Store page")
    public void iAmOnTheStorePage() {
        driver = DriverFactory.getDriver();
        new StorePage(driver).load("https://askomdch.com/store");
    }
    @When("I add a {product} to the cart")
    public void iAddAToTheCart(Product product) {
        new StorePage(driver).addToCart(product.getName());
    }

    @Then("I should see {int} {product} int the cart")
    public void i_should_see_int_the_cart(int quantity, Product product) {
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(product.getName(), cartPage.getProductName());
        Assert.assertEquals(quantity, cartPage.getProductQuantity());
        //Integer.compare(quantity, cartPage.getProductQuantity());
    }

    @Given("I am a guest customer")
    public void iMAGuestCustomer() {
        driver = DriverFactory.getDriver();
        new StorePage(driver).load("https://askomdch.com/store");
    }

    @And("my billing details are")
    public void myBillingDetailsAre(BillingDetails billingDetails) {
        this.billingDetails = billingDetails;
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
    public void iProvideBillingDetails() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.setBillingDetails(billingDetails);
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
