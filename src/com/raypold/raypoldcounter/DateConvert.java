package com.raypold.raypoldcounter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DateConvert {

	private Counter counter;
	private ArrayList<Date> dates;

	public DateConvert(Counter counter) {
		super();
		this.counter = counter;
		this.dates = counter.getDates();
		
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}
	
	public ArrayList<SimpleDateFormat> getDatesByHour() {
		Integer count = 0;
		ArrayList<SimpleDateFormat> list = new ArrayList<SimpleDateFormat>();
		
		/* Truncate unnecessary date */
		for(Date date: this.dates) {
			SimpleDateFormat curDate = new SimpleDateFormat("yyyyMMddkk");
			curDate.format(date);
			list.add(curDate);
		}

		return list;
	}
	
	
}
