package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Date;

public class TestBase {
    Capabilities chromeCapabilities = DesiredCapabilities.chrome();

    public void launchBrowser() {

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCapabilities);
        System.out.println("open site: " + new Date());
        driver.get("http://automationpractice.com/");
    }
}
