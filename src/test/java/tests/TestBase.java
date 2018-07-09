package tests;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class TestBase {
    public WebDriver driver;

    @BeforeMethod
    public void testMethodInit(){
        this.launchBrowser();
    }


    @AfterTest
    public void testCleanUp() {
        //driver.quit();
    }

    public void launchBrowser() {

        Dotenv dotenv = Dotenv.configure().directory("./").load();
        String runRemoteDriver = dotenv.get("RUN_VIA_REMOTE_DRIVER");

        if(runRemoteDriver.toUpperCase().equals("TRUE") == false) {
            driver = new ChromeDriver();
        } else {
            try {
                DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();

                String hubUrl = dotenv.get("HUB_HOST");
                String hubPort = dotenv.get("HUB_PORT");
                System.out.println(hubUrl + hubPort);
                driver = new RemoteWebDriver(new URL("http://" + hubUrl + ":" + hubPort + "/wd/hub"), chromeCapabilities);
            } catch (Exception ex) {
            }
        }
        driver.get("http://automationpractice.com/");
    }
}
