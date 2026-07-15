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
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.listenerutility.ListImpClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.crm.generic.basetest.BaseClass;


@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class CreateOrganizationTest extends BaseClass {

	@Test(groups ="smokeTest")

	public void createOrganizationTest() throws Throwable {
		//toparticipateinparrallelexe//withlogmsg
		UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");

		// read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		// step 2: navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step 3: click on "create Organization" Button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// step 4: enter all the details & create new Organization
		UtilityClassObject.getTest().log(Status.INFO, "create org");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO, orgName + "====>Create a new Org");
		
		// verify Header msg Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " name is verified ==PASS");
		} else {
			System.out.println(orgName + " name is not verified ==FAIL");
		}

	}

	@Test(groups ="regressionTest")

	public void createOrganizationWithPhoneNumber() throws IOException, Throwable {
		UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
		// read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);

		// step 2: navigate to organization module
	
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// step 3: click on "create Organization" Button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();
		// step 4: enter all the details & create new Organization
		UtilityClassObject.getTest().log(Status.INFO, "create org");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, phoneNumber);
		UtilityClassObject.getTest().log(Status.INFO, orgName + "====>Create a new Org with phonenumber");

		// verify header phone Number info Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		boolean status = actOrgName.contains(orgName);
		Assert.assertEquals(status, true);

		String actPhoneNumber = oip.getHeaderMsg2().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actPhoneNumber, phoneNumber);
		

	}

	@Test(groups ="regressionTest")
	public void createOrganizationWithType() throws IOException, Throwable {
		UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
		// read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);
	
		// step 2: navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// step 3: click on "create Organization" Button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();
		// step 4: enter all the details & create new Organization
		UtilityClassObject.getTest().log(Status.INFO, "create org");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry, type);
		UtilityClassObject.getTest().log(Status.INFO, orgName + "====>Create a new Org with type");
		// verify the industries and type info
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		String actIndustries = oip.getHeaderMsg3().getText();
		String actType = oip.getHeaderMsg4().getText();

		if (actIndustries.equals(industry)) {
			System.out.println(industry + " information is verified==PASS");
		} else {
			System.out.println(industry + " information is not verified==FAIL");
		}
		if (actType.equals(type)) {
			System.out.println(type + " information is verified==PASS");
		} else {
			System.out.println(type + " information is not verified==FAIL");
		}

		
	}
}
