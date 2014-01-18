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

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CounterFragment extends Fragment implements View.OnClickListener {
	
	public static final String USERPREFERENCES = "userPreferences";
	public static final String LASTCOUNTER = "lastOpenCounter";
	public static final String DEFAULTNAME = "RenameThisCounter";
	
	/* Simpler to declare these variable instead of passing them between methods each time */
	int workingCount;
	String workingCounterName;
	View inflatedView;
		
	// TODO ? is this actually required? Doesn't java create it automatically?
	public CounterFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		inflatedView = inflater.inflate(R.layout.fragment_counter, container, false);
				
		setTextDisplay();
		displayCount();
		
		/* Increment button logic */
		Button increment = (Button) inflatedView.findViewById(R.id.incrementBtn);
		increment.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				incrementCount(workingCounterName);
				displayCount();
				Log.d("MyTag", "Increment was pressed");
			}
			
		});
		
		/* Decrement button logic */
		Button decrement = (Button) inflatedView.findViewById(R.id.decrementBtn);
		decrement.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				decrementCount(workingCounterName);
				displayCount();
				Log.d("MyTag", "Decrement was pressed");		
			}
			
		});
		
		// Inflate the layout for this fragment
		return inflatedView;
		
	}

	/* Sets the name of the text to the last open counter */
	public void setTextDisplay(){
		/* Retrieve user preferences from the last session */	
		SharedPreferences preferences = this.getActivity().getSharedPreferences(USERPREFERENCES ,0);
		workingCounterName = preferences.getString(LASTCOUNTER, DEFAULTNAME);
		
		// TODO . If defaultname, then show help dialogue.
		
		
		/* findViewById() doesn't work inside a fragment. Must use on inflatedView */
		TextView counterTextView = (TextView) inflatedView.findViewById(R.id.counterNameView);
		counterTextView.setText(workingCounterName);
	}
	
	/* Retrieve the count from the saved counters file and then display it */
	public void displayCount(){
		String currentCount = String.valueOf(getCount(workingCounterName));
		
		TextView countTextView = (TextView) inflatedView.findViewById(R.id.countDisplay);
		
		countTextView.setText(currentCount);
	}
	
	/* Increment the count and store the date-time */
	public void incrementCount(String counterName){
		/* Increment the count in the savedCounters file */
		SharedPreferences storedCounters = this.getActivity().getSharedPreferences("savedCounters", 0);
		int oldCount = storedCounters.getInt(counterName, 0);
		
		Editor editor = storedCounters.edit();
		editor.putInt(counterName, ++oldCount);
		editor.commit();

		/* Save the date-time of the increment */
		// TODO
		// call save count
		
	}
	
	/* Decrement the count and store the date-time */
	public void decrementCount(String counterName){
		/* Increment the count in the savedCounters file */
		SharedPreferences storedCounters = this.getActivity().getSharedPreferences("savedCounters", 0);
		int oldCount = storedCounters.getInt(counterName, 0);
		
		Editor editor = storedCounters.edit();
		editor.putInt(counterName, --oldCount);
		editor.commit();
		
		/* Save the date-time of the decrement */
		// TODO
		// call save count
	}
	
	/* Retrieve the count for the specified counter name. If counter doesn't exist, return 0 */
	public int getCount(String counterName){
		SharedPreferences storedCounters = this.getActivity().getSharedPreferences("savedCounters", 0);
		int currentCount = storedCounters.getInt(counterName, 0);
		
		return currentCount;
	}
	
	public void saveCount(){
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
