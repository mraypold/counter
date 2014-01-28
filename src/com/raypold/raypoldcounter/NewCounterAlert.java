/*
 * Author Michael Raypold
 * 
 * A copy of the license is available in LICENSE or at http://www.gnu.org/licenses/gpl-2.0.html
 * 
 * Creates an alert dialog for a new counter asking for the name
 * - Some layout is handled with builder the rest in res/layout/create_counter.xml
 */
package com.raypold.raypoldcounter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

public class NewCounterAlert extends DialogFragment {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();

		builder.setView(inflater.inflate(R.layout.create_counter, null))
				.setTitle(R.string.createCounter)
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
							
					@Override
							public void onClick(DialogInterface dialog, int id) {

								EditText textField = (EditText) getDialog().findViewById(R.id.createCounterTextField);
								
								String newCounterName = textField.getText().toString();

								Counter newCounter = new Counter(newCounterName);
								newCounter.saveCount();

								Preferences userPreferences = new Preferences(MainActivity.context);
								userPreferences.setLastOpenCounter(newCounterName);
								
								/* Important note:
								 * 
								 * Must call the fragments to refresh display from within this code, since Android will continue running
								 * other threads while it waits for the user to confirm their action.
								 */ 

								CounterFragment.refreshDisplay();
								SavedCounterFragment.refreshAdapter();
								
								Toast.makeText(MainActivity.context, R.string.counterCreated, Toast.LENGTH_SHORT).show();

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
