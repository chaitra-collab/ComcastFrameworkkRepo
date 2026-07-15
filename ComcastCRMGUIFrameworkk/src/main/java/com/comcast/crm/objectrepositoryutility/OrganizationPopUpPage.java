package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPopUpPage {
	 WebDriver driver;

	    public OrganizationPopUpPage(WebDriver driver) {

	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    @FindBy(name="search_text")
	    private WebElement searchEdt;

	    @FindBy(name="search")
	    private WebElement searchBtn;

	    public void searchAndSelectOrganization(String orgName) {

	        searchEdt.clear();
	        searchEdt.sendKeys(orgName);

	        searchBtn.click();

	        driver.findElement(By.linkText(orgName)).click();

	        // DO NOT use driver.getCurrentUrl() here
	        // DO NOT click anything else here
	    }
	}

