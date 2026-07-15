package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewContactPage {
	 WebDriver driver;

	    public CreatingNewContactPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    @FindBy(name="lastname")
	    private WebElement lastNameEdt;

	    @FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	    private WebElement organizationLookupImg;

	    @FindBy(xpath="//input[@title='Save [Alt+S]']")
	    private WebElement saveBtn;

	

		@FindBy(name = "support_start_date")
		private WebElement supportStartDateEdt;

		@FindBy(name = "support_end_date")
		private WebElement supportEndDateEdt;
	    public WebElement getLastNameEdt() {
	        return lastNameEdt;
	    }
	    public WebElement getSupportStartDateEdt() {
			return supportStartDateEdt;
		}

		public WebElement getSupportEndDateEdt() {
			return supportEndDateEdt;
		}


	    public WebElement getOrganizationLookupImg() {
	        return organizationLookupImg;
	    }

	    public WebElement getSaveBtn() {
	        return saveBtn;
	    }
	    
	    

	    // Business Method
	    public void createContact(String lastName) {
	    	lastNameEdt.sendKeys(lastName);
	    	saveBtn.click();
	    }
	   
	    public void createContactWithOrg(String lastName) {

	        lastNameEdt.sendKeys(lastName);

	        organizationLookupImg.click();
	        
	    }
	    public void createContactWithSupportDate(String lastName,
				String startDate,
				String endDate) {

			lastNameEdt.sendKeys(lastName);
			supportStartDateEdt.clear();
			supportStartDateEdt.sendKeys(startDate);

			supportEndDateEdt.clear();
			supportEndDateEdt.sendKeys(endDate);

			saveBtn.click();
		}
	  

	    public void clickSave() {

	        saveBtn.click();
	    }
}
	
	 
	

	

	

	

