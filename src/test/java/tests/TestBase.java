package tests;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.URL;


public class TestBase {
    public WebDriver driver;
    public String reportSuffix;

    @Parameters("browser")
    @BeforeMethod
    public void testMethodInit(@Optional("chrome") String browser) {
        System.out.println(String.format("Open browser: %s", browser));
        this.launchBrowser(browser);
    }

    @AfterMethod
    public void testCleanUp() {
        driver.quit();
    }

    private void launchBrowser(String browser) {

        Dotenv dotenv = Dotenv.configure().directory("./").load();
        String runRemoteDriver = dotenv.get("RUN_VIA_REMOTE_DRIVER");
        String url = dotenv.get("URL");

        if (runRemoteDriver.equalsIgnoreCase("true")) {
            try {
                DesiredCapabilities capabilities;

                switch (browser.toLowerCase()) {
                    case "ff":
                    case "firefox": {
                        capabilities = DesiredCapabilities.firefox();
                    }
                    break;

                    default: {
                        capabilities = DesiredCapabilities.chrome();
                    }
                }

                String hubUrl = dotenv.get("HUB_HOST");
                String hubPort = dotenv.get("HUB_PORT");
                System.out.println(String.format("%s:%s", hubUrl, hubPort));
                driver = new RemoteWebDriver(new URL("http://" + hubUrl + ":" + hubPort + "/wd/hub"), capabilities);
            } catch (Exception ex) {
            }
        } else {
            switch (browser.toLowerCase()) {
                case "ff":
                case "firefox": {
                    driver = new FirefoxDriver();
                }
                break;

                default: {
                    driver = new ChromeDriver();
                }
            }
        }
        driver.get(url);
    }
}
