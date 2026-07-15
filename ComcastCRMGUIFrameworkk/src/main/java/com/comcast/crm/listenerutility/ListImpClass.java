package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.TestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import java.util.Date;

public class ListImpClass implements ITestListener, ISuiteListener {
	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;

	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		String time = new Date().toString().replace(" ", "_").replaceAll(":", "_");
		// spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add env information & create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}

	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println("========>" + result.getMethod().getMethodName() + ">==Start===");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName() + "===>STARTED<===");

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("========>" + result.getMethod().getMethodName() + ">===Ended==");
		test.log(Status.PASS, result.getMethod().getMethodName() + "===>COMPLETED<===");
	}

	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();

		// step-1:create an Object to EventFirig Webdriver
		// EventFiringWebDriver edriver=new EventFiringWebDriver(BaseClass.sdriver);
		// File srcFile=eDriver.getScreenshotAs(OutputType.FILE);

//		//step-2:use getScreenshotAs method to get file type of screenshot
		TakesScreenshot eDriver = (TakesScreenshot) BaseClass.sdriver;
		String filePath=eDriver.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replaceAll(":", "_");
        test.addScreenCaptureFromBase64String(filePath,testName+"_"+time);
		 test.log(Status.FAIL ,result.getMethod().getMethodName()+"===>FAILED<===");
	}

	public void onTestSkipped(TestResult result) {
		
	}
}
