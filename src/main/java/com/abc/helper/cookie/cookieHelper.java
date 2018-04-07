package com.abc.helper.cookie;

import java.util.Date;
import java.util.Set;
import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import com.abc.helper.Logger.LoggerHelper;

public class cookieHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(cookieHelper.class);

	// Get All the cookie
	public Set<Cookie> getAllCookie() {
		return driver.manage().getCookies();
	}

	// Get Cookie name
	public Cookie getCookieName(String name) {
		return driver.manage().getCookieNamed(name);
	}

	public String getValueOfCookieName(String name) {
		return driver.manage().getCookieNamed(name).getValue();
	}

	public void addCookie(String name, String value, String Domain,
			String path, Date Expiry) {
		driver.manage()
				.addCookie(new Cookie(name, value, Domain, path, Expiry));
	}

	public void addCookieToBrowser(Set<Cookie> cookies, String domain,
			String name, String value, String path, Date Expiry) {
		for (Cookie c : cookies) {
			if (c != null) {
				if (c.getDomain().contains(domain)) {
					driver.manage().addCookie(
							new Cookie(name, domain, value, path, Expiry));
				}
			}
		}
	}


   public void deleteCookie(String name){
	   driver.manage().deleteCookieNamed(name);
   }

   public void deleteAllCookie(){
	   driver.manage().deleteAllCookies();
   }
   
}
