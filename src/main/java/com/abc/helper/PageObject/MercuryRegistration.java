package com.abc.helper.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abc.helper.Logger.LoggerHelper;
import com.abc.helper.wait.WaitHelper;

public class MercuryRegistration {

	WebDriver driver;
	private Logger log = LoggerHelper.getLogger(MercuryRegistration.class);
	WaitHelper waithelper;

	@FindBy(css = "input[name*='firstName']")
	private WebElement firstName;

	@FindBy(css = "input[name*='lastName']")
	private WebElement LastName;

	@FindBy(css = "input[name*='phone']")
	private WebElement phone;

	@FindBy(css = "input[id*='userName']")
	private WebElement UserName;

	@FindBy(xpath = "//input[@id='email'][@name='email']")
	private WebElement Email;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;

	@FindBy(xpath = "//input[starts-with(@name,'confirmP')]")
	private WebElement confirmPassword;

	@FindBy(xpath = "//input[contains(@src,'submit.gif')]")
	private WebElement submit;

	
	public  static MercuryRegistration getMercuryRegistration(WebDriver driver) {
       return PageFactory.initElements(driver, MercuryRegistration.class);
	}

	
	public WebElement getFirstName() {
		return firstName;
	}

	public WebElement getLastName() {
		return LastName;
	}

	public WebElement getPhone() {
		return phone;
	}

	public WebElement getUserName() {
		return UserName;
	}

	public WebElement getEmail() {
		return Email;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getConfirmPassword() {
		return confirmPassword;
	}

	public WebElement getSubmitbutton() {
		return submit;
	}

	
	
	
	
}
