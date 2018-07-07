package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class TestBase {
    public WebDriver driver;

    public void launchBrowser()  {

//        driver = new ChromeDriver();
//        System.out.println("open site: " + new Date());
//        driver.get("http://automationpractice.com/");
        try {
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
            driver.get("http://automationpractice.com/");
        } catch (Exception ex){}
    }


}
