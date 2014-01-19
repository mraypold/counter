/*
 * Author: Michael Raypold
 * 
 * Responsible for getting and setting preferences that aid the fragments
 * 
 * If this is the first time the application has run, or there exists no
 * saved counters, then the lastOpenCounter is default as "Counter".
 * 
 * This class requires as arguments an open sharedPreferences file, since calling
 * sharedPreferences differs between fragments and views.
 * 
 */
package com.raypold.raypoldcounter;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {

	/* Attributes for accessing key/value in sharedPreferences */
	private final String LASTOPENCOUNTERKEY = "lastOpenCounter";
	
	private String lastOpenCounter = "Counter";
	private SharedPreferences userPreferences;
		
	public Preferences(SharedPreferences preferences) {
		super();
		setUserPreferences(preferences);
		setLastOpenCounter(getLastOpenCounter());
	}

	public String getLastOpenCounter() {
		return userPreferences.getString(LASTOPENCOUNTERKEY, lastOpenCounter);
	}

	public void setLastOpenCounter(String lastOpenCounter) {
		this.lastOpenCounter = lastOpenCounter;
		
		/* Save lastOpenCounter to disk */
		Editor editor = userPreferences.edit();
		editor.putString(LASTOPENCOUNTERKEY, lastOpenCounter);
		editor.commit();
	}

	public SharedPreferences getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(SharedPreferences preferences) {
		this.userPreferences = preferences;
	}
}
