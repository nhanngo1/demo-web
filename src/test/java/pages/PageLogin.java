package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLogin extends PageBase{
    private WebDriver driver;

    @FindBy (id = "email")
    WebElement email;

    @FindBy (id = "passwd")
    WebElement password;

    @FindBy (id = "SubmitLogin")
    private WebElement signInButon;

    public PageLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PageMyAccount signIn(String email, String pwd){

        super.waitForVisibilityOfElement(driver, this.email);

        this.email.sendKeys(email);
        password.sendKeys(pwd);
        signInButon.click();

        return new PageMyAccount(driver);
    }
}
