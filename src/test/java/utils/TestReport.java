package utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.testng.Assert.fail;
import static utils.Common.captureScreenshot;

public class TestReport {

    public static ExtentTest extentTest;

    public static void testReport(WebDriver driver, boolean isPassed, String passMessage, String failMessage, boolean isCaptureScreenshot) {
        String screeshotPath = "";
        if (isCaptureScreenshot) {
            screeshotPath = extentTest.addScreenCapture(getScreenshotPath(captureScreenshot(driver)));
            passMessage += screeshotPath;
            failMessage += screeshotPath;
        }

        if (isPassed) {
            extentTest.log(LogStatus.PASS, passMessage);
        } else {
            extentTest.log(LogStatus.FAIL, failMessage);
        }
    }

    public static void testReport(WebDriver driver, boolean isPassed, String message, boolean isCaptureScreenshot) {
        String screeshotPath = "";
        if (isCaptureScreenshot) {
            screeshotPath = extentTest.addScreenCapture(getScreenshotPath(captureScreenshot(driver)));
            message += screeshotPath;
        }

        if (isPassed) {
            extentTest.log(LogStatus.PASS, message);
        } else {
            extentTest.log(LogStatus.FAIL, message);
        }
    }

    public static void testReport(WebDriver driver, LogStatus status, String msgDetail, boolean isCaptureScreenshot) {
        String screeshotPath = "";
        if (isCaptureScreenshot) {
            screeshotPath = extentTest.addScreenCapture(getScreenshotPath(captureScreenshot(driver)));
            msgDetail += screeshotPath;
        }

        extentTest.log(status, msgDetail);
    }

    public static void handleExceptionAndMarkFailResult(WebDriver driver, Exception ex){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string
        System.out.println(sStackTrace);

        testReport(driver, LogStatus.FAIL, ex.getMessage() + "<br>StackTrace: " + sStackTrace.replace("\n", "<br>"), true);
        System.out.println("Exception: " + ex.getMessage());
        fail();
    }

    private static String getScreenshotPath(String filePath){

        return filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
    }
}
