package com.abc.helper.Generic;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.abc.helper.Logger.LoggerHelper;

public class GenericHelper {

	private final Logger log = LoggerHelper.getLogger(GenericHelper.class);
		
	public String readValueFromElement(WebElement element){
		if(null == element){
			return null;
		}
		boolean displayed = false;
		try {
			displayed = isDisplayed(element);
		} catch (Exception e) {
			return null;
		}
		if(!displayed)
			return null;
		String text = element.getText();
		   return text;
		}
		
	public String readValueFromInput(WebElement element){
		if(null == element)
			return null;
		 if(!isDisplayed(element))
		  return null;
		  String value = element.getAttribute("value");
		  log.info("Element value " + value);
		  return value;
	}
	
	  public boolean isDisplayed(WebElement element){
		  try {
			element.isDisplayed();
			log.info("element is displayed " + element);
			return true;
		} catch (Exception e) {
			log.info(e);
			return false;
		}	  
	  }
	
	  protected String getDisplayedText(WebElement element){
		  if(null == element)
		    return null;
		  if(!isDisplayed(element))
	      return null;
		  return element.getText();
	  }
		
	
	  
	  
	}
	
	
	
	
	
	
	
	
	
	
	
	

