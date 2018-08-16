package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.fail;
import static utils.Common.captureScreenshot;


public class TestListener implements ITestListener {
    public WebDriver driver;
    public ExtentHtmlReporter reporter;
    public ExtentReports extent;
    private static Map extentLoggerMap = new HashMap();

    public ExtentTest logger;


    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    public void onStart(ITestContext iTestContext) {
        if (reporter == null) {
            String reportFullPath = System.getProperty("user.dir") + "/report/AutomationTestReport.html";
            System.out.println("create report: " + reportFullPath);
            reporter = new ExtentHtmlReporter(reportFullPath);
            reporter.config().setChartVisibilityOnOpen(true);
//            reporter.config().setTheme(Theme.DARK);
            reporter.config().setDocumentTitle("Automation Test Report");
            reporter.config().setEncoding("utf-8");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
    }

    public static ExtentTest getLogger() {
        return (ExtentTest) extentLoggerMap.get((int) (long) (Thread.currentThread().getId()));
    }

    // Create logger
    public void onTestStart(ITestResult iTestResult) {
        System.out.println(String.format("Start test method: %s - %s", iTestResult.getName(), "create logger."));
        String reportTestName = iTestResult.getName();
        String browser;

        try {
            browser = iTestResult.getTestContext().getCurrentXmlTest().getParameter("browser");
            if(browser.isEmpty()){
                browser = "chrome";
            }
        } catch (Exception ex){
            browser = "chrome";
        }

        reportTestName = reportTestName + "_" + browser;

        logger = extent.createTest(reportTestName);
        extentLoggerMap.put((int) (long) (Thread.currentThread().getId()), logger);
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("Finish test - write report");
        extent.flush();
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test succeed: " + iTestResult.getName());
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test failed: " + iTestResult.getName());
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test skipped: " + iTestResult.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio: " + iTestResult.getName());
    }

    public static void testReport(WebDriver driver, boolean isPassed, String passMessage, String failMessage, boolean isCaptureScreenshot) throws IOException {
        String screeshotPath = "";
        if (isCaptureScreenshot) {
            screeshotPath = getScreenshotPath(captureScreenshot(driver));
        }

        if (isPassed) {
            getLogger().log(Status.PASS, passMessage, MediaEntityBuilder.createScreenCaptureFromPath(screeshotPath).build());
        } else {
            getLogger().log(Status.FAIL, failMessage, MediaEntityBuilder.createScreenCaptureFromPath(screeshotPath).build());
        }
    }

    public static void testReport(WebDriver driver, boolean isPassed, String message, boolean isCaptureScreenshot) throws IOException {
        String screeshotPath = "";
        if (isCaptureScreenshot) {
            screeshotPath = getScreenshotPath(captureScreenshot(driver));
        }

        if (isPassed) {
            getLogger().log(Status.PASS, message, MediaEntityBuilder.createScreenCaptureFromPath(screeshotPath).build());
        } else {
            getLogger().log(Status.FAIL, message, MediaEntityBuilder.createScreenCaptureFromPath(screeshotPath).build());
        }
    }

    public static void testReport(WebDriver driver, Status status, String msgDetail, boolean isCaptureScreenshot) throws IOException {
        String screeshotPath = "";
        if (isCaptureScreenshot) {
            screeshotPath = getScreenshotPath(captureScreenshot(driver));
        }

        getLogger().log(status, msgDetail, MediaEntityBuilder.createScreenCaptureFromPath(screeshotPath).build());
    }

    public static void handleExceptionAndMarkFailResult(WebDriver driver, Exception exception) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            System.out.println(sStackTrace);

            testReport(driver, Status.FAIL, exception.getMessage() + "<br>StackTrace: " + sStackTrace.replace("\n", "<br>"), true);
            System.out.println("Exception: " + exception.getMessage());
            fail();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static String getScreenshotPath(String filePath) {

        return filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
    }
}
