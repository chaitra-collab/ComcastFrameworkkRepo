package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	WebDriver driver;
	//asperrule3initialiaztaionshouldbedoneinconstructor
	public OrganizationInfoPage(WebDriver driver){//loadingtheobject//driverpassedfromtestscript
		//everytestscriptlaunchesnewbrowser//driverisocatothisconstructor
	//executionofallfindbyannotationandloadelementwithcurrentaddress 
		this.driver=driver;//getdriverobjectinpomclasses
		PageFactory.initElements(driver, this);//thisforcurrentobjectreference//LoginPage.class
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}

}
