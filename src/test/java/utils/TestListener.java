package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;


public class TestListener implements ITestListener {
    private static ExtentReports extentReports;


    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    public void onStart(ITestContext iTestContext) {
        System.out.println("Start tests");
        if (TestReport.extentTest == null) {
            String workingDir = System.getProperty("user.dir");
            System.out.println("workingDir: " + workingDir);
            extentReports = new ExtentReports(workingDir + "/report/AutomationTestReport.html", true, NetworkMode.ONLINE);
            extentReports.loadConfig(new File("report-config.xml"));
        }
    }


    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Start test method: " + iTestResult.getName());
        TestReport.extentTest = extentReports.startTest(iTestResult.getName());
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("Finish test");
        extentReports.endTest(TestReport.extentTest);
        extentReports.flush();
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
}



