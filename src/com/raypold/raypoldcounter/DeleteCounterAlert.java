/*
 * Author: Michael Raypold
 *  
 * A copy of the license is available in LICENSE
 *  
 * Provides functionality to the action bar delete icon.
 *  - All string resources are available in res/values/strings.xml or are dynamically created.
 */
package com.raypold.raypoldcounter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class DeleteCounterAlert extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setMessage(R.string.deleteQuestion)
			   .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
						
				@Override
				public void onClick(DialogInterface dialog, int id) {
					/* Find the counter with the next highest number of counts and switch to it */
					Counter openCounter = ActionBarHandler.getOpenCounter();
					openCounter.deleteCounter();
					
					Serialize serialize = new Serialize();
					CountersMap savedCounters = serialize.deserializeCountersMap();
					
					String newCounterName = null;
					
					/* No counters exist. Will need to create a default counter for the user */
					if(savedCounters.isEmpty()) {
						newCounterName = getString(R.string.defaultCounterName);
						
						Counter newCounter = new Counter(newCounterName);
						savedCounters.insertCounterObject(newCounter);
						
						serialize.serializeCountersMap(savedCounters);
						serialize.serializeCounter(newCounter);

						Toast.makeText(MainActivity.context, R.string.deleteAndDefault, Toast.LENGTH_LONG).show();
					}
					else {
						/* Switch to the counter with the highest current count */
						newCounterName = savedCounters.getLargestCountName();
						Toast.makeText(MainActivity.context, R.string.deleted, Toast.LENGTH_SHORT).show();
					}
					
					Preferences userPreferences = new Preferences(MainActivity.context);
					userPreferences.setLastOpenCounter(newCounterName);

					/* Important note:
					 * 
					 * Must call the fragments to refresh display from within this code, since Android will continue running
					 * other threads while it waits for the user to confirm their action.
					 */ 

					CounterFragment.refreshDisplay();

				}
			})

	.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					/* Do nothing */
				}
			});

		return builder.create();
	}
}
