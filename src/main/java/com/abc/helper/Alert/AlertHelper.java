package com.abc.helper.Alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.abc.helper.Logger.LoggerHelper;

public class AlertHelper {

	private WebDriver driver;
	
	private Logger log = LoggerHelper.getLogger(AlertHelper.class);
		
	public AlertHelper(WebDriver driver) {
		this.driver=driver;
		log.info("Alert Helper "+ driver.hashCode());
	}
	
	public Alert getAlert(){
	 return driver.switchTo().alert();
	}
	
	public void AcceptAlert(){
		getAlert().accept();
	}
	
	public void dismissAlert(){
		getAlert().dismiss();
	}
	
	public String getAlertText(){
		String text = getAlert().getText();
		log.info(text);
		return text;
	}
	
	public boolean isAlertPresent(){
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	
	public void acceptAlertIfPresent(){
		if(!isAlertPresent())
		  return;
		dismissAlert();
	}
	
	public void acceptPropt(String text){
		if(!isAlertPresent())
			return;
		Alert alert = getAlert();
		alert.sendKeys(text);
	    alert.accept();	
	}
	
}
