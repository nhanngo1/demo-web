package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;

public class TestBase {
    public WebDriver driver;

    public void launchBrowser() {

        driver = new ChromeDriver();
        System.out.println("open site: " + new Date());
        driver.get("http://automationpractice.com/");
    }
}
