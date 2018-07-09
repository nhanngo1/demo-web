package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLogin extends PageBase{
    private WebDriver driver;

    @FindBy (id = "email")
    WebElement txtEmail;

    @FindBy (id = "passwd")
    WebElement txtPassword;

    @FindBy (id = "SubmitLogin")
    private WebElement btnSignIn;

    public PageLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PageMyAccount signIn(String email, String pwd){

        super.waitForVisibilityOfElement(driver, this.txtEmail);

        this.txtEmail.sendKeys(email);
        this.txtPassword.sendKeys(pwd);
        this.btnSignIn.click();

        return new PageMyAccount(driver);
    }
}
