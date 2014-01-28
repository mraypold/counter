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
	
	/* This would be better modeled with booleans for hour day week month */
	public void setDisplayCountsType(String type) {
		Editor editor = userPreferences.edit();
		editor.putString(DISPLAYCOUNTSTYPE, type);
		editor.commit();
	}
	
	public String getDisplayCountsType() {
		return userPreferences.getString(DISPLAYCOUNTSTYPE, "day");
	}
	public Boolean isFirstRun() {
		return userPreferences.getBoolean(FIRSTRUN, true);
	}
	
	public void setFirstRun() {
		Editor editor = userPreferences.edit();
		editor.putBoolean(FIRSTRUN, firstRun);
		editor.commit();
	}
}
