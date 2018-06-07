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
	
	/**
	 * returns the current date of the system
	 * 
	 * @return GregorianCalendar object
	 */
	public static Calendar now() {
		return new GregorianCalendar();
	}
	
	/**
	 * returns the number of the week in the year given a certain date
	 * 
	 * @param date Calendar instance
	 * @return week number
	 */
	public static int weekNumber(final Calendar date) {
		return date.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * returns the current month of the current date of the system
	 * 
	 * @return current month
	 */
	public static int currentMonth() {
		return now().get(Calendar.MONTH);
	}
	
	/**
	 * returns the current year of the current date of the system
	 * 
	 * @return current year
	 */
	public static int currentYear() {
		return now().get(Calendar.YEAR);
	}
	
	/**
	 * checks if the two Calendar instances represent dates of the same month
	 * 
	 * @param a
	 * @param b
	 * @return boolean
	 */
	public static boolean isSameMonth(final Calendar a, final Calendar b) {
		return a.get(Calendar.MONTH) == b.get(Calendar.MONTH);
	}
	
	/**
	 * checks if the two Calendar instances represent dates of the same year
	 * @param a
	 * @param b
	 * @return boolean
	 */
	public static boolean isSameYear(final Calendar a, final Calendar b) {
		return a.get(Calendar.YEAR) == b.get(Calendar.YEAR);
	}
	
	/**
	 * converts a Date instance to a Calendar instance
	 * 
	 * @param date
	 * @return Calendar instance
	 */
	public static Calendar dateToCalendar(final Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	
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
	
}
