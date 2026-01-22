package com.comcast.crm.objectrepositoryutility;

import java.time.Duration;
/** 
 * @author Deepak
 * 
 * Contains Login Page elements & business library like login()
 * 
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility{//nameofclassshouldbenameofpagename
	//rule1 create a separate java class
	//rule2 object Creation using @Findby
	//createonemoredriverobjectglobal
	WebDriver driver;
	//asperrule3initialiaztaionshouldbedoneinconstructor
	public LoginPage(WebDriver driver){//loadingtheobject//driverpassedfromtestscript
		//everytestscriptlaunchesnewbrowser//driverisocatothisconstructor
	//executionofallfindbyannotationandloadelementwithcurrentaddress 
		this.driver=driver;//getdriverobjectinpomclasses
		PageFactory.initElements(driver, this);//thisforcurrentobjectreference//LoginPage.class
	}
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;//storethelemENT

	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	     //Rule3 : Object Initialization
	
	//Rule4: Object Encapsulation
		//mouserightclick//source//generategetters
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	/**
	 * login to application based on username,passowrd,url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	//rule5 :provide Action//bybusinesslibrary//specifictobusiness
	//thismethodcannotbeusedforotherapplication
	public void loginToapp(String url,String username , String password) {
		//driverobjectinsidebusinessmethod
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	
	
	
	
}
