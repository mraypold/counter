/*
 * Code on this page is mostly from http://developer.android.com/guide/topics/ui/dialogs.html
 *  
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

public class RenameCounterAlert extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();

		builder.setView(inflater.inflate(R.layout.rename_counter, null))
				.setTitle(R.string.RenameCounterMessage)
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
							
					@Override
							public void onClick(DialogInterface dialog, int id) {

								EditText textField = (EditText) getDialog().findViewById(R.id.renameCounterTextField);
								
								String newCounterName = textField.getText().toString();

								Counter openCounter = ActionBarHandler.getOpenCounter();
								openCounter.renameCounter(newCounterName);

								Preferences userPreferences = new Preferences(MainActivity.context);
								userPreferences.setLastOpenCounter(newCounterName);

								/*
								 * Important note:
								 * 
								 * Must call the fragments to refresh display from within this code, since Android will continue running
								 * other threads while it waits for the user to confirm their action.
								 */

								CounterFragment.refreshDisplay();
								SavedCounterFragment.refreshAdapter();
								CounterSummaryFragment.refreshSummary();
								
								Toast.makeText(MainActivity.context, R.string.renamed, Toast.LENGTH_SHORT).show();

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
