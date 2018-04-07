package com.abc.helper.Verification;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.abc.helper.Logger.LoggerHelper;

public class verificationHelper {

	private static final Logger log = LoggerHelper
			.getLogger(verificationHelper.class);

	public static synchronized boolean verifyElementPresent(WebElement element) {
		boolean isDisplayed = false;
		try {
			isDisplayed = element.isDisplayed();
			log.info(element.getText() + " isDisplayed");
		} catch (Exception e) {
			log.error(e);
		}
		return isDisplayed;
	}

	public static synchronized boolean verifyElementNotPresent(
			WebElement element) {
		boolean isDisplayed = false;
		try {
			element.isDisplayed();
			log.info(element.getText() + " is Displayed");
		} catch (Exception e) {
			log.error("Element Not Found" + e);
			isDisplayed = true;
		}
		return isDisplayed;
	}

	public static synchronized boolean verifyTextEquals(WebElement element,
			String expectedText) {
		boolean flag = false;
		try {
			String actualText = element.getText();
			if (actualText.equals(expectedText)) {
				log.info("actualText is :" + actualText + " expected text is: "
						+ expectedText);
				return flag = true;
			} else {
				log.error("actualText is :" + actualText
						+ " expected text is: " + expectedText);
				return flag;
			}

		} catch (Exception e) {
			log.error("actualText is :" + element.getText()
					+ " expected text is: " + expectedText);
			log.info("text not matching" + e);
			return flag;
		}

	}

}
