package com.raypold.raypoldcounter;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Counter {

	private int currentCount;
	private String counterName;
	
	private SharedPreferences counterFile;
	
	// The sharedPreferences file is the file the holds all counters and their count
	public Counter(String name, SharedPreferences file) {
		setCounterName(name);
		setCounterFile(file);
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
		saveCount();
	}
	
	public void decrementCount() {
		this.currentCount--;
		saveCount();
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
