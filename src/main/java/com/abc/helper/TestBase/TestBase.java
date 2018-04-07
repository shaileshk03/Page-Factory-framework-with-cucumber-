package com.abc.helper.TestBase;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abc.configBrowser.BrowserType;
import com.abc.configBrowser.ChromeBrowser;
import com.abc.configBrowser.FirefoxBrowser;
import com.abc.configBrowser.HtmlUnitBrowser;
import com.abc.configBrowser.IExploreBrowser;
import com.abc.configReader.ObjectRepo;
import com.abc.configReader.PropertyFileReader;
import com.abc.helper.Logger.LoggerHelper;
import com.abc.utility.DateTimeHelper;
import com.abc.utility.ExcelReader;
import com.abc.utility.ResourceHelper;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class TestBase {

	ExcelReader excel;
	private final Logger log = LoggerHelper.getLogger(TestBase.class);
	public static WebDriver driver;
	

	public void waitForElement(WebElement element, int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotFoundException.class);
		wait.pollingEvery(250, TimeUnit.MILLISECONDS);
		wait.until(elementLocated(element));
	}
		
	private Function<WebDriver, Boolean> elementLocated(final WebElement element) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				log.debug("Waiting for Element : " + element);
				return element.isDisplayed();
			}
		};
	}
	
    public WebElement getElement(By locator) {
		log.info(locator);
		if (isElementPresentQuick(locator))
			return driver.findElement(locator);
		try {
			throw new NoSuchElementException("Element Not Found : " + locator);
		} catch (RuntimeException re) {
			log.error(re);
			throw re;
		}
	}

	
	public boolean isElementPresentQuick(By locator){
		boolean flag = driver.findElements(locator).size() >= 1;
		log.info(flag);
		return flag;
	}
	
	
	public String [][] getData(String excelName, String sheetName){
		String path = System.getProperty("user.dir") + "/src/main/resources/exceldata/" + excelName;
		excel = new ExcelReader(path);
		String[][] data = excel.getDataFromSheet(excelName, sheetName);
		return data;
	}
		
	public String takeScreenShot(String name) throws IOException {
		if (driver instanceof HtmlUnitDriver) {
			log.fatal("HtmlUnitDriver Cannot take the ScreenShot");
			return "";
		}
		File destDir = new File(ResourceHelper.getResourcePath("screenshots/") + DateTimeHelper.getCurrentDate());
		if (!destDir.exists())
			destDir.mkdir();

		File destPath = new File(destDir.getAbsolutePath() + System.getProperty("file.separator") + name + ".jpg");
		try {
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), destPath);
		} catch (IOException e) {
			log.error(e);
			throw e;
		}
		log.info(destPath.getAbsolutePath());
		return destPath.getAbsolutePath();
	}

	public WebElement getElementByNull(By locator){
		log.info(locator);
		try {
			 return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			//Ignore 
		}
	  return null;
	}
	
	public WebDriver getBrowserObject(BrowserType btype) throws Exception {
		try {
			switch (btype) {

			case Chrome:
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				return chrome.getChromeDriver(chrome.getChromeCapabilities());
				
			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				return firefox.getFirefoxDriver(firefox.getFirefoxCapabilities());

			case HtmlUnitDriver:
				HtmlUnitBrowser htmlUnit = HtmlUnitBrowser.class.newInstance();
				return htmlUnit.getHtmlUnitDriver(htmlUnit.getHtmlUnitDriverCapabilities());

			case Iexplorer:
				IExploreBrowser iExplore = IExploreBrowser.class.newInstance();
				return iExplore.getIExplorerDriver(iExplore.getIExplorerCapabilities());
								
			default:
				throw new Exception("Driver not found"
						+ new PropertyFileReader().getBrowser());
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void setUpDriver(BrowserType btype) throws Exception{
		driver = getBrowserObject(btype);
		driver.manage().timeouts().pageLoadTimeout(ObjectRepo.reader.getPageLoadTimeOut(), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(ObjectRepo.reader.getImplicitWait(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
		
	@Before()
	public void before() throws Exception {
		ObjectRepo.reader = new PropertyFileReader();
		setUpDriver(ObjectRepo.reader.getBrowser());
		log.info(ObjectRepo.reader.getBrowser());
	}

	@After()
	public void after(Scenario scenario) throws Exception {
		//driver.quit();
		log.info("");
	}
}
