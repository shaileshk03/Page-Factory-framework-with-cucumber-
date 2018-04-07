package com.abc.StepDefinition;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abc.helper.Logger.LoggerHelper;
import com.abc.helper.PageObject.MercuryRegistration;
import com.abc.helper.PageObject.UserData;
import com.abc.helper.TestBase.TestBase;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MercuryStepDef {

	 private Logger log = LoggerHelper.getLogger(MercuryStepDef.class);
	 
	 
	 
	@Given("^I've a valid set of data and access to Registration Page$")
	public void i_ve_a_valid_set_of_data_and_access_to_Registration_Page() throws Throwable {
	     log.info("Navigating to Mercury Site");
	     TestBase.driver.get("http://newtours.demoaut.com/mercurywelcome.php");
	     
	}

	@When("^Registration page Displayed$")
	public void registration_page_Displayed() throws Throwable {
		log.info("Wait for element to Appear");
		WebDriverWait wait = new WebDriverWait(TestBase.driver,10);	 	    
	    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("REGISTER"))); 
	    
	  //*********using javascript executor to open the registration page in same window********
	    WebElement register = TestBase.driver.findElement(By.linkText("REGISTER"));	   
	    JavascriptExecutor js = (JavascriptExecutor) TestBase.driver;
		js.executeScript("arguments[0].setAttribute('target','_self');",register);
	    register.click();  
	}

	@Then("^I enter valid data on registration page and check if registration is successfull$")
	  public void provideUserData(List<UserData> details) throws Throwable{
	    log.info("Entering Registration details");
		System.out.println("Total users :"+details.size());
		for(UserData user :details)
		{
		
			System.out.println("Registering :"+user.getFirstName()+" "+user.getLastName());		
			WebElement firstName =  MercuryRegistration.getMercuryRegistration(TestBase.driver).getFirstName();
			firstName.sendKeys(user.getFirstName());
			WebElement lastName = MercuryRegistration.getMercuryRegistration(TestBase.driver).getLastName();
			lastName.sendKeys(user.getLastName());
            WebElement phone = MercuryRegistration.getMercuryRegistration(TestBase.driver).getPhone();			
            phone.sendKeys(user.getPhone());
            
            WebElement Email = MercuryRegistration.getMercuryRegistration(TestBase.driver).getEmail();
            Email.sendKeys(user.getEMail());
            
            WebElement username = MercuryRegistration.getMercuryRegistration(TestBase.driver).getUserName();
            username.sendKeys(user.getUserName());
            
            WebElement password = MercuryRegistration.getMercuryRegistration(TestBase.driver).getPassword();
            password.sendKeys(user.getPassword());
            
            WebElement ConfirmPassword = MercuryRegistration.getMercuryRegistration(TestBase.driver).getConfirmPassword();
            ConfirmPassword.sendKeys(user.getConfirmPassword());
            
            WebElement submitButton = MercuryRegistration.getMercuryRegistration(TestBase.driver).getSubmitbutton();
            submitButton.click();
            
//asserting post registration			    
		    String msg = TestBase.driver.findElement(By.xpath("//*[contains(text(),'Thank you for registering')]")).getText();
		    System.out.println(msg);
		    Assert.assertTrue("Text is not getting displayed properly",msg.contains("Thank you for registering."));    

//going to register link after post successful registration of other user
		    WebElement register = TestBase.driver.findElement(By.linkText("REGISTER"));
		    ((JavascriptExecutor) TestBase.driver).executeScript("arguments[0].setAttribute('target','_self');",register);
		    		   register.click();
			
		}
	}

	@Then("^Close the Browser$")
	public void close_the_Browser() throws Throwable {
	 TestBase.driver.quit();   
	}
}
