package tests;

import utils.Product;
import utils.Utility;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

//public class SummaryTest extends TestBase{
public class SummaryTest extends TestBase {

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
        System.out.println(pageMyAccount.getPageName());

        Assert.assertEquals(pageMyAccount.getPageName(),"My Account");
    }

    @Test
    public void testSummanyInfo(){

        String email = "autotest@clearstep.33mail.com";
        String pwd = "P@ssword123";

        PageHome pageHome = new PageHome(driver);
        PageLogin pageLogin = pageHome.ClickSignInButton();

        PageMyAccount pageMyAccount = pageLogin.signIn(email, pwd);
        pageHome = pageMyAccount.clickHomeIcon();

        pageHome.addARandomProductToCart();
        pageHome.selectContinueShopping();
        pageHome.addARandomProductToCart();
        PageSummary pageSummary = pageHome.selectProceedToCheckOut();

        double expectedPrice = 0;
        for (Product prod : Utility.Cart) {
            expectedPrice += prod.price * prod.quantity;
            System.out.println(prod.toString());
        }
        expectedPrice = Math.round(expectedPrice * 100);
        expectedPrice = expectedPrice/100;

        double actualPrice = pageSummary.getTotalProductPrice();
        Assert.assertEquals(actualPrice, expectedPrice);

        pageSummary.scrollToElement(driver, pageSummary.proceedToCheckout);
        pageSummary.proceedToCheckout.click();
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
