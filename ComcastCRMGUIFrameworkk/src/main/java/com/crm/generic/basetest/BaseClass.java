package com.crm.generic.basetest;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {

	// provides utilityobjects
	/* createobject */
	public DataBaseUtility dbLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;// static

	@BeforeSuite(groups = { "smokeTest", "regressionTest" })
	public void configBS() throws SQLException {

		System.out.println("=====connect to DB,Report Config=====");
		dbLib.getDbconnection();

	}

	// @Parameters("BROWSER")
	@BeforeClass(groups = { "smokeTest", "regressionTest" })

	public void configBC() throws Throwable {
		System.out.println("=====Launch a browser===== ");
		// realtimewealwaysgetdatafromcommandlineandparameterfromruntimeJENKINSALWAYSTHEPARAMETERFRPOMCMDLINE
		// cd C:\Users\HP\git\Automation_TL\ComcastCRMGUIFrameworkk
		// toeexceutetetscriptfromcmdlinetopassparameteratruntime
		// mvn test -Durl=http://localhost:8888/index.php -Dbrowser=firefox
		// -Dusername=admin -Dpassword=admin

		// anotheroptionreceiveparameterfrompropertyfilefLib.getDataFromPropertiesFile("password")ifuserwantstoexeutefromeclipseforgotfrmcmdline
		// system.getpropertyusingdatafrmpropertyfilejenkinsalwaysusesystem.getpropertyruntimemvnparameterandpropertyfileframeworkbecomesflexible
		String BROWSER = System.getProperty("browser", fLib.getDataFromPropertiesFile("browser"));// toreaddatafromcmdline
		// toreaddatafromcommandlineString BROWSER =
		// fLib.getDataFromPropertiesFile("browser");
		// String BROWSER=browser;
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
	}

	@BeforeMethod(groups = { "smokeTest", "regressionTest" })
	public void configBM() throws Throwable {
		System.out.println("==login== ");

		String URL = System.getProperty("url", fLib.getDataFromPropertiesFile("url"));// toreaddatafromcmdline
		// String URL = fLib.getDataFromPropertiesFile("url");

		String USERNAME = System.getProperty("username", fLib.getDataFromPropertiesFile("username"));// toreaddatafromcmdline
		// String USERNAME = fLib.getDataFromPropertiesFile("username");

		String PASSWORD = System.getProperty("password", fLib.getDataFromPropertiesFile("password"));// toreaddatafromcmdline
		// String PASSWORD = fLib.getDataFromPropertiesFile("password");

		LoginPage lp = new LoginPage(driver);// constructortatkecareofinitialization
		lp.loginToapp(URL, USERNAME, PASSWORD);

	}

	@AfterMethod(groups = { "smokeTest", "regressionTest" })
	public void configAfterMethod() {
		System.out.println("==logout==");
		HomePage hp = new HomePage(driver);
		hp.logOut();

	}

	@AfterClass(groups = { "smokeTest", "regressionTest" })
	public void configAfterClass() {
		System.out.println("=====close browser=====");
		driver.quit();
	}

	@AfterSuite(groups = { "smokeTest", "regressionTest" })
	public void configAS() throws SQLException {
		System.out.println("====close DB,Report Backup====");
		dbLib.closeDbconnection();

	}

	// @BeforeTestrequiredifpreconditionreqbeforedongparallelexecution
}
