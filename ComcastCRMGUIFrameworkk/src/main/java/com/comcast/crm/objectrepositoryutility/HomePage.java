package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	  
	WebDriver driver;
	//asperrule3initialiaztaionshouldbedoneinconstructor
	public HomePage(WebDriver driver){//loadingtheobject//driverpassedfromtestscript
		//everytestscriptlaunchesnewbrowser//driverisocatothisconstructor
	//executionofallfindbyannotationandloadelementwithcurrentaddress 
		this.driver=driver;//getdriverobjectinpomclasses
		PageFactory.initElements(driver, this);//thisforcurrentobjectreference//LoginPage.class
	}
	 @FindBy(linkText="Products")
     private WebElement productLink;
	 
     public WebElement getProductLink() {
		return productLink;
	}
	 @FindBy(linkText="Organizations")
     private WebElement orgLink;
     
     @FindBy(linkText="Contact")
     private WebElement contactLink;
     
     @FindBy(linkText="Campaigns")
     private WebElement campaignLink;
     
     @FindBy(linkText="More")
     private WebElement moreLink;
     
     @FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
     private WebElement adminImg;
     
     @FindBy(linkText="Sign Out")
     private WebElement signOutLink;
     
     
	 public WebElement getOrgLink() {
		 return orgLink;
	 }

	 public WebElement getContactLink() {
		 return contactLink;
	 }
     public WebDriver getDriver() {
		return driver;
	}

	 public WebElement getCampaignLink() {
		 return campaignLink;
	 }


	 public WebElement getMoreLink() {
		 return moreLink;
	 }
	 
   //gettersprovidesingleelementacccess//businessmethodprovidemultipleelementaccesss
	 
	 //businesslibrary
	 public void navigateToCampaignPage() {
		 Actions act=new Actions(driver);
		 act.moveToElement(moreLink).perform();
		 campaignLink.click();
	 }
     //businessmethodtologout
	 public void logOut() {
		 Actions act = new Actions(driver);
		 act.moveToElement(adminImg).perform();
		 signOutLink.click();
	 }
     
     
}
