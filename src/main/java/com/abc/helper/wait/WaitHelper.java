package com.abc.helper.wait;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abc.helper.Logger.LoggerHelper;

public class WaitHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);
	
	
	public WaitHelper(WebDriver driver) {
	  this.driver=driver;
	}
	
	public void setImplicitWait(long timeout, TimeUnit unit){
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS :unit);
	}
	
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMilSec){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.pollingEvery(pollingEveryInMilSec, TimeUnit.MILLISECONDS);
        wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
        return wait;
	}
	
    public void waitForElementVisible(WebElement locator,int timeOutInSeconds, int pollingEveryInMiliSec ){
    	WebDriverWait wait =  getWait(timeOutInSeconds,pollingEveryInMiliSec);
    	wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public void waitForElement(WebDriver driver, WebElement element, long timeOut){
    	WebDriverWait wait = new WebDriverWait(driver, timeOut);
    	wait.until(ExpectedConditions.visibilityOf(element));
    	log.info("Element is " +element.getText());
    }
    
    public WebElement waitForElement(WebDriver driver, long time, WebElement element){
    	WebDriverWait wait = new WebDriverWait(driver, time);
    	return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
}
