package com.comcast.crm.orgTest1;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationWithPhoneNumberTest {

	public static void main(String[] args) throws Throwable {
	     
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility(); 

		         
		         String BROWSER = fLib.getDataFromPropertiesFile("browser");
		         String URL = fLib.getDataFromPropertiesFile("url");
		         String USERNAME = fLib.getDataFromPropertiesFile("username");
		         String PASSWORD =fLib.getDataFromPropertiesFile("password");
		     
        
         
         //read testScript data from Excel file
	      String orgName=eLib.getDataFromExcel("org", 7, 2)+jLib.getRandomNumber();
	      String phoneNumber=eLib.getDataFromExcel("org", 7, 3);

      
         WebDriver driver=null;
         
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
         
         // step 1: login to app
 	    wLib.waitForPageToLoad(driver);
         driver.get(URL);
         
         LoginPage lp=new LoginPage(driver);
         lp.loginToapp(URL, USERNAME, PASSWORD);

         
         //step 2: navigate to organization module
         HomePage hp=new HomePage(driver);
         hp.getOrgLink().click();         
         //step 3: click on "create Organization" Button
         OrganizationsPage cnp=new OrganizationsPage(driver);
         cnp.getCreateNewOrgBtn().click();
         //step 4: enter all the details & create new  Organization
         CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		 cnop.createOrg(orgName, phoneNumber);
         
 
         //verify header phone Number info Expected Result
		 OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		 String actOrgName=oip.getHeaderMsg().getText();
         String actPhoneNumber=oip.getHeaderMsg2().getText();
         if(actOrgName.contains(orgName)) {
			 System.out.println(orgName + " name is verified ==PASS");
		 }else {
			 System.out.println(orgName + " name is not verified ==FAIL");
		 }
        
         if(actPhoneNumber.equals(phoneNumber)) {
        	 System.out.println(phoneNumber +" is verified==PASS");
         }else {
        	 System.out.println(phoneNumber +" is not verified==FAIL");
         }
         hp.logOut();
     
         driver.quit();
         
         }

         
	}

