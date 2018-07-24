package tests;

import io.github.cdimascio.dotenv.Dotenv;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PageHome;
import pages.PageLogin;
import pages.PageMyAccount;

public class LoginWithPageObjectTest extends TestBase {

    @Test
    public void loginSuccessfullyUsingPOM() {
        int result = 1;
        Dotenv dotenv = Dotenv.configure().directory("./").load();

        String email = dotenv.get("EMAIL");
        String pwd = dotenv.get("PASSWORD");
        String accountName = dotenv.get("USER_NAME");

        PageHome pageHome = new PageHome(driver);
        PageLogin pageLogin = pageHome.ClickSignInButton();

        PageMyAccount pageMyAccount = pageLogin.signIn(email, pwd);
        result *= pageMyAccount.verifyUserName(accountName);

        Assert.assertEquals(result, 1);
    }
}
