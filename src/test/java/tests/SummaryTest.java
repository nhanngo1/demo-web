package tests;

import model.Cart;
import model.Product;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.util.ArrayList;
import java.util.List;

public class SummaryTest extends TestBase{

    @BeforeMethod
    public void testMethodInit(){
        super.launchBrowser();
    }

    //@Test
    public void testSignInSuccessfull(){
        String email = "autotest@clearstep.33mail.com";
        String pwd = "P@ssword123";

        PageHome pageHome = new PageHome(driver);
        PageLogin pageLogin = pageHome.ClickSignInButton();

        PageMyAccount pageMyAccount = pageLogin.signIn(email, pwd);
        System.out.println(pageMyAccount.getLblPageName());

        Assert.assertEquals(pageMyAccount.getLblPageName(),"My Account");
    }

    @Test
    public void testSummanyInfo(){

        Cart cart = new Cart();
        String email = "autotest@clearstep.33mail.com";
        String pwd = "P@ssword123";

        PageHome pageHome = new PageHome(driver);
        PageLogin pageLogin = pageHome.ClickSignInButton();

        PageMyAccount pageMyAccount = pageLogin.signIn(email, pwd);
        pageHome = pageMyAccount.clickHomeIcon();

        pageHome.addARandomProductToCart(cart);
        pageHome.selectContinueShopping();
        pageHome.addARandomProductToCart(cart);
        PageSummary pageSummary = pageHome.selectProceedToCheckOut();

        double expectedPrice = 0;
        for (Product prod : cart.products) {
            expectedPrice += prod.price * prod.quantity;

            System.out.println(prod.toString());
        }
        expectedPrice = Math.round(expectedPrice * 100);
        expectedPrice = expectedPrice/100;

        double actualPrice = pageSummary.getTotalProductPrice();
        Assert.assertEquals(actualPrice, expectedPrice);

        pageSummary.clickProceedToCheckout(driver);

        System.out.println("navigating to page address");
        PageAddress pageAddress = new PageAddress(driver);
        pageAddress.checkToUseDeliveryAddressAsBillingAddress();
        pageAddress.clickProceedToCheckout(driver);

        PageShipping pageShipping = new PageShipping(driver);
        pageShipping.checkToAgreeTermsAndCondition();
        pageShipping.clickProceedToCheckout(driver);

        PagePayment pagePayment = new PagePayment(driver);
        pagePayment.verifyCartSummary(cart);
    }

    @AfterMethod
    public void testMethodCleanUp() {
        //driver.close();
    }

    @AfterTest
    public void testCleanUp() {
        //driver.quit();
    }
}
