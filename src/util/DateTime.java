/*
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DateTime {
	
	private DateTime() {
		//set it to private to make this an utility class
	}
	
	*/
/**
	 * returns the current date of the system
	 * 
	 * @return GregorianCalendar object
	 *//*

	public static Calendar now() {
		return new GregorianCalendar();
	}
	
	*/
/**
	 * returns the number of the week in the year given a certain date
	 * 
	 * @param date Calendar instance
	 * @return week number
	 *//*

	public static int weekNumber(final Calendar date) {
		return date.get(Calendar.WEEK_OF_YEAR);
	}
	
	*/
/**
	 * returns the current month of the current date of the system
	 * 
	 * @return current month
	 *//*

	public static int currentMonth() {
		return now().get(Calendar.MONTH);
	}
	
	*/
/**
	 * returns the current year of the current date of the system
	 * 
	 * @return current year
	 *//*

	public static int currentYear() {
		return now().get(Calendar.YEAR);
	}
	
	*/
/**
	 * checks if the two Calendar instances represent dates of the same month
	 * 
	 * @param a
	 * @param b
	 * @return boolean
	 *//*

	public static boolean isSameMonth(final Calendar a, final Calendar b) {
		return a.get(Calendar.MONTH) == b.get(Calendar.MONTH);
	}
	
	*/
/**
	 * checks if the two Calendar instances represent dates of the same year
	 * @param a
	 * @param b
	 * @return boolean
	 *//*

	public static boolean isSameYear(final Calendar a, final Calendar b) {
		return a.get(Calendar.YEAR) == b.get(Calendar.YEAR);
	}
	
	*/
/**
	 * converts a Date instance to a Calendar instance
	 * 
	 * @param date
	 * @return Calendar instance
	 *//*

	public static Calendar dateToCalendar(final Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	*/
/**
	 * parses a string that contains a date in a certain format
	 * 
	 * @param aDateString
	 * @param format
	 * @return a Date object or null if there was an error parsing the string
	 *//*

	public static Calendar parseDate(final String aDateString, final String format) {
		try {
			final SimpleDateFormat df = new SimpleDateFormat(format);
			final Date date = df.parse(aDateString);
			return dateToCalendar(date);
		} catch(final ParseException ex) {
			Logger.getLogger(DateTime.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	*/
/**
	 * format a given date to the specific date format YYYY/MM/dd
	 * 
	 * @param aDate Calendar instance
	 * @return String containing the date formatted
	 *//*

	public static String format(final Calendar aDate) {
		return format(aDate, "YYYY/MM/dd");
	}
	
	*/
/**
	 * format a given date to a specific date format
	 * 
	 * @param aDate Calendar instance
	 * @param dateFormat
	 * @return String containing the date formatted
	 *//*

	public static String format(final Calendar aDate, String dateFormat) {
		final SimpleDateFormat formater = new SimpleDateFormat(dateFormat);
		return formater.format(aDate.getTime());
	}
	
	*/
/**
	 * compare if two dates are the same
	 * 
	 * @param dateA
	 * @param dateB
	 * @return boolean
	 *//*

	public static boolean compareDates(Calendar dateA, Calendar dateB) {
		return (dateA.get(Calendar.DAY_OF_MONTH) == dateB.get(Calendar.DAY_OF_MONTH)
				&& dateA.get(Calendar.MONTH) == dateB.get(Calendar.MONTH)
				&& dateA.get(Calendar.YEAR) == dateB.get(Calendar.YEAR));
	}
}
*/
