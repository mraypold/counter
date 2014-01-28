/*
 * Author: Michael Raypold
 * 
 * A copy of the license is available in LICENSE
 * 
 * Counter provides all the basic functionality of a tally counter.
 * 
 */
package com.raypold.raypoldcounter;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class Counter implements Serializable {
	
	private String counterName;
	private Integer currentCount;
	private ArrayList<Date> dates;
		
	public Counter(String name) {
		setCounterName(name);
		setCurrentCount(0);
		dates = new ArrayList<Date>();
	}
	
	public Integer getCurrentCount() {
		return this.currentCount;
	}

	public void setCurrentCount(Integer value) {
		this.currentCount = value;
	}
	
	public void resetCurrentCount() {
		this.dates = new ArrayList<Date>();
		setCurrentCount(0);
		saveCount();
	}
	
	public String getCounterName() {
		return counterName;
	}

	private void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	
	public void renameCounter(String newCounterName) {
		/* Delete old key in hash map and existing counter file on disk,
		 * then update the name and save the counter.
		 * */
		deleteCounter();
		setCounterName(newCounterName);

		saveCount();
	}
	
	public void incrementCount() {
		this.currentCount++;
		this.dates.add(new Date(System.currentTimeMillis()));
		saveCount();
	}
	
	public void decrementCount() {
		/* Only decrement until it reaches zero */
		if(getCurrentCount() >= 1){
			this.currentCount--;
			dates.remove(dates.size() - 1);
			saveCount();
		}
	}
	
	public void saveCount() {
		CountersMap savedCounters = new CountersMap();
		Serialize serialize = new Serialize();
		
		savedCounters = serialize.deserializeCountersMap();
		savedCounters.insertCounter(this.counterName, this.currentCount);
		
		serialize.serializeCountersMap(savedCounters);
		serialize.serializeCounter(this);
		
		Preferences userPreferences = new Preferences(MainActivity.context);
		userPreferences.setLastOpenCounter(this.counterName);
	}
	
	public void deleteCounter() {
		CountersMap savedCounters = new CountersMap();
		Serialize serialize = new Serialize();

		savedCounters = serialize.deserializeCountersMap();
		savedCounters.deleteCounter(this.counterName);
		
		serialize.serializeCountersMap(savedCounters);
		serialize.deleteCounterFile(this.counterName);	
	}
	
	public ArrayList<Date> getDates() {
		return this.dates;
	}
	
}
