package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TestBase {
    public WebDriver driver;
    public static ResourceBundle rb;

    public void launchBrowser() {

        // get config
        rb = ResourceBundle.getBundle("config");
        String RUN_VIA_REMOTE_DRIVER = rb.getString("RUN_VIA_REMOTE_DRIVER");

        if(RUN_VIA_REMOTE_DRIVER.toUpperCase().equals("YES") == false) {
            driver = new ChromeDriver();
        } else {
            try {
                DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
                driver = new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"), chromeCapabilities);
            } catch (Exception ex) {
            }
        }
        driver.get("http://automationpractice.com/");
    }
}
