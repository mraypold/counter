/*
 * This class primarily handles the UI logic for the counter fragment.
 * 
 * Since 
 * 
 * Documentation/Stackoverflow which was helpful:
 * http://stackoverflow.com/questions/15322237/android-how-do-i-update-my-textview-in-a-fragment
 * 
 * 
 */

package com.raypold.raypoldcounter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CounterFragment extends Fragment implements View.OnClickListener {
	
	/* Names of the sharedPreferences files */
	public static final String USERPREFERENCES = "userPreferences";
	public static final String SAVEDCOUNTERS = "savedCounters";
	
	public static String workingCounterName;
	
	SharedPreferences preferences, savedCounters;
	
	Preferences userPreferences;
	static Counter openCounter;
	
	static View inflatedView;
		
	public CounterFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		inflatedView = inflater.inflate(R.layout.fragment_counter, container, false);
		
		/* Determine the last open counter and set to the workingCounterName */
		preferences = this.getActivity().getSharedPreferences(USERPREFERENCES, 0);
		userPreferences = new Preferences(preferences);
		workingCounterName = userPreferences.getLastOpenCounter();
		
		// TODO probably don't need savedCounters anymore
		savedCounters = this.getActivity().getSharedPreferences(SAVEDCOUNTERS, 0);
		openCounter = new Counter(workingCounterName, savedCounters);

		/* Use the working counter name to deserialize */
		
		setTextDisplay();
		displayCount();
		
		/* Increment button logic */
		Button increment = (Button) inflatedView.findViewById(R.id.incrementBtn);
		increment.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				openCounter.incrementCount();
				displayCount();
			}
			
		});
		
		/* Decrement button logic */
		Button decrement = (Button) inflatedView.findViewById(R.id.decrementBtn);
		decrement.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				openCounter.decrementCount();
				displayCount();
			}
			
		});
		
		// Inflate the layout for this fragment
		return inflatedView;
		
	}

	public static void setTextDisplay() {				
		/* findViewById() doesn't work inside a fragment. Must use on inflatedView */
		TextView counterTextView = (TextView) inflatedView.findViewById(R.id.counterNameView);
		counterTextView.setText(workingCounterName);
	}
	
	public static void displayCount() {
		String currentCount = String.valueOf(openCounter.getCurrentCount());	
		TextView countTextView = (TextView) inflatedView.findViewById(R.id.countDisplay);
		countTextView.setText(currentCount);
	}
	
	public static void refreshDisplay() {
		// TODO. THis is incorrect. Need to update workingCounterName
		setTextDisplay();
		displayCount();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

}
