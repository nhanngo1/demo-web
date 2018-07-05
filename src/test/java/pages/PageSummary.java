package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageSummary extends PageBase{

    private WebDriver driver;

    @FindBy(css = "#cart_summary>tbody>tr")
    List<WebElement> selectedProducts;

    @FindBy(id = "total_product")
    WebElement totalProductPrice;

    @FindBy(linkText = "Proceed to checkout")
    public WebElement proceedToCheckout;

    public PageSummary(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public double getTotalProductPrice(){
        String totalProdPrice = totalProductPrice.getText();
        totalProdPrice = totalProdPrice.substring(1);
        return Double.parseDouble(totalProdPrice);
    }
}
