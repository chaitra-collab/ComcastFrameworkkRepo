package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	WebDriver driver;
	
	public ContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText="Contacts")
    private WebElement contactsLink;
	@FindBy(className  = "dvHeaderText")
	private WebElement headerMsg1;
    
	

	@FindBy(xpath = "//img[@alt='Create Contact...']")
	private WebElement createNewContactBtn;
	
	 public WebElement getHeaderMsg1() {
			return headerMsg1;
		}
	public WebElement getCreateNewContactBtn() {
		return createNewContactBtn;
	}

	public WebElement getContactLink() {
		return contactsLink;
	}


	



	
	
}
