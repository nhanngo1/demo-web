package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageBase {

    @FindBy(css = "h1.page-heading")
    WebElement PageName;

    // wait for element basing on element visibility
    public void waitForVisibilityOfElement(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // wait for element basing on element attribute
    public void waitForElementAttributeContains(WebDriver driver, WebElement element, String attribute, String value){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public String getPageName(){
        return PageName.getText();
    }

    public void scrollToElement(WebDriver driver, WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try{Thread.sleep(500);}catch (Exception ex){};
    }
}
