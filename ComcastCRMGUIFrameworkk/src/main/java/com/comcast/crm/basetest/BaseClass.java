package com.comcast.crm.basetest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	  /* create object */
	public DataBaseUtility dbLib=new DataBaseUtility();
	public FileUtility fLib=new FileUtility();
	public    ExcelUtility eLib=new ExcelUtility();
	public	JavaUtility jLib = new JavaUtility();
	public  WebDriver driver=null;
	public static WebDriver sdriver=null;
	
	


	@BeforeSuite(groups= {"smokeTest", "regressionTest"})
	public void configBS() throws SQLException {
		System.out.println("=====connect to DB,Report Config=====");
		dbLib.getDbconnection();
		
	}
	
	@Parameters("BROWSER")
	@BeforeClass(groups= {"smokeTest", "regressionTest"})
	public void configBC(String browser) throws Throwable {
		System.out.println("=====Launch a browser===== ");
		String BROWSER=browser;
				//fLib.getDataFromPropertiesFile("browser");
		
	         if(BROWSER.equals("chrome")) {
	        	 driver=new ChromeDriver();
	         }
	         else if(BROWSER.equals("firefox")) {
	        	 driver=new FirefoxDriver();
	         }else if(BROWSER.equals("edge")) {
	        	 driver=new EdgeDriver();
	         }else {
	        	 driver=new ChromeDriver();
	         }
	         sdriver=driver;
	         UtilityClassObject.setDriver(driver);
	}
	 @BeforeMethod(groups= {"smokeTest", "regressionTest"})
	    public void configBM() throws Throwable {
	    	System.out.println("==login== ");
	    	String URL=fLib.getDataFromPropertiesFile("url");
	    	String USERNAME=fLib.getDataFromPropertiesFile("username");
	    	String PASSWORD=fLib.getDataFromPropertiesFile("password");
	    	LoginPage lp=new LoginPage(driver);
	    	lp.loginToapp(URL, USERNAME, PASSWORD);
	    }
	 
	 @AfterMethod(groups= {"smokeTest", "regressionTest"})
		public void configAfterMethod() {
			System.out.println("==logout==");
			HomePage hp=new HomePage(driver);
			hp.logOut();
			
		}
	@AfterClass(groups= {"smokeTest", "regressionTest"})
	public void configAfterClass() throws SQLException {
		System.out.println("=====close browser=====");
		driver.quit();
	}
	@AfterSuite(groups= {"smokeTest", "regressionTest"})
	public void configAS() throws SQLException {
		System.out.println("====close DB,Report Backup====");
		dbLib.closeDbconnection();
		
	}
	//@BeforeTest//iftodopreconditionbefordoingparallelexecution
	

}
