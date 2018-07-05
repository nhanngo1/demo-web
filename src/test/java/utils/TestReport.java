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
            screeshotPath = extentTest.addScreenCapture(getScreenshotPathBasingOnEnv(captureScreenshot(driver)));
            passMessage += screeshotPath;
            failMessage += screeshotPath;
        }

        testReport(driver, isPassed, passMessage, failMessage);
    }

    // capture screenshot if result = failed
    public static void testReport(WebDriver driver, boolean isPassed, String testLog) {
        String screeshotPath = "";
        if(isPassed == false) {
            String filePath = getScreenshotPathBasingOnEnv(captureScreenshot(driver));
            screeshotPath = extentTest.addScreenCapture(filePath);
            testLog += screeshotPath;
        }
        testReport(driver, isPassed, testLog, testLog);
    }

    // capture screenshot if isCaptureScreenshot = true
    public static void testReport(WebDriver driver, LogStatus status, String msgDetail, boolean isCaptureScreenshot) {
        String screeshotPath = "";
        if (isCaptureScreenshot) {
            screeshotPath = extentTest.addScreenCapture(getScreenshotPathBasingOnEnv(captureScreenshot(driver)));
            msgDetail += screeshotPath;
        }

        extentTest.log(status, msgDetail);
    }

    // always capture screenshot
    public static void testReport(WebDriver driver, LogStatus status, String msgDetail) {
        String screeshotPath = "";
        screeshotPath = extentTest.addScreenCapture(getScreenshotPathBasingOnEnv(captureScreenshot(driver)));
        msgDetail += screeshotPath;

        extentTest.log(status, msgDetail);
    }

    public static void testReport(WebDriver driver, boolean isPassed, String passMessage, String failMessage) {
        if (isPassed) {
            extentTest.log(LogStatus.PASS, passMessage);
        } else {
            extentTest.log(LogStatus.FAIL, failMessage);
        }
    }

    public static void handleExceptionAndMarkFailResult(WebDriver driver, Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string
        System.out.println(sStackTrace);

        testReport(driver, LogStatus.FAIL, ex.getMessage() + "\nStackTrace: " + sStackTrace, true);
        System.out.println("Exception: " + ex.getMessage());
        fail();
    }

    private static String getScreenshotPathBasingOnEnv(String filePath){
        String jenkinsEnv = System.getProperty("runOnJenkins");
        System.out.println("jenkinsEnv"  + jenkinsEnv);

        if((jenkinsEnv == null)||(!jenkinsEnv.equals("true"))) {
            return filePath;
        }

        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
        return fileName;
    }
}
