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
	private static Context context;
	private static String openCounterName;
	
	public ActionBarHandler(MenuItem item, Context applicationContext) {
		super();
		this.item = item;
		ActionBarHandler.context = applicationContext;
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
	
	private void resetCounter() {	
		Counter openCounter = getOpenCounter();
		openCounter.resetCurrentCount();
		
		CounterFragment.refreshDisplay();
		// TODO will also need to refreshDispaly in savedCounters and summary
		
		Toast.makeText(context, String.format("%s has been reset", openCounterName), 
				Toast.LENGTH_SHORT).show();
		
	}
	
	private void renameCounter() {
        RenameCounterAlert renameAlert = new RenameCounterAlert();
        renameAlert.show(MainActivity.getFragment(), "RenameCounterAlert");        
	}
	
	private void newCounter() {
        NewCounterAlert newAlert = new NewCounterAlert();
        newAlert.show(MainActivity.getFragment(), "newCounterAlert");        
	}
	
	private void deleteCounter() {

		Toast.makeText(context, "Counter has been deleted", Toast.LENGTH_SHORT).show();

	}
	
	private void help(){

	}
	
	/* Intention of this method is to not have an openCounter attribute in the actionBar wasting memory all the time */
	/* ActionBar buttons are not clicked very often */
	public static Counter getOpenCounter() {
		setOpenCounterName();
		Serialize serialize = new Serialize();
		Counter openCounter = serialize.deserializeCounter(openCounterName);

		return openCounter;
	}
	
	public static void setOpenCounterName() {
		Preferences userPreferences = new Preferences(context);
		openCounterName = userPreferences.getLastOpenCounter();
	}
}
