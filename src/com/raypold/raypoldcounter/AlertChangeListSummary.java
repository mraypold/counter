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
						// The 'which' argument contains the index position
						// of the selected item
						Preferences preferences = new Preferences(
								MainActivity.context);
						switch (which) {
						case 0:
							preferences.setDisplayCountsType("hour");
						case 1:
							preferences.setDisplayCountsType("day");
						case 2:
							preferences.setDisplayCountsType("week");
						case 3:
							preferences.setDisplayCountsType("month");
						}
						// Now update the list view in counter summary tab
						
					}
				});
		return builder.create();
	}

}
