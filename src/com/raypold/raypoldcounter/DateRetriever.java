package com.raypold.raypoldcounter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.util.Log;

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
	
}
