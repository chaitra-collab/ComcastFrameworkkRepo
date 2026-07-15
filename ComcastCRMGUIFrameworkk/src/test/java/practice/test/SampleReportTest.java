package practice.test;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest {

	public ExtentReports report;

	@BeforeSuite
	public void configBS() {
		// spark report config

		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add env information & create test
		 report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");

	}

	@AfterSuite
	public void configAS() {
		report.flush();

	}

	@Test

	public void createContactTest() {
		
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888/index.php");
		
		TakesScreenshot eDriver=(TakesScreenshot)driver;
		//base64supportedbyextentsreportlocatesscreenshotlocat
		String filePath=eDriver.getScreenshotAs(OutputType.BASE64);

		ExtentTest test = report.createTest("createContactTest");

		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page ");
		test.log(Status.INFO, "create contact");
		if ("Hfddd".equals("HDFC")) {
			test.log(Status.PASS, "contact is created pass");
		} else {
        test.addScreenCaptureFromBase64String(filePath, "errorfile");
        }
		
       driver.close();
	}

	@Test

	public void createContactWithOrgTest() {

		ExtentTest test = report.createTest("createContactWithOrg");

		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page ");
		test.log(Status.INFO, "create contact");
		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "contact is created pass");
		} else {
			test.log(Status.FAIL, "contact is not created");
		}
		

	}

	@Test

	public void createContactWithPhoneNumberTest() {

		ExtentTest test = report.createTest("createContactWithPhoneNumberTest");

		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page ");
		test.log(Status.INFO, "create contact");
		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "contact is created pass");
		} else {
			test.log(Status.FAIL, "contact is not created");
		}
		

	}

}
