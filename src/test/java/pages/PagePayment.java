package pages;

import model.Cart;
import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.commons.collections4.CollectionUtils;


import java.util.ArrayList;
import java.util.List;

public class PagePayment extends PageBase{
    private WebDriver driver;
    @FindBy(id = "cart_summary")
    WebElement tblCartSummary;

    public PagePayment(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void verifyCartSummary(Cart cart){
        int totalProduct = 0;
        List<Product> actualSelectedProduct = new ArrayList<Product>();

        WebElement cartSummary = tblCartSummary.findElement(By.cssSelector("tbody"));
        List<WebElement> selectedProductRows = cartSummary.findElements(By.cssSelector("tr"));
        totalProduct = selectedProductRows.size();

        for(int i = 0; i< totalProduct; i++) {
            actualSelectedProduct.add(getSelectedProduct(selectedProductRows.get(i)));
        }
        System.out.println(actualSelectedProduct.toString());
        System.out.println(cart.products.toString());
        System.out.println(CollectionUtils.isEqualCollection(actualSelectedProduct, cart.products));
    }


    private Product getSelectedProduct(WebElement tableRow){

        WebElement eleName = tableRow.findElement(By.cssSelector(".cart_description .product-name"));
        WebElement eleAttribute = tableRow.findElement(By.cssSelector(".cart_description small:nth-child(3)"));
        WebElement eleQuantity = tableRow.findElement(By.cssSelector(".cart_quantity.text-center"));
        WebElement elePrice;

        double pOldPrice = 0.00;
        int pDisountPercent = 0;
        try{
            elePrice = tableRow.findElement(By.cssSelector(".cart_unit span.special-price"));
            WebElement eleDiscountPercent = tableRow.findElement(By.cssSelector(".cart_unit span.price-percent-reduction"));
            WebElement eleOldPrice = tableRow.findElement(By.cssSelector(".cart_unit span.old-price"));

            pDisountPercent = Integer.parseInt(eleDiscountPercent.getText().replace("%", "").trim());
            pOldPrice = Double.parseDouble(eleOldPrice.getText().replace("$", "").trim());
            System.out.println("pDisountPercent" + pDisountPercent);
            System.out.println("pOldPrice" + pOldPrice);

        } catch (Exception ex){
            System.out.println("pagePayment.getSelectedProduct() Err: " + ex.getMessage());
            elePrice = tableRow.findElement(By.cssSelector(".cart_unit span.price"));
        }

        String pName = eleName.getText().trim();
        String pAttribute[] = eleAttribute.getText().replace("Color : ", "").replace(" Size : ", "").split(",");
        String pColor = pAttribute[0].trim();
        String pSize = pAttribute[1].trim();
        int pQuantity = Integer.parseInt(eleQuantity.getText().trim());
        double pPrice = Double.parseDouble(elePrice.getText().replace("$", "").trim());

        if(pOldPrice != 0.00 && pDisountPercent != 0)
            return new Product(pName, pColor, pSize, pQuantity, pPrice, pDisountPercent, pOldPrice);
        return new Product(pName, pColor, pSize, pQuantity, pPrice);
    }
}
