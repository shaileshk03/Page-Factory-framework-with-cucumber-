package com.abc.helper.DropDown;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.abc.helper.Logger.LoggerHelper;

public class DropDownHelper {

	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger( DropDownHelper.class);
	
	
	public DropDownHelper(WebDriver driver) {
		this.driver=driver;
	}
	  // Select by visible text 
	public void SelectByVisibleText(WebElement element,String visibleValue){
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
		log.info("WebElement " + element + "Visible value" + visibleValue );
	}
	
	// Select first option
	public String getFirstSelectedOption(WebElement element){
		String value = new Select(element).getFirstSelectedOption().getText();
		log.info("WebElement " + element + "value" + value );
		return value;
	}
	
	// Select using index
	public void SelectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
		select.selectByIndex(index);
		log.info("WebElement " + element + "index" + index );
	}
	
	 // Select by value 
	public void SelectByValue(WebElement element, String value){
		Select select = new Select(element);
		select.selectByValue(value);
		log.info("WebElement " + element + "value" + value );
	}
	
	
	public void DeSelectByValue(WebElement element, String value){
		Select select = new Select(element);
		select.deselectByValue(value);
		log.info("WebElement " + element + "value" + value);
	}
		
	public List<String> getAllDropDownValue(WebElement locator){
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for(WebElement element: elementList){
		log.info("Element" + element.getText());	
		valueList.add(element.getText());
		}
		return valueList;
	 }

	
	
  }
