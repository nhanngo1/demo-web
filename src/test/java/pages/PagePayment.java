package pages;

import model.Cart;
import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.commons.collections4.CollectionUtils;

import static utils.TestReport.testReport;

import java.util.ArrayList;
import java.util.List;

public class PagePayment extends PageBase {
    private WebDriver driver;
    @FindBy(id = "cart_summary")
    WebElement tblCartSummary;

    public PagePayment(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public int verifyCartSummary(Cart cart) {
        int totalProduct = 0;
        List<Product> actualSelectedProduct = new ArrayList<Product>();

        WebElement cartSummary = tblCartSummary.findElement(By.cssSelector("tbody"));
        List<WebElement> selectedProductRows = cartSummary.findElements(By.cssSelector("tr"));
        totalProduct = selectedProductRows.size();
        
        this.scrollToElement(driver, tblCartSummary);
        actualSelectedProduct = getSelectedProducts();
//        for (int i = 0; i < totalProduct; i++) {
//            actualSelectedProduct.add(getSelectedProduct(selectedProductRows.get(i)));
//        }
//        System.out.println(actualSelectedProduct.toString());
//        System.out.println(cart.products.toString());
//        System.out.println(CollectionUtils.isEqualCollection(actualSelectedProduct, cart.products));

        int actualSelectedProductSize = actualSelectedProduct.size();
        int expectedSelectedProductSize = cart.products.size();

        String log = "";

        boolean result = actualSelectedProductSize == expectedSelectedProductSize;
//        if (result == false) {
//            log = String.format("Expected: %d products are selected.<br>Actual: %d products are selected", actualSelectedProduct.size(), cart.products.size());
//            testReport(driver, false, log, true);
//            return 0;
//        } else {
            int count = 0;
            for (int i = 0; i < totalProduct; i++) {
                boolean stepResult = (actualSelectedProduct.get(i).name.equals(cart.products.get(i).name) &&
                        actualSelectedProduct.get(i).size.equals(cart.products.get(i).size) &&
                        actualSelectedProduct.get(i).color.equals(cart.products.get(i).color) &&
                        actualSelectedProduct.get(i).quantity == cart.products.get(i).quantity &&
                        actualSelectedProduct.get(i).price == cart.products.get(i).price &&
                        actualSelectedProduct.get(i).oldPrice == cart.products.get(i).oldPrice &&
                        actualSelectedProduct.get(i).discountPercent == cart.products.get(i).discountPercent);
                log += String.format("Expect: %s.<br>Actual: %s.<br><br>", actualSelectedProduct.get(i).toString(), cart.products.get(i).toString());

                if(stepResult == true)
                    count ++;
            }
            result = count == totalProduct;
            testReport(driver, result, log, true);

            if(result == true)
                return 1;
            return 0;
        //}
    }


    private List<Product> getSelectedProducts() {

        List<Product> actualSelectedProduct = new ArrayList<Product>();
        WebElement cartSummary = tblCartSummary.findElement(By.cssSelector("tbody"));
        List<WebElement> selectedProductRows = cartSummary.findElements(By.cssSelector("tr"));
        int totalProduct = selectedProductRows.size();

        System.out.println("totalProduct: " + totalProduct);


        for (int i = 0; i < totalProduct; i++) {
            WebElement eleName = selectedProductRows.get(i).findElement(By.cssSelector(".cart_description .product-name"));
            WebElement eleAttribute = selectedProductRows.get(i).findElement(By.cssSelector(".cart_description small:nth-child(3)"));
            WebElement eleQuantity = selectedProductRows.get(i).findElement(By.cssSelector(".cart_quantity.text-center"));
            WebElement elePrice;

            double pOldPrice = 0.00;
            int pDisountPercent = 0;
            try {
                elePrice = selectedProductRows.get(i).findElement(By.cssSelector(".cart_unit span.special-price"));
                WebElement eleDiscountPercent = selectedProductRows.get(i).findElement(By.cssSelector(".cart_unit span.price-percent-reduction"));
                WebElement eleOldPrice = selectedProductRows.get(i).findElement(By.cssSelector(".cart_unit span.old-price"));

                pDisountPercent = Integer.parseInt(eleDiscountPercent.getText().replace("%", "").trim());
                pOldPrice = Double.parseDouble(eleOldPrice.getText().replace("$", "").trim());
                System.out.println("pDisountPercent" + pDisountPercent);
                System.out.println("pOldPrice" + pOldPrice);

            } catch (Exception ex) {
                //System.out.println("pagePayment.getSelectedProduct() Err: " + ex.getMessage());
                elePrice = selectedProductRows.get(i).findElement(By.cssSelector(".cart_unit span.price"));
            }

            String pName = eleName.getText().trim();
            String pAttribute[] = eleAttribute.getText().replace("Color : ", "").replace(" Size : ", "").split(",");
            String pColor = pAttribute[0].trim();
            String pSize = pAttribute[1].trim();
            int pQuantity = Integer.parseInt(eleQuantity.getText().trim());
            double pPrice = Double.parseDouble(elePrice.getText().replace("$", "").trim());

            if (pOldPrice != 0.00 && pDisountPercent != 0)
                actualSelectedProduct.add(new Product(pName, pColor, pSize, pQuantity, pPrice, pDisountPercent, pOldPrice));
            actualSelectedProduct.add(new Product(pName, pColor, pSize, pQuantity, pPrice));
        }
        return actualSelectedProduct;
    }
}