package testscrips;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver driver;

    public void launchBrowser() {

        //System.setProperty("webdriver.gecko.driver","D://selenium-webdriver//geckodriver.exe");
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        //driver.manage().window().maximize();
        System.out.println("open site: " + new Date());

        //driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //try {
            driver.get("http://automationpractice.com/");
            //driver.get("http://automationpractice.com/index.php?controller=authentication");
        //}catch (TimeoutException e){
            //System.out.println("timeout of get()");
        //}
    }
}
