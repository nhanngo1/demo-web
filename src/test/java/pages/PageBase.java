package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageBase {

    //WebDriverWait wait;

    @FindBy(css = "h1.page-heading")
    WebElement lblPageName;

    @FindBy(linkText = "Proceed to checkout")
    WebElement proceedToCheckout;


    // wait for element basing on element visibility
    public void waitForVisibilityOfElement(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // wait for element basing on element attribute
    public void waitForElementAttributeContains(WebDriver driver, WebElement element, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    // wait for element enable
    public void waitForElementEnable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementPresent(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public String getLblPageName() {
        return lblPageName.getText();
    }

    public void scrollToElement(WebDriver driver, WebElement element) {
        //System.out.println("scrolling...");

        //System.out.println("element:" + element);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        //try{Thread.sleep(500);}catch (Exception ex){};

    }


    public void clickProceedToCheckout(WebDriver driver) {
        try {Thread.sleep(500); } catch (Exception ex) {};

        //proceedToCheckout = driver.findElement(By.linkText("Proceed to checkout"));
        proceedToCheckout = driver.findElement(By.cssSelector("button.button-medium > span"));
        scrollToElement(driver, proceedToCheckout);
        proceedToCheckout.click();
    }
}
