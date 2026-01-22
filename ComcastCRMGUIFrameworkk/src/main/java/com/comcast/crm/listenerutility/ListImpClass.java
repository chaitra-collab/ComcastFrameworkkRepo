package com.comcast.crm.listenerutility;

import java.io.File;

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
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

import sun.util.calendar.BaseCalendar.Date;


public class ListImpClass implements ITestListener, ISuiteListener{
	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		
		//Spark report config
				ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
				spark.config().setDocumentTitle("CRM Test Suite results");
				spark.config().setReportName("CRM Report");
				spark.config().setTheme(Theme.DARK);
				
				//add Env information & create test
				 report=new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "Windows-10");
				report.setSystemInfo("BROWSER", "CHROME-100");
	}
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		report.flush();
	}
	public void onTestStart(ITestResult result) {
		System.out.println("========>"+result.getMethod().getMethodName()+">==Start===");
		 test=report.createTest(result.getMethod().getMethodName());	
		 UtilityClassObject.setTest(test);
		 test.log(Status.INFO,result.getMethod().getMethodName()+"===>STARTED<===");

	}
    public void onTestSuccess(ITestResult result) {
		System.out.println("========>"+result.getMethod().getMethodName()+">===Ended==");
		 test.log(Status.PASS ,result.getMethod().getMethodName()+"===>COMPLETED<===");

		public void onTestFailure(ITestResult result) {
		String testName=result.getMethod().getMethodName();
		//step-1:create an Object to EventFirig Webdriver
//		EventFiringWebDriver edriver=new EventFiringWebDriver(BaseClass.sdriver);
//		//step-2:use getScreenshotAs method to get file type of screenshot
//		File srcFile=edriver.getScreenshotAs(OutputType.FILE);
		
		TakesScreenshot eDriver=(TakesScreenshot) BaseClass.sdriver;
		String filePath=eDriver.getScreenshotAs(OutputType.BASE64);
		
		String time=new Date().toString().replace(" ","_" ).replace(":","_" );
		test.addScreenCaptureFromBase64String(filePath, testName+"_"+time);
		 test.log(Status.FAIL ,result.getMethod().getMethodName()+"===>FAILED<===");
		}
		public void onTestSkipped(TestResult result) {
		}

		//step 3: Store screen on Local driver
		FileUtils.copyFile(srcFile, new File("./screenshot/"+testName+"+"+time+".png"));
		}
    }
}