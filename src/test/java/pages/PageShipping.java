package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageShipping extends PageBase{
    private WebDriver driver;

    @FindBy(id = "cgv")
    WebElement chkAgreeTermsAndCondition;

    @FindBy(css = ".delivery_option_price")
    WebElement lblShipingFee;

    public PageShipping(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PageShipping checkToAgreeTermsAndCondition(){
        if(chkAgreeTermsAndCondition.isSelected() == false){
            chkAgreeTermsAndCondition.click();
        }
        return this;
    }

    public String getShippingFee(){
        String shippingFee = lblShipingFee.getText();
        return shippingFee.substring(1, shippingFee.length());
    }
}
