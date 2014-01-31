/*
 * Author: Michael Raypold
 * 
 * Responsible for getting and setting preferences that aid the fragments
 * 
 */
package com.raypold.raypoldcounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {

	/* Attributes for accessing key/value in sharedPreferences */
	private final String LASTOPENCOUNTERKEY = "lastOpenCounter";
	private final String FIRSTRUN = "isFirstRun";
	private final String DISPLAYCOUNTSTYPE = "displayCountsType";
	private final String PREFERENCESFILE = "userPreferences";

	private SharedPreferences userPreferences;
	
	private String lastOpenCounter = null;
	private Boolean firstRun = false;

	public Preferences(Context context) {
		super();
		setUserPreferences(context.getSharedPreferences(PREFERENCESFILE, 0));
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
	
	/* Prefererences for how the user wants to view the list of counter dates */
	public void setDisplayCountsType(int type) {
		/* hour = 0
		 * day = 1
		 * week = 2
		 * month = 3
		 */
		Editor editor = userPreferences.edit();
		editor.putInt(DISPLAYCOUNTSTYPE, type);
		editor.commit();
	}
	
	/* Returns hour, day, week, month date preferences */
	public int getDisplayCountsType() {
		return userPreferences.getInt(DISPLAYCOUNTSTYPE, 0);
	}
	
	/* Responds true if first time application has run */
	public Boolean isFirstRun() {
		return userPreferences.getBoolean(FIRSTRUN, true);
	}
	
	public void setFirstRun() {
		Editor editor = userPreferences.edit();
		editor.putBoolean(FIRSTRUN, firstRun);
		editor.commit();
	}
}
