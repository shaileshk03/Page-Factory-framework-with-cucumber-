package com.abc.configBrowser;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.abc.utility.ResourceHelper;

public class ChromeBrowser {

	public Capabilities getChromeCapabilities() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("start-maximized");
		option.addArguments("--disable-notifications");
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		chrome.setCapability(ChromeOptions.CAPABILITY, option);
		return chrome;
	}

	public WebDriver getChromeDriver(Capabilities cap) {
		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty(
					"webdriver.chrome.driver",
					ResourceHelper
							.getResourcePath("/src/main/resources/drivers/chromedriver"));
			return new ChromeDriver(cap);
		}
		if (System.getProperty("os.name").contains("Window")) {
			System.setProperty(
					"webdriver.chrome.driver",
					ResourceHelper
							.getResourcePath("/src/main/resources/drivers/chromedriver.exe"));
			return new ChromeDriver(cap);
		}
		return null;
	}

}
