package pages;

import common.Product;
import common.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class PageHome extends PageBase{
    private WebDriver driver;

    @FindBy(id = "homepage-slider")
    WebElement slider;

    @FindBy(css = "a.login")
    WebElement signInButon;

    @FindBy(css = "a.homefeatured")
    WebElement popular;

    @FindBy(css = "a.blockbestsellers")
    WebElement bestSeller;

    @FindBy(id = "layer_cart")
    WebElement addToCartPopup;

    @FindBy(css = "#layer_cart div.clearfix div.layer_cart_cart div.button-container span")
    WebElement continueShopping;

    @FindBy(linkText = "Proceed to checkout")
    WebElement proceedToCheckout;

    public PageHome(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        super.waitForVisibilityOfElement(driver, this.slider);
    }

    public PageLogin ClickSignInButton(){
        signInButon.click();
        return new PageLogin(driver);
    }

    private String getActiveTabName(){
        WebElement activeTab = driver.findElement(By.cssSelector("#home-page-tabs li.active>a"));
        return activeTab.getText();
    }

    public PageHome addARandomProductToCart(){
        String activeTab = getActiveTabName();
        String selector = "";

        if(activeTab.toLowerCase().equals("popular")){
            selector = "#homefeatured li.ajax_block_product";
        }
        else {
            selector = "#blockbestsellers li.ajax_block_product";
        }

        List<WebElement> products = driver.findElements(By.cssSelector(selector));
        int productCount = products.size();

        Random rnd = new Random();
        int selectedProdIndex = rnd.nextInt(productCount);
        //selectedProdIndex = 6;
        WebElement selectedProduct = products.get(selectedProdIndex);

        String prodName = selectedProduct.findElement(By.cssSelector("a.product-name")).getText();
        String prodPriceInString = selectedProduct.findElement(By.cssSelector("div.right-block div.content_price span.price")).getText();
        prodPriceInString = prodPriceInString.substring(1);
        double prodPrice = Double.parseDouble(prodPriceInString);

        Product selectedProd = new Product(prodName, prodPrice, 1);

        Utility.Cart.add(selectedProd);

        Actions action = new Actions(driver);
        action.moveToElement(products.get(selectedProdIndex));
        action.build().perform();

        //products.get(selectedProdIndex).findElement(By.cssSelector("div.right-block a.button.ajax_add_to_cart_button.btn.btn-default")).click();
        products.get(selectedProdIndex).findElement(By.linkText("Add to cart")).click();

        waitForVisibilityOfElement(driver, addToCartPopup);
        return this;
    }

    public void selectContinueShopping(){
        continueShopping.click();
        waitForElementAttributeContains(driver, addToCartPopup, "style", "display: none");
    }

    public PageSummary selectProceedToCheckOut(){
        proceedToCheckout.click();
        return new PageSummary(driver);
    }
}