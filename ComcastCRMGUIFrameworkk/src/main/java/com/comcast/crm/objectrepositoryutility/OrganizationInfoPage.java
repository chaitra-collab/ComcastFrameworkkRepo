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
	private WebElement headerMsg1;
	
	@FindBy(id="dtlview_Phone")
	private WebElement headerMsg2;
    
	@FindBy(id="dtlview_Industry")
	private WebElement headerMsg3;
	
	@FindBy(id="dtlview_Type")
	private WebElement headerMsg4;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getHeaderMsg1() {
		return headerMsg1;
	}

	public WebElement getHeaderMsg3() {
		return headerMsg3;
	}

	public WebElement getHeaderMsg4() {
		return headerMsg4;
	}

	public WebElement getHeaderMsg() {
		return headerMsg1;
	}

	public WebElement getHeaderMsg2() {
		return headerMsg2;
	}

}
