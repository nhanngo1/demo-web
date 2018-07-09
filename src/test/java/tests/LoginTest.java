package tests;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {

    public WebDriver driver;


    @Test
    public void LoginSuccessfully(){

        Dotenv dotenv = Dotenv.configure().directory("./").load();

        String email = dotenv.get("EMAIL");
        String pwd = dotenv.get("PASSWORD");
        String accountName = dotenv.get("USER_NAME");
        String url = dotenv.get("URL");

        driver = new ChromeDriver();
        driver.get(url);

        driver.findElement(By.cssSelector("a.login")).click();

        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(pwd);
        driver.findElement(By.id("SubmitLogin")).click();

        String userName = driver.findElement(By.cssSelector("a.account")).getText();
        Assert.assertEquals(userName, accountName);
    }
}







//WebDriverWait wait = new WebDriverWait(driver, 60);

//        System.out.println(wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.account"))).getText());