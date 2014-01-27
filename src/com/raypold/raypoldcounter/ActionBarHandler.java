/*
 * Author: Michael Raypold
 * 
 * A copy of the license is available in LICENSE
 * 
 * ActionBarHandler is responsible for giving functionality to the action bar.
 *  - The corresponding UI is handled in /menu/main_activity_actions.xml 
 * 
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
		setOpenCounterName();
	}
	
	/* Respond to the icon that the user clicked */
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
		}
		return false;
	}	
	
	private void resetCounter() {	
		Counter openCounter = getOpenCounter();
		openCounter.resetCurrentCount();
		
		CounterFragment.refreshDisplay();
		
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
        DeleteCounterAlert deleteAlert = new DeleteCounterAlert();
        deleteAlert.show(MainActivity.getFragment(), "deleteCounterAlert");        
	}
	
	/* Intention of this method is to not have an openCounter attribute in the actionBar wasting memory all the time */
	/* ActionBar buttons are not clicked very often */
	public static Counter getOpenCounter() {
		setOpenCounterName(); /* Ensure we have the most recent open counter */
		Serialize serialize = new Serialize();
		Counter openCounter = serialize.deserializeCounter(openCounterName);

		return openCounter;
	}
	
	private static void setOpenCounterName() {
		Preferences userPreferences = new Preferences(context);
		openCounterName = userPreferences.getLastOpenCounter();
	}
}
