package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import static utils.TestListener.testReport;

public class PageMyAccount extends PageBase{

    private WebDriver driver;

    @FindBy(className = "icon-home")
    private WebElement icoHome;

    @FindBy(css = "a.account")
    private WebElement lblUserName;

    public PageMyAccount(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        super.waitForVisibilityOfElement(driver, this.lblPageName);
    }

    public String getLblPageName(){
        return lblPageName.getText();
    }

    public PageHome clickHomeIcon(){

        this.icoHome.click();
        return new PageHome(this.driver);
    }

    public boolean verifyUserName(String expectedName) throws IOException{
        String actualName = lblUserName.getText();
        String log = String.format("Expect: name is \"%s\".<br>Actual: name is \"%s\".", expectedName, actualName);

        boolean result = actualName.equals(expectedName);
        testReport(driver, result, log, true);

        return result;
    }
}
