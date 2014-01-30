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
import android.widget.Toast;

public class ResetCounterAlert extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setMessage(R.string.resetQuestion)
				.setPositiveButton(R.string.reset, new DialogInterface.OnClickListener() {
							
					@Override
							public void onClick(DialogInterface dialog, int id) {
								Counter openCounter = ActionBarHandler.getOpenCounter();
								openCounter.resetCurrentCount();

								/*
								 * Important note:
								 * 
								 * Must call the fragments to refresh display from within this code, since Android will continue running
								 * other threads while it waits for the user to confirm their action.
								 */

								CounterFragment.refreshDisplay();
								SavedCounterFragment.refreshAdapter();
								CounterSummaryFragment.refreshSummary();
								
								Toast.makeText(MainActivity.context, String.format("%s has been reset", openCounter.getCounterName()), 
										Toast.LENGTH_SHORT).show();
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
