package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Common {

    public static String captureScreenshot(WebDriver driver) {
        String filePath = "";

        try {
            String workingDir = System.getProperty("user.dir");
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            filePath = workingDir + "/report/" + generateUniqueString() + ".png";
            FileUtils.copyFile(scrFile, new File(filePath));
        } catch (Exception e) {
            System.out.println("captureScreenshot exception: " + e.getMessage());
        } finally {
            return filePath;
        }
    }

    public static String captureScreenshot1(WebDriver driver) {
        String fileName = "";
        try {

            fileName = "./" + generateUniqueString() + ".png";
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (Exception e) {

        } finally {
            return fileName;
        }
    }

    public static String generateUniqueString() {
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss_SSS").format(Calendar.getInstance().getTimeInMillis());

        return timeStamp;
    }
}
