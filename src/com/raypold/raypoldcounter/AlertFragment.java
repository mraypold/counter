package com.raypold.raypoldcounter;

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
	private String confirm = "Yes";
	private String decline = "No";
	public static Boolean confirmClicked = false;
	
	/* No constructors allowed per android guidelines unless use suppress warnings */
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
        		/* User selects yes */
               .setPositiveButton(confirm, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   AlertFragment.confirmClicked = false;
                   }
               })
                /* User selects no */
               .setNegativeButton(decline, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   AlertFragment.confirmClicked = true;
                   }
               });
        
        return builder.create();
    	
    }

	public void setMessage(String message) {
		this.message = message;
	}


	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}


	public void setDecline(String decline) {
		this.decline = decline;
	}
	
	public Boolean getConfirmClicked() {
		return confirmClicked;
	}
}
