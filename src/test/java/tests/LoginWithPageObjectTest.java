package tests;

import io.github.cdimascio.dotenv.Dotenv;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PageHome;
import pages.PageLogin;
import pages.PageMyAccount;

import static org.testng.Assert.assertEquals;
import static utils.TestListener.handleExceptionAndMarkFailResult;

public class LoginWithPageObjectTest extends TestBase {

    @Test
    public void loginSuccessfullyUsingPOM() {
        boolean result = true;

        Dotenv dotenv = Dotenv.configure().directory("./").load();

        String email = dotenv.get("EMAIL");
        String pwd = dotenv.get("PASSWORD");
        String accountName = dotenv.get("USER_NAME");

        try {
            PageHome pageHome = new PageHome(driver);
            PageLogin pageLogin = pageHome.ClickSignInButton();

            PageMyAccount pageMyAccount = pageLogin.signIn(email, pwd);
            result &= pageMyAccount.verifyUserName(accountName);

            Assert.assertTrue(result);
        } catch (Exception ex) {
            result = false;
            handleExceptionAndMarkFailResult(driver, ex);
        } finally {
            assertEquals(result, true);
        }

    }
}
