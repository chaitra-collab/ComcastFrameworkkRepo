package com.comcast.crm.orgtest;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listenerutility.ListImpClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createOrgTest() throws Throwable, Throwable {
        UtilityClassObject.getTest().log(Status.INFO,"read data from Excel");//executesinparallelexecution
		// read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		// step 2: navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO,"navigate to org page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();// gettersprovidesingleelementaccess//singleaction
		// hp.navigateToCampaignPage();//businessmethod//multipleelementaccess//multipleactions

		// step 3: click on "create Organization" Button
		  ListImpClass.test.log(Status.INFO,"navigate to create org page");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// step 4: enter all the details & create new Organization
		UtilityClassObject.getTest().log(Status.INFO,"create a new Org");

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
	    
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO,orgName+"===>create a new Org");
   
		// verify Header msg Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " name is verified ==PASS");
		} else {
			System.out.println(orgName + " name is not verified ==FAIL");
		}
	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithPhoneNumberTest() throws Throwable, Throwable {
		// read testScript data from Excel file

		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);

		// step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// step3:click on "create organization" button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();
		// step 4: enter all the details & create new Organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrgPhoneNumber(orgName, phoneNumber);
		// verify header msg expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + "name is verified===PASS");
		} else {
			System.out.println(orgName + "name is not verified===FAIL");
		}
		// verify Header orgName info Expected Result
		String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if (actOrgName.equals(orgName)) {
			System.out.println(orgName + " is created==PASS");
		} else {
			System.out.println(orgName + " is not created==FAIL");
		}
		// verify phone Number info Expected Result
		String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actPhoneNumber.equals(phoneNumber)) {
			System.out.println(phoneNumber + " is verified==PASS");
		} else {
			System.out.println(phoneNumber + " is not verified==FAIL");
		}

	}

	@Test(groups = "regressionTest")
	public void createOrgWithIndustriesTest() throws Throwable, Throwable {

		// read testScript data from Excel file

		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		// step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step 3: click on "create Organization" Button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// step 4: enter all the details & create new Organization
		UtilityClassObject.getTest().log(Status.INFO, "Create a new Org");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry, type);

		// verify the industries and type info
		String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actIndustries.equals(industry)) {
			System.out.println(industry + " information is verified==PASS");
		} else {
			System.out.println(industry + " information is not verified==FAIL");
		}
		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actType.equals(type)) {
			System.out.println(type + " information is verified==PASS");
		} else {
			System.out.println(type + " information is not verified==FAIL");
		}
	}
}
