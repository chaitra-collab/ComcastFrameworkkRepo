package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	WebDriver driver;
	//asperrule3initialiaztaionshouldbedoneinconstructor
	public ContactInfoPage(WebDriver driver){//loadingtheobject//driverpassedfromtestscript
		//everytestscriptlaunchesnewbrowser//driverisocatothisconstructor
	//executionofallfindbyannotationandloadelementwithcurrentaddress 
		this.driver=driver;//getdriverobjectinpomclasses
		PageFactory.initElements(driver, this);//thisforcurrentobjectreference//LoginPage.class
	}
	
	

	@FindBy(id="dtlview_Last Name")
	private WebElement lastName;
	@FindBy(id="mouseArea_Organization Name")
	private WebElement headerMsg3;
	@FindBy(id="dtlview_Support Start Date")
	private WebElement supportStartDate;
	@FindBy(id="dtlview_Support End Date")
	private WebElement supportEndDate;
	 @FindBy(xpath = "//input[@title='Save [Alt+S]']")
	    private WebElement saveBtn;
	 
	
	


	 public WebElement getLastName() {
		 return lastName;
	 }


	 public WebElement getHeaderMsg3() {
		 return headerMsg3;
	 }


	 public WebElement getSupportStartDate() {
		 return supportStartDate;
	 }


	 public WebElement getSupportEndDate() {
		 return supportEndDate;
	 }


	 public WebElement getSaveBtn() {
		 return saveBtn;
	 }


	
}
