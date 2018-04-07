package com.abc.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeHelper {

	public static String getCurrentDataAndTime() {
		DateFormat dateformate = new SimpleDateFormat("_yyyy-mm-dd_HH-mm-ss");
		Calendar cal = Calendar.getInstance();
		String time = "" + dateformate.format(cal.getTime());
		return time;
	}

   public static String getCurrentDate(){
	  return getCurrentDataAndTime().substring(0, 11);
   }

}
