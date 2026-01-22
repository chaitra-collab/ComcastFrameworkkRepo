package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class OrganizationsPage {
	
	WebDriver driver;
	//asperrule3initialiaztaionshouldbedoneinconstructor
	public OrganizationsPage(WebDriver driver){//loadingtheobject//driverpassedfromtestscript
		//everytestscriptlaunchesnewbrowser//driverisocatothisconstructor
	//executionofallfindbyannotationandloadelementwithcurrentaddress 
		this.driver=driver;//getdriverobjectinpomclasses
		PageFactory.initElements(driver, this);//thisforcurrentobjectreference//LoginPage.class
	}
	

	@FindBy(linkText="Organizations")
    private WebElement orgLink;  
	
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createNewOrgBtn;
	
	public WebElement UtilityClassObject.getTest() {
		return createNewOrgBtn;
	}	
	public WebElement getOrgLink() {
		 return orgLink;
	 }
			
		}

