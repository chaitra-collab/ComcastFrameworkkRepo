package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
   
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void waitForElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void switchToTabOnURL(WebDriver driver, String partialURL) {
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		
		while(it.hasNext()) {
			String windowID=it.next();
			driver.switchTo().window(windowID);
			
			String actUrl=driver.getCurrentUrl();
			if(actUrl.contains(partialURL)) {
				break;
			}
		}
	}
	public void switchToWindowOnURL(WebDriver driver, String partialURL) {

	    Set<String> allWindows = driver.getWindowHandles();

	    for (String window : allWindows) {

	        driver.switchTo().window(window);

	        String url = driver.getCurrentUrl();

	        System.out.println("Current URL : " + url);

	        if (url.contains(partialURL)) {
	            break;
	        }
	    }
	}
	public void switchToTabOnTitle(WebDriver driver, String partialTitle) {
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		
		while(it.hasNext()) {
			String windowID=it.next();
			driver.switchTo().window(windowID);
			
			String actUrl=driver.getTitle();
			if(actUrl.contains(partialTitle)) {
				break;
			}
		}
	}
	//overloadedmethod
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String nameID) {
		driver.switchTo().frame(nameID);
	}
	
	public void switchToFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	public void switchToAlertAndCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();;
	}
	//overloadedmethod
	public void select(WebElement element,String text) {
		Select sel=new Select (element);
		sel.selectByVisibleText(text);
	}
	public void select(WebElement element,int text) {
		Select sel=new Select (element);
		sel.selectByIndex(text);
	}
	public void mousemoveOnElement(WebDriver driver,WebElement element ) {
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void doubleClick(WebDriver driver,WebElement element ) {
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	
		public void switchToWindow(WebDriver driver, String partialWindowTitle) {
			 Set<String> allWindowIds = driver.getWindowHandles();

			    for (String windowId : allWindowIds) {

			        driver.switchTo().window(windowId);

			        String title = driver.getTitle();

			        System.out.println("Current Window Title : " + title);

			        if (title.contains(partialWindowTitle)) {
			            break;
			        }
			    }
		}
}

