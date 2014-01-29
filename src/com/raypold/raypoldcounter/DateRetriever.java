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

	/* Returns a list of strings of all truncated dates in the open counter */
	public ArrayList<String> getDatesByHour() {
		ArrayList<String> datesByHour = new ArrayList<String>();
		SimpleDateFormat curDate = new SimpleDateFormat("MMM dd hh':00'a");

		for(Date date: this.dates) {
			String dateToString = curDate.format(date);
			datesByHour.add(dateToString);
		}

		return datesByHour;
	}
	
	/* Returns a list of strings of all truncated dates in the open counter	*/
	public ArrayList<String> getDatesByDay() {
		ArrayList<String> datesByHour = new ArrayList<String>();
		SimpleDateFormat curDate = new SimpleDateFormat("MMM dd");

		for(Date date: this.dates) {
			String dateToString = curDate.format(date);
			datesByHour.add(dateToString);
		}

		return datesByHour;
	}
	
	/* Returns a list of strings of all truncated dates in the open counter
	 *
	 * This stackoverflow thread was helpful
	 * http://stackoverflow.com/questions/7645178/getting-the-start-and-the-end-date-of-a-week-using-java-calendar-class
	 */
	public ArrayList<String> getDatesByWeek() {
		ArrayList<String> datesByHour = new ArrayList<String>();
		SimpleDateFormat curDate = new SimpleDateFormat("MMM WW");
		
		for(Date date: this.dates) {
			/* Get the first day of the week */
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(this.dates.get(0));

			String dateToString = curDate.format(date);
			datesByHour.add(dateToString);
		}

		return datesByHour;
	}
	
	/* Returns a list of strings of all truncated dates in the open counter */
	public ArrayList<String> getDatesByMonth() {
		ArrayList<String> datesByHour = new ArrayList<String>();
		SimpleDateFormat curDate = new SimpleDateFormat("MMM");

		for(Date date: this.dates) {
			String dateToString = curDate.format(date);
			datesByHour.add(dateToString);
		}

		return datesByHour;
	}
}
