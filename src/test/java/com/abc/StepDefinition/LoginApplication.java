package com.abc.StepDefinition;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.abc.configReader.ObjectRepo;
import com.abc.helper.Logger.LoggerHelper;
import com.abc.helper.PageObject.LoginPage;
import com.abc.helper.TestBase.TestBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginApplication {
	
	private final Logger log = LoggerHelper.getLogger(LoginApplication.class);
	LoginPage loginpage;
	
	@Given("^I Navigate to login page$")
	public void i_Navigate_to_login_page() throws Throwable {
	  TestBase.driver.get(ObjectRepo.reader.getWebsite());
	}

	@When("^I click on singin Link$")
	public void i_click_on_singin_Link() throws Throwable {
		loginpage = new LoginPage(TestBase.driver);
		loginpage.clickOnSignInLink();
	}

	@When("^I enter username as \"([^\"]*)\"$")
	public void i_enter_username_as(String arg1) throws Throwable {
		loginpage.enterEmailAddress(arg1);
	}

	@When("^I enter password as \"([^\"]*)\"$")
	public void i_enter_password_as(String arg1) throws Throwable {
		loginpage.enterPassword(arg1);
	}

	@When("^I click on singin button$")
	public void i_click_on_singin_button() throws Throwable {
		loginpage.clickOnSubmitButton();
	}

	@Then("^I should see the login successful$")
	public void i_should_see_the_login_successful() throws Throwable {
		if(loginpage.verifySuccessLoginMsg()){
			log.info("login test pass");
		} else{
			Assert.assertTrue(false, this.getClass().getSimpleName()+"is fail");
		}
	}

	@Then("^I click on logout button$")
	public void i_click_on_logout_button() throws Throwable {
	    log.info("Logging out");
		loginpage.clickOnLogOutbtn();
	}
	
}
