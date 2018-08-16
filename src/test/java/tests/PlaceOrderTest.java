package tests;

import io.github.cdimascio.dotenv.Dotenv;
import model.Cart;
import model.Product;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import static org.testng.Assert.assertEquals;
import static utils.TestListener.handleExceptionAndMarkFailResult;
//import static utils.TestReport.handleExceptionAndMarkFailResult;

public class PlaceOrderTest extends TestBase {

    @Test
    public void buyRandomProducts() {

        boolean testResult = true;
        try {
            Dotenv dotenv = Dotenv.configure().directory("./").load();

            String email = dotenv.get("EMAIL");
            String pwd = dotenv.get("PASSWORD");
            Cart cart = new Cart();

            PageHome pageHome = new PageHome(driver);
            PageLogin pageLogin = pageHome.ClickSignInButton();

            PageMyAccount pageMyAccount = pageLogin.signIn(email, pwd);
            pageHome = pageMyAccount.clickHomeIcon();

            pageHome.addARandomProductToCart(cart);
            pageHome.selectContinueShopping();
            pageHome.addARandomProductToCart(cart);
            PageSummary pageSummary = pageHome.selectProceedToCheckOut();

            testResult &= pageSummary.verifyAmount(cart);
            pageSummary.clickProceedToCheckout(driver);

            System.out.println("navigating to page address");
            PageAddress pageAddress = new PageAddress(driver);
            pageAddress.checkToUseDeliveryAddressAsBillingAddress();
            pageAddress.clickProceedToCheckout(driver);

            PageShipping pageShipping = new PageShipping(driver);
            pageShipping.checkToAgreeTermsAndCondition();
            pageShipping.clickProceedToCheckout(driver);

            PagePayment pagePayment = new PagePayment(driver);
            //testResult &= pagePayment.verifyCartSummary(cart);
            double totalPrice = pagePayment.getTotalPrice();
            pagePayment.selectPayByCheck();
            testResult &= pagePayment.verifyAmount(totalPrice);
            pagePayment.confirmOrder();
            testResult &= pagePayment.verifyOrderPalcedSuccess();

        } catch (Exception ex) {
            testResult = false;
            handleExceptionAndMarkFailResult(driver, ex);
        } finally {
            assertEquals(testResult, true);
        }
    }
}
