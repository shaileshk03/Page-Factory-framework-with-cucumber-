package com.abc.helper.Browser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.abc.helper.Logger.LoggerHelper;

 public class BrowserHelper {

	private static WebDriver driver;
	private Logger log = LoggerHelper.getLogger(BrowserHelper.class);
	
	
	public BrowserHelper(WebDriver driver) {
	  this.driver=driver;
	  log.debug("BrowserHelper " + this.driver.hashCode());	
	}
	
	public void goBack(){
		driver.navigate().back();
	    log.info("");
	}
	
	public void goForward(){
		driver.navigate().forward();
	}
	
	public void refresh(){
		driver.navigate().refresh();
	}
		
	public void resizeBrowser(){
		Dimension d =  new Dimension(800, 480);
		driver.manage().window().setSize(d);
	}
	
	public Point getLocation(WebElement element){
		return element.getLocation();
	}
	
	public Set<String> getWindowHandles(){
		return driver.getWindowHandles();
	}
	
    public void switchToWindow(int index){
    	LinkedList<String> windowsId= new LinkedList<String>(getWindowHandles());
    	if(index < 0 || index > windowsId.size())
    		throw new IllegalArgumentException("Invalid index :" + index);
        driver.switchTo().window(windowsId.get(0));
        log.info(index);
    }
	
   public void switchToParentWindow(){
	   LinkedList<String> windowsId= new LinkedList<String>(getWindowHandles());
	   driver.switchTo().window(windowsId.get(0));   
      }
  
 
    public void switchToParentWithChildClose(){
    	switchToParentWindow();
    	 LinkedList<String> windowsId= new LinkedList<String>(getWindowHandles());
    	for(int i = 1; i< windowsId.size(); i++){
    	  log.info(windowsId.get(0));	
    	  driver.switchTo().window(windowsId.get(i));
    	  driver.close();
    	 }
       switchToParentWindow();
     }
 
    public void switchToFrame(String nameOrID){
    	 driver.switchTo().frame(nameOrID);
     }
    
    public ArrayList<String> tabsCount(){
      return new ArrayList<String>(driver.getWindowHandles());
    }
    

}
