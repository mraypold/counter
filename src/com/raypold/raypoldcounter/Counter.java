package com.raypold.raypoldcounter;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Counter {

	private int currentCount;
	private String counterName;
	
	private SharedPreferences counterFile;
	
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
	
}
