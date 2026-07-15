package com.comcast.crm.objectrepositoryutility;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage extends WebDriverUtility {
	  
	WebDriver driver;
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	//asperrule3initialiaztaionshouldbedoneinconstructor
	public HomePage(WebDriver driver){//loadingtheobject//driverpassedfromtestscript
		//everytestscriptlaunchesnewbrowser//driverisocatothisconstructor
	//executionofallfindbyannotationandloadelementwithcurrentaddress 
		this.driver=driver;//getdriverobjectinpomclasses
		PageFactory.initElements(driver, this);//thisforcurrentobjectreference//LoginPage.class
	}
	 @FindBy(linkText="Products")
     private WebElement productLink;
	 
	 @FindBy(linkText="Email")
     private WebElement emailLink;
	 
     public WebElement getEmailLink() {
		return emailLink;
	}

	 public WebElement getAdminImg() {
		 return adminImg;
	 }

	 public WebElement getSignOutLink() {
		 return signOutLink;
	 }

	 public WebElement getProductLink() {
		return productLink;
	}
	 @FindBy(linkText="Organizations")
     private WebElement orgLink;
     
     @FindBy(linkText="Contacts")
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
	 
	 //businesslibrary//usdercannavigatetocampaignlinkbycallingonlyonemethod
	 public void navigateToCampaignPage() {
		 Actions act=new Actions(driver);
		 act.moveToElement(moreLink).perform();
		 campaignLink.click();
	 }
     //businessmethodtologout
//	 public void logOut() {
//		 waitForPageToLoad(driver);
//		 
//		
//		 Actions act = new Actions(driver);
//		 act.moveToElement(adminImg).pause(Duration.ofMillis(500)).perform();
//		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		 wait.until(ExpectedConditions.elementToBeClickable(signOutLink));
//		 signOutLink.click();
//	 }
	 
	 public void logOut() {

		    try {

		        Alert alert = driver.switchTo().alert();

		        System.out.println(alert.getText());

		        alert.accept();

		    } catch (Exception e) {

		    }

		    WebDriverWait wait =
		            new WebDriverWait(driver, Duration.ofSeconds(20));

		    wait.until(ExpectedConditions.visibilityOf(adminImg));

		    new Actions(driver)
		            .moveToElement(adminImg)
		            .perform();

		    wait.until(ExpectedConditions.elementToBeClickable(signOutLink));

		    signOutLink.click();
		}
}
