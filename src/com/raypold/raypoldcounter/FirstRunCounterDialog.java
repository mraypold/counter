/*
 * 
 * 
 * Documentation: http://developer.android.com/guide/topics/ui/dialogs.html
 */

package com.raypold.raypoldcounter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment; // Can also import other if bugs
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

public class FirstRunCounterDialog extends DialogFragment {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    builder.setView(inflater.inflate(R.layout.create_counter, null))
	    /* Do not give user option to select no */
	           .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	   // Create the new counter
	                   // sign in the user ...
	               }
	           });      
		
		return builder.create();
	}

}
