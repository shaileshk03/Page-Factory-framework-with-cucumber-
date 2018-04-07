package com.abc.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = {"classpath:featurefiles/FacebookLogin.feature"},
glue={"classpath:com.abc.StepDefinition","classpath:com.abc.helper"},
plugin = {"html:target/cucumber-html-report"}
)
public class runnerFacebookLoginTest extends AbstractTestNGCucumberTests {

}
