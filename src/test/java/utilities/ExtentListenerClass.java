package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;


public class ExtentListenerClass implements ITestListener {

	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	public void configureReport() {
		BaseClass readconfig=new BaseClass();
		String timestamp=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		String reportName="MyStoreV1TestReport -" + timestamp+".html";
		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"//Reports//"+reportName);
		reports=new ExtentReports();
		reports.attachReporter(htmlReporter);

		//add system information/environment
		reports.setSystemInfo("Machine : ", "testpc1");
		reports.setSystemInfo("os : ", "window 11");
		//reports.setSystemInfo("browser : ", readconfig.getBrowser());
		reports.setSystemInfo("user name : ", "Sajid");
		//configuration to change look and feel of report
		htmlReporter.config().setDocumentTitle("ExtentListenerReportDemo");
		htmlReporter.config().setReportName("Automation Documentation Report");
		htmlReporter.config().setTheme(Theme.DARK);

	}
	public void onStart(ITestContext Result) {

		configureReport();
		System.out.println("on the Start method invked...");
	}
	public void onFinish(ITestContext Result) {

		System.out.println("on the Finished method invked...");
		reports.flush();
	}
	public void onTestFaillure(ITestResult Result) {

		System.out.println("Name of test method failed : " + Result.getClass().getName());
		test = reports.createTest(Result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: "+ Result.getClass().getName() , ExtentColor.RED));

		String screenShotPath=System.getProperty("user.dir")+" \\Screenshots\\" + Result.getClass().getName()+".png";
		File screenShotFile = new File(screenShotPath);
		if (screenShotFile.exists()) {
			test.fail("Captured Screenshot is below : " +test.addScreenCaptureFromPath(screenShotPath));
		}
	}

	public void onTestSkipped(ITestResult Result) {

		System.out.println("Name of test method skipped : " + Result.getClass().getName());
		test = reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the Skipped test case is: "+ Result.getClass().getName() , ExtentColor.YELLOW));
	}
	public void onTestStart(ITestContext Result) {

		System.out.println("Name of the test method started : " +Result.getClass().getName());
	}
	public void onTestSuccess(ITestResult Result) {
	
		System.out.println("Name of test method Passed : " + Result.getClass().getName());
		test = reports.createTest(Result.getTestClass().getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the Passed test case is: "+ Result.getName() , ExtentColor.GREEN));
		//String screenShotPath=System.getProperty("user.dir")+" \\Screenshots\\" + Result.getName()+".png";
	}

	public void onTestFailedButWithinSuccessPercentage(ITestContext Result) {

	}
}