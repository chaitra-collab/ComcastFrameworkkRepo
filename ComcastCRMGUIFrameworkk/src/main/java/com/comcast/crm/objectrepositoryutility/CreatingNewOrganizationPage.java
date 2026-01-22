package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {

	WebDriver driver;

	// asperrule3initialiaztaionshouldbedoneinconstructor
	public CreatingNewOrganizationPage(WebDriver driver) {// loadingtheobject//driverpassedfromtestscript
		// everytestscriptlaunchesnewbrowser//driverisocatothisconstructor
		// executionofallfindbyannotationandloadelementwithcurrentaddress
		this.driver = driver;// getdriverobjectinpomclasses
		PageFactory.initElements(driver, this);// thisforcurrentobjectreference//LoginPage.class
	}

	@FindBy(name = "accountname")
	private WebElement orgNameEdit;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="accounttype")
	private WebElement typeDB;

	@FindBy(name = "industry")
	private WebElement industryDB;

	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	// businessmethod//enternameandclickonsave//multipleactions
	public void createOrg(String orgName) {
		orgNameEdit.sendKeys(orgName);
		saveBtn.click();
	}

	public void createOrg(String orgName, String industry,String type) {
		// methodnameissame//implementationissame//bothperformingcreatingorganization
		// methodoverloadingconcept//duringdevelopmentofscriptinpomclasseswecanprovideoverloadedmethods
		// onemethodrequiredforonetestscript
		orgNameEdit.sendKeys(orgName);
		
		Select sel1 = new Select(industryDB);
		sel1.selectByVisibleText(industry);
		saveBtn.click();
		
		Select sel2 = new Select(typeDB);
		sel2.selectByVisibleText(type);
		saveBtn.click();
		

	}

}
