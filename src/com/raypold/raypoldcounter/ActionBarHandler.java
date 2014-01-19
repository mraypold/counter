/*
 * Author: Michael Raypold
 * 
 * ActionBarHandler is responsible for giving functionality to the actionbar.
 */
package com.raypold.raypoldcounter;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ActionBarHandler {

	MenuItem item;
	View view;
	Context context;
	
	public ActionBarHandler(MenuItem item, Context applicationContext) {
		super();
		this.item = item;
		this.context = applicationContext;
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
		Toast.makeText(context, "Counter has been reset", Toast.LENGTH_SHORT).show();

	}
	
	private void renameCounter() {
		Toast.makeText(context, "Counter has been renamed", Toast.LENGTH_SHORT).show();

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
