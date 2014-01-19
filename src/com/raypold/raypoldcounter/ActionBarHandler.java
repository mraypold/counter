/*
 * Author: Michael Raypold
 * 
 * ActionBarHandler is responsible for giving functionality to the action bar.
 */
package com.raypold.raypoldcounter;

import android.content.Context;
import android.view.MenuItem;
import android.widget.Toast;

public class ActionBarHandler extends MainActivity {

	private MenuItem item;
	private Context context;
	private Preferences userPreferences;
	private Counter counter;
	private String openCounterName;
	
	public ActionBarHandler(MenuItem item, Context applicationContext) {
		super();
		this.item = item;
		this.context = applicationContext;
		this.userPreferences = new Preferences(MainActivity.preferences);
		
		setOpenCounterName(); // Must be called before setting counter.
		
		this.counter = new Counter(openCounterName, MainActivity.savedCounters);
	}
	
	public boolean getAction() {
		switch(item.getItemId()) {
		case R.id.reset_counter:
			resetCounter();
			return true;
		case R.id.rename_counter:
			renameCounter();
			return true;
		case R.id.new_counter:
			newCounter();
			return true;
		case R.id.counter_delete:
			deleteCounter();
			return true;
		case R.id.help:
			help();
			return true;
		}
		return false;
	}
	
	private void setOpenCounterName() {
		this.openCounterName = userPreferences.getLastOpenCounter();
	}
	
	
	private void resetCounter() {
		// TODO ask if sure want to reset
		counter.resetCurrentCount();
		Toast.makeText(context, String.format("%s has been reset", openCounterName), 
				Toast.LENGTH_SHORT).show();
	}
	
	// Make it show the old and new name?
	private void renameCounter() {
		Toast.makeText(context, String.format("%s has been renamed", openCounterName), 
				Toast.LENGTH_SHORT).show();
	}
	
	private void newCounter() {
		Toast.makeText(context, "New counter has been created", Toast.LENGTH_SHORT).show();

	}
	
	private void deleteCounter() {
		Toast.makeText(context, "Counter has been deleted", Toast.LENGTH_SHORT).show();

	}
	
	private void help(){

	}
}
