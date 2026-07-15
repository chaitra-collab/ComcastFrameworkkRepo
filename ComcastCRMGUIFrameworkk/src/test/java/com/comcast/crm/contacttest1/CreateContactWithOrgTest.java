package com.comcast.crm.contacttest1;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPopUpPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility(); 
		         
		         String BROWSER = fLib.getDataFromPropertiesFile("browser");
		         String URL = fLib.getDataFromPropertiesFile("url");
		         String USERNAME = fLib.getDataFromPropertiesFile("username");
		         String PASSWORD =fLib.getDataFromPropertiesFile("password");
		      
		    
		         //read testScript data from Excel file
			      String orgName=eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();;
			      String contactLastName=eLib.getDataFromExcel("contact", 7, 3);
		        
		         
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
		         
		         LoginPage lp = new LoginPage(driver);
		         lp.loginToapp(URL, USERNAME, PASSWORD);
		      // Step 2: Click Organizations
		         HomePage hp = new HomePage(driver);
		         hp.getOrgLink().click();

		         // Step 3: Click Create Organization
		         OrganizationsPage op = new OrganizationsPage(driver);
		         op.getCreateNewOrgBtn().click();

		         // Step 4: Create Organization
		         CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		       

		         cnop.createOrg(orgName);
		         try {
		        	    String alertMsg = driver.switchTo().alert().getText();
		        	    System.out.println("Alert = " + alertMsg);
		        	    driver.switchTo().alert().accept();
		        	} catch (Exception e) {
		        	    System.out.println("No Alert");
		        	}

		         // Verify organization is created
		         Thread.sleep(2000);

		          hp = new HomePage(driver);

		         hp.getContactLink().click();

		         ContactPage cp = new ContactPage(driver);

		         cp.getCreateNewContactBtn().click();

		         CreatingNewContactPage ccp = new CreatingNewContactPage(driver);

		         ccp.createContactWithOrg(contactLastName);

		         wLib.switchToWindow(driver, "Accounts");

		         OrganizationPopUpPage opp = new OrganizationPopUpPage(driver);
		      // Switch to Organization popup
		         wLib.switchToWindowOnURL(driver, "module=Accounts");
		         // Search organization
		         opp.searchAndSelectOrganization(orgName);
		       
		         // Switch back to Contact page
		         wLib.switchToWindowOnURL(driver, "module=Contacts");
		       ccp = new CreatingNewContactPage(driver);

		         ccp.clickSave();

		         ContactInfoPage cip = new ContactInfoPage(driver);

		        

		        String actlastName= cip.getLastName().getText();
		        String actorgName=cip.getHeaderMsg3().getText();
		         if(actorgName.contains(orgName)) {
		        	 System.out.println(orgName+" is created==PASS");
		         }else {
		        	 System.out.println(orgName+ " is not created==FAIL");
		         }
		          if(actlastName.contains(contactLastName)) {
		         	 System.out.println(contactLastName+" is created==PASS");
		          }else {
		         	 System.out.println(contactLastName+ " is not created==FAIL");
		          }
		          
		          hp.logOut();
		          
		          driver.quit();
	}
}
						