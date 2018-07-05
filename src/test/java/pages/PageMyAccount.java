package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageMyAccount extends PageBase{

    private WebDriver driver;

    @FindBy(className = "icon-home")
    private WebElement homeicon;

    public PageMyAccount(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        super.waitForVisibilityOfElement(driver, this.PageName);
    }

    public String getPageName(){
        return PageName.getText();
    }

    public PageHome clickHomeIcon(){

        this.homeicon.click();
        return new PageHome(this.driver);
    }
}
