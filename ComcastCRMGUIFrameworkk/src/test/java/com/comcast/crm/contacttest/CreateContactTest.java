package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
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
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactTest extends BaseClass {

	@Test(groups={"smokeTest"})
	public void createContactTest() throws IOException, Throwable {
		// read testScript data from Excel file
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// step2:navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		// step3:click on "create contact" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewOrgBtn().click();

		// step 4: enter all the details & create new contact
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(lastName);

		// verify Header orgName info Expected Result
		String actlastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actlastName.equals(lastName)) {
			System.out.println(lastName + " information is verified==PASS");
		} else {
			System.out.println(lastName + " information is not verified==FAIL");
		}

	}

	@Test(groups="regressionTest")
	public void createContactWithSupportDateTest() {
		// read testScript data from Excel file
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		// step 2: navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3: click on "create contact" Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewOrgBtn().click();

		// step 4: enter all the details & create new contact
		String endDate = jLib.getSystemDateYYYYDDMM();
		String startDate = jLib.getRequiredDateYYYYDDMM(30);
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContactWithSupportDate(lastName, startDate, endDate);

		// verify Header phone number info Expected Result
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (actStartDate.equals(startDate)) {
			System.out.println(startDate + " information is verified==PASS");
		} else {
			System.out.println(startDate + " information is not verified==FAIL");
		}

		String actendDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if (actendDate.equals(endDate)) {
			System.out.println(endDate + " information is verified==PASS");
		} else {
			System.out.println(endDate + " information is not verified==FAIL");
		}
	}

	@Test(groups="regressionTest")
	public void createContactWithOrgTest() throws  Throwable   {
		//read testScript data from Excel file
        
        String orgName =eLib.getDataFromExcel("contact", 7, 2)+jLib.getRandomNumber();
        String contactLastName = eLib.getDataFromExcel("contact", 7, 3);
        
        //step 2: navigate to organization module
   HomePage hp=new HomePage(driver);
   hp.getOrgLink().click();;
        
        //step 3: click on "create Organization" Button
    OrganizationsPage cnp=new OrganizationsPage(driver);
    cnp.getCreateNewOrgBtn().click();
        //step 4: enter all the details & create new  Organization
     CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
     cnop.createOrg(orgName);
         
         //verify Header orgName info Expected Result
         String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
         if(actOrgName.trim().equals(orgName)) {
        	 System.out.println(orgName+" is created==PASS");
         }else {
        	 System.out.println(orgName+ " is not created==FAIL");
         }
         

	}
	
}