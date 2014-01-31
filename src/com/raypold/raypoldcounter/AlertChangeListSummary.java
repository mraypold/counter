/*
 * Author: Michael Raypold
 * 
 * A copy of the license is available in LICENSE
 * 
 * Called when the user asks to change display preferences from hour to day to week, etc...
 * 
 */
package com.raypold.raypoldcounter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlertChangeListSummary extends DialogFragment {

	/* Followed the code available http://developer.android.com/guide/topics/ui/dialogs.html */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.changeView).setItems(R.array.dialogViewArray,
				
				new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						Preferences preferences = new Preferences(
								MainActivity.context);
						switch(which){
						case 0:
							preferences.setDisplayCountsType(0);
							break;
						case 1:
							preferences.setDisplayCountsType(1);
							break;
						case 2:
							preferences.setDisplayCountsType(2);
							break;
						case 3:
							preferences.setDisplayCountsType(3);
							break;
						default:
							preferences.setDisplayCountsType(0);
							break;
						}
						CounterSummaryFragment.refreshSummary();
					}
				});
		return builder.create();
	}

}
