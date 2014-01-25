package com.raypold.raypoldcounter;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import android.util.Log;

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
		/* Delete old key in hash map and existing counter file on disk */
		Log.e("degbug", "deleteCounter() before");
		deleteCounter();
		Log.e("degbug", "deleteCounter() after");
		setCounterName(newCounterName);
		Log.e("degbug", "after setCounterName()");

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
		
		// TODO when the counter is deleted will have to switch to a new counter or a default one
	}
	
}
