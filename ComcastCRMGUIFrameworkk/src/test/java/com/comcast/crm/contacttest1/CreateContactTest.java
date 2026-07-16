package com.comcast.crm.contacttest1;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
/**
 * @author Deepak
 * 
 */
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
import com.crm.generic.basetest.BaseClass;

public class CreateContactTest extends BaseClass {
//ctrlallctrlshiftf to align everything
//tochangetestngsuitefilenameclickonxmlfileandpressf2
	//xml and twoattributesversion1.1xmllangverencodingfoutf8englishversion utfcarryinginoffromxmltosleeniumtestinenglish
	//doctypeschemaurltestng..dtdtwhatalltagscanbeusedcustomtagsandtagsusage
	//toseethesuiteresultrefreshtheproject//test-outputemailable-report.htmlopeninthwebbrowser6testcases
	//batchexe//<suite <test <classes <class name=/> </classes> </test></suite>
	//groupexe//<suite <groups <run <include name="groupkey" /> <include name="RT"/></run</group<>test <classses <class name /> </classes><<test></suite></test 
	//groupkeybetweensuitetagandtesttag//everytestcasshuldhavegroupkey
	//givegroupnameforconfiguurationannotationsincextendsbaseclass //foralllconfigannotinbaseclassmentiongroupname(groups= {"smokeTest","regressionTest"})
	//sothatbaseclassparticipatesinallexecution
	//toexecutesomplextesstcasetobeexedcutedinbothgroupthenonetetstcaseprovidemutiplegroupusecurlubraces
	//regionalregressiontestafterimpactanalysisexecuteonlyimpactedtestcase
	//distributedparalleluseparallel=testandthreadcount=2increatingsuitefile//resultearlyadv//functinaltest//mutlipletestrunnerreducesuiteexectijme//enableparalleandthreadcuntandinsuitetgmaxthreadcountis5
	//inselenium3.14.59thradcounmaxis5//insleenium4maxthreadcountisnolimitation//crossbrpwsertestisnonfunctionaltetsing
	//selenniumbigadcsupportscrossbrowsertesting
	//crossplatformuneedseleniumgrid//crossbrowser
	//inpropertyfilewecankeeponlyonedata//tellbaseclassimgoingtopassparameterfromxmltakemyparametertoacievecrossbrowsertesting
	//testscriptextnedbaseclass//toreeivedatfromxmlintobaseclassuse@parametersannotationbaseclassinbeforeclassannotat
	//threaditselfpassingparametertoreceiveparamateruse@Parameters
	//useparametersbeforeclassandspecifykenameandcansendmultiplekeysbycommaandprovideargumentstoreceivevariableinpublicvoid(string browser)insteadofreadingfrompropertyfile 
	//usebrowservariabletoStringBROWSER
	//@Dataprovider,@ParameterstwowaystopasstheparameterintotestngTest,ethod
	
	@Test(groups ={"smokeTest","regressionTest"})
	public void createContactTest() throws Throwable {

		/* read testScript data from Excel file*/
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		/* step 2: navigate to contact module*/
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3: click on "create contact" Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// Step 4: Enter Last Name and create new Contact
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact(lastName);

		// verify Header orgName info Expected Result
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actlastName = cip.getLastName().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actlastName, lastName);

		//verify header infoexpectedresult
				String actHeader=cp.getHeaderMsg1().getText();
				boolean status = actHeader.contains(lastName);
				Assert.assertEquals(status, true);
				System.out.println("hello");//anymodificationandpushingtogitautomaticallystartsbuildinjenkins
				
	}

	//everytestcasesshouldbeindependentkkepplogininbeforemethodkeeplogoutinaftremethod
	@Test(groups ="regressionTest")
	public void createContactWithSupportDateTest() throws Throwable, Throwable {
		// read testScript data from Excel file
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYDDMM(30);

		// Home Page

		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// Contact Page

		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// Create Contact

		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);

		ccp.createContactWithSupportDate(lastName, startDate, endDate);

		// verify Header orgName info Expected Result
		ContactInfoPage cip = new ContactInfoPage(driver);

		String actstartDate = cip.getSupportStartDate().getText();
		String actEndDate = cip.getSupportEndDate().getText();

		if (actstartDate.equals(startDate)) {
			System.out.println(startDate + " information is verified==PASS");
		} else {
			System.out.println(startDate + " information is not verified==FAIL");
		}
		String actendDate = cip.getSupportEndDate().getText();

		if (actendDate.equals(endDate)) {
			System.out.println(endDate + " information is verified==PASS");
		} else {
			System.out.println(endDate + " information is not verified==FAIL");
		}

	}

	@Test(groups ="regressionTest")
	public void createContactWithOrgTest() throws Throwable, Throwable {

		// read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
	
		String contactLastName = eLib.getDataFromExcel("contact", 7, 3);

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

		String actlastName = cip.getLastName().getText();
		String actorgName = cip.getHeaderMsg3().getText();
		
		if (actorgName.contains(orgName)) {
			System.out.println(orgName + " is created==PASS");
		} else {
			System.out.println(orgName + " is not created==FAIL");
		}
		if (actlastName.contains(contactLastName)) {
			System.out.println(contactLastName + " is created==PASS");
		} else {
			System.out.println(contactLastName + " is not created==FAIL");
		}

	}

}
