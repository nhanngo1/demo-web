package pages;

import model.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import model.Cart;
import java.io.IOException;
import java.util.List;

import static utils.TestListener.testReport;

public class PageSummary extends PageBase{

    private WebDriver driver;

    @FindBy(css = "#cart_summary>tbody>tr")
    List<WebElement> selectedProducts;

    @FindBy(id = "total_product")
    WebElement totalProductPrice;

    @FindBy(linkText = "Proceed to checkout")
    public WebElement proceedToCheckout;

    @FindBy(id = "cart_title")
    WebElement txtCartTitle;

    public PageSummary(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public double getTotalProductPrice(){
        String totalProdPrice = totalProductPrice.getText();
        totalProdPrice = totalProdPrice.substring(1);
        return Double.parseDouble(totalProdPrice);
    }

    @Override
    public void clickProceedToCheckout(WebDriver driver) {
        try {Thread.sleep(500); } catch (Exception ex) {};
        super.scrollToElement(driver, this.proceedToCheckout);
        this.proceedToCheckout.click();
    }

    public boolean verifyAmount(Cart cart) throws IOException {

        double expectedPrice = 0;
        String log = "";
        boolean result = true;

        scrollToElement(driver, lblPageName);
        for (Product prod : cart.products) {
            expectedPrice += prod.price * prod.quantity;
            System.out.println(prod.toString());
        }
        expectedPrice = Math.round(expectedPrice * 100);
        expectedPrice = expectedPrice/100;

        double actualPrice = this.getTotalProductPrice();

        log = String.format("Amount without shipping fee.<br>Expect: %.2f.<br>Actual: %.2f.", expectedPrice, actualPrice);
        result = expectedPrice == actualPrice;
        testReport(driver, result, log, true);

        return result;
    }
}
