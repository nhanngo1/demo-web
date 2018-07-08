package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageAddress extends PageBase{

    private WebDriver driver;

    @FindBy(id = "addressesAreEquals")
    WebElement chkDeliveryAddressAsBillingAddress;

    public PageAddress(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PageAddress checkToUseDeliveryAddressAsBillingAddress(){
        if(chkDeliveryAddressAsBillingAddress.isSelected() == false){
            chkDeliveryAddressAsBillingAddress.click();
        }

        return this;
    }
}
