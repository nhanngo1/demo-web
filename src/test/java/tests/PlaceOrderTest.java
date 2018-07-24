package tests;

import io.github.cdimascio.dotenv.Dotenv;
import model.Cart;
import model.Product;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

public class PlaceOrderTest extends TestBase{

    @Test
    public void buyRandomProducts() {

        Dotenv dotenv = Dotenv.configure().directory("./").load();

        String email = dotenv.get("EMAIL");
        String pwd = dotenv.get("PASSWORD");
        Cart cart = new Cart();
        int result = 1;

        PageHome pageHome = new PageHome(driver);
        PageLogin pageLogin = pageHome.ClickSignInButton();

        PageMyAccount pageMyAccount = pageLogin.signIn(email, pwd);
        pageHome = pageMyAccount.clickHomeIcon();

        pageHome.addARandomProductToCart(cart);
        pageHome.selectContinueShopping();
        pageHome.addARandomProductToCart(cart);
        PageSummary pageSummary = pageHome.selectProceedToCheckOut();

//        double expectedPrice = 0;
//        for (Product prod : cart.products) {
//            expectedPrice += prod.price * prod.quantity;
//            System.out.println(prod.toString());
//        }
//        expectedPrice = Math.round(expectedPrice * 100);
//        expectedPrice = expectedPrice/100;
//
//        double actualPrice = pageSummary.getTotalProductPrice();
//        Assert.assertEquals(actualPrice, expectedPrice);
        result *= pageSummary.verifyAmount(cart);
        pageSummary.clickProceedToCheckout(driver);

        System.out.println("navigating to page address");
        PageAddress pageAddress = new PageAddress(driver);
        pageAddress.checkToUseDeliveryAddressAsBillingAddress();
        pageAddress.clickProceedToCheckout(driver);

        PageShipping pageShipping = new PageShipping(driver);
        pageShipping.checkToAgreeTermsAndCondition();
        pageShipping.clickProceedToCheckout(driver);

        PagePayment pagePayment = new PagePayment(driver);
        result *= pagePayment.verifyCartSummary(cart);

        Assert.assertEquals(result, 1);
    }
}
