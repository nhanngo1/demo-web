package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Date;

public class TestBaseGrid {
    DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
    public WebDriver driver;
    public void launchBrowser() {
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCapabilities);
            System.out.println("open site: " + new Date());
            driver.get("http://automationpractice.com/");
        } catch (Exception ex){}
    }
}
