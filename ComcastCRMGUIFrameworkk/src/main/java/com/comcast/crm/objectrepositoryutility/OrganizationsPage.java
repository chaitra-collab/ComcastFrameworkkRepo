package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
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
	//INPOMWECANTHANDEDYNAMIC ELEMENTS

	@FindBy(linkText="Organizations")
    private WebElement orgLink;  
	
public WebElement getSearchEdt() {
		return searchEdt;
	}


	public void setSearchEdt(WebElement searchEdt) {
		this.searchEdt = searchEdt;
	}


	public WebElement getSearchDD() {
		return searchDD;
	}


	public void setSearchDD(WebElement searchDD) {
		this.searchDD = searchDD;
	}

@FindBy(name="search_text")
private WebElement searchEdt;

@FindBy(name="search_field")
private WebElement searchDD;


@FindBy(name="submit")
private WebElement searchBtn;

	public WebElement getSearchBtn() {
	return searchBtn;
}


	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}


	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createNewOrgBtn;
	
	public void deleteOrganization(WebDriver driver, String orgName) {
	    driver.findElement(
	        By.xpath("//a[text()='"+orgName+"']/../../td[8]//a[text()='del']")
	    ).click();

	    driver.switchTo().alert().accept();
	}
//	public WebElement UtilityClassObject.getTest() {
//		return createNewOrgBtn;

			
		}

