package com.raypold.raypoldcounter;

import java.sql.Date;
import java.util.ArrayList;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Counter implements java.io.Serializable {

	private String counterName;
	private int currentCount;
	private ArrayList<Date> dates;
	
	private SharedPreferences counterFile;
	
	// The sharedPreferences file is the file the holds all counters and their count
	public Counter(String name, SharedPreferences file) {
		setCounterName(name);
		setCounterFile(file);
		dates = new ArrayList<Date>();
	}
	
	public void setCounterFile(SharedPreferences file) {
		this.counterFile = file;
	}
	
	public int getCurrentCount() {
		this.currentCount = counterFile.getInt(counterName, 0);
		return currentCount;
	}

	public void setCurrentCount(int value) {
		this.currentCount = value;
	}
	
	public void resetCurrentCount() {
		setCurrentCount(0);
		saveCount();
	}
	
	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	
	public void incrementCount() {
		this.currentCount++;
		dates.add(new Date(System.currentTimeMillis()));
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
		Editor editor = counterFile.edit();
		editor.putInt(counterName, currentCount);
		editor.commit();
	}
	
	public void deleteCounter() {
		Editor editor = counterFile.edit();
		editor.remove(counterName);
		editor.commit();
	}
	
}
