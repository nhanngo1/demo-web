package tests;

import io.github.cdimascio.dotenv.Dotenv;
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

    public void launchBrowser() {

        Dotenv dotenv = Dotenv.configure().directory("./").load();
        String RUN_VIA_REMOTE_DRIVER = dotenv.get("RUN_VIA_REMOTE_DRIVER");

        if(RUN_VIA_REMOTE_DRIVER.toUpperCase().equals("TRUE") == false) {
            driver = new ChromeDriver();
        } else {
            try {
                DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();

                String HUB_HOST = dotenv.get("HUB_HOST");
                String HUB_PORT = dotenv.get("HUB_PORT");
                System.out.println(HUB_HOST + HUB_PORT);
                driver = new RemoteWebDriver(new URL("http://" + HUB_HOST + ":" + HUB_PORT + "/wd/hub"), chromeCapabilities);
            } catch (Exception ex) {
            }
        }
        driver.get("http://automationpractice.com/");
    }


}
