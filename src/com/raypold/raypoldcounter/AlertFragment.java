package com.raypold.raypoldcounter;

/*
 * Helpful documentation 
 * http://developer.android.com/guide/topics/ui/dialogs.html
 */

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

@SuppressLint("ValidFragment")
public class AlertFragment extends DialogFragment {

	/* Default text display unless instructed otherwise */
	private String message = "Are you sure?";
	public Boolean confirmClicked = false;
	public Boolean responseConfirmed = false;
	
	/* No constructors allowed per android guidelines unless use suppress warnings */
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	    	
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        builder.setMessage(message)      		      
        		/* User selects yes */
               .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   confirmClicked = true;
                	   responseConfirmed = false;
                   }
               })
                /* User selects no */
               .setNegativeButton(R.string.decline, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   confirmClicked = true;
                	   responseConfirmed = false;
                   }
               });
        
        return builder.create();
    	
    }
    
	public void setMessage(String message) {
		this.message = message;
	}
		
	/* Use this to determine if dialog has been clicked */
	public Boolean isClicked() {
		return confirmClicked;
	}
	
	public Boolean response() {
		return responseConfirmed;
	}
}
