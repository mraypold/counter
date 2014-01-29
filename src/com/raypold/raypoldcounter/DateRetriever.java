package com.raypold.raypoldcounter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DateRetriever {

	private ArrayList<Date> dates;
	
	public DateRetriever(Counter counter) {
		super();
		this.dates = counter.getDates();
	}

	/* Inserts the counter dates in the given simpledateformat format into an ArrayList */
	private ArrayList<String> insertDatesToArray(SimpleDateFormat curDate) {
		ArrayList<String> datesByPeriod = new ArrayList<String>();
		
		for(Date date: this.dates) {
			String dateToString = curDate.format(date);
			datesByPeriod.add(dateToString);
		}
		
		return datesByPeriod;
	}
	
	/* Returns a list of strings of all truncated dates in the open counter */
	public ArrayList<String> getDatesByHour() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd hh':00'a");

		ArrayList<String> datesByHour = insertDatesToArray(dateFormat);

		return datesByHour;
	}
	
	/* Returns a list of strings of all truncated dates in the open counter	*/
	public ArrayList<String> getDatesByDay() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");

		ArrayList<String> datesByDay = insertDatesToArray(dateFormat);

		return datesByDay;
	}
	
	/* Returns a list of strings of all truncated dates in the open counter
	 *
	 * This stackoverflow thread was helpful
	 * http://stackoverflow.com/questions/7645178/getting-the-start-and-the-end-date-of-a-week-using-java-calendar-class
	 */
	public ArrayList<String> getDatesByWeek() {
		ArrayList<String> datesByHour = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM");
		SimpleDateFormat weekDay = new SimpleDateFormat("dd");
		
		/* Add the start day of the week and truncate the week number */
		for(Date date: this.dates) {
			/* Set the time to the current date in the iteration */
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			/* Get first day of the week and retrieve a string representation */
			Integer dayNumber = calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek();
			calendar.add(Calendar.DAY_OF_MONTH, - dayNumber);
			String dayOfWeek = weekDay.format(calendar.getTime());
			
			/* Concat the month with the weekday number */
			String dateToString = dateFormat.format(date);
			dateToString = dateToString.concat(" " + dayOfWeek);
			datesByHour.add(dateToString);
		}

		return datesByHour;
	}
	
	/* Returns a list of strings of all truncated dates in the open counter */
	public ArrayList<String> getDatesByMonth() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM");

		ArrayList<String> datesByHour = insertDatesToArray(dateFormat);

		return datesByHour;
	}
}
