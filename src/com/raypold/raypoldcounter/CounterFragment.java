/*
 * Author: Michael Raypold
 * 
 * A copy of the license is available in LICENSE
 * 
 * CounterFragment handles the logic associated with the XML UI.
 * 	- Incrementing/decrementing through calling the appropriate Counter()
 *  - The UI is available in /res/layout/fragment_counter.xml
 * 
 * Documentation from Stackoverflow which was helpful:
 * http://stackoverflow.com/questions/15322237/android-how-do-i-update-my-textview-in-a-fragment
 */

package com.raypold.raypoldcounter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CounterFragment extends Fragment implements View.OnClickListener {
	
	
	private static String workingCounterName;	
	private static Preferences userPreferences;
	private static Counter openCounter;
	private static View inflatedView;
		
	public CounterFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		inflatedView = inflater.inflate(R.layout.fragment_counter, container, false);
		
		/* Determine the last open counter and set to the workingCounterName */		
		userPreferences = new Preferences(getActivity());
		workingCounterName = userPreferences.getLastOpenCounter();

		/* Deserialize the necessary counter */
		Serialize deserialize = new Serialize();
		openCounter = deserialize.deserializeCounter(workingCounterName);
		
		/* Refresh the UI */
		setTextDisplay();
		displayCount();
		
		/* Increment button logic */
		Button increment = (Button) inflatedView.findViewById(R.id.incrementBtn);
		increment.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				openCounter.incrementCount();
				displayCount();
				/* Updates the list in saved counters tab to reflect new count */
				SavedCounterFragment.refreshAdapter();
				/* Android takes care of updating summary tab itself */
			}
			
		});
		
		/* Decrement button logic */
		Button decrement = (Button) inflatedView.findViewById(R.id.decrementBtn);
		decrement.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				openCounter.decrementCount();
				displayCount();
				/* Updates the list in saved counters tab to reflect new count */
				SavedCounterFragment.refreshAdapter();
				/* Android takes care of updating summary tab itself */
			}
			
		});
		
		return inflatedView;
		
	}
	
	public static String getWorkingCounterName() {
		return workingCounterName;
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
		/* 
		 * Not an ideal solution since high I/O overhead, but ensures that the counter stays in sync with changes from
		 * the actionBar and other fragment tabs.
		 * 
		 * If redesigning the application, I would keep an open counter in the MainActivity and then have getters and setters
		 * to ensure all fragments are using the same counter.
		 * */
		
		workingCounterName = userPreferences.getLastOpenCounter();
		Serialize deserialize = new Serialize();
		openCounter = deserialize.deserializeCounter(workingCounterName);

		setTextDisplay();
		displayCount();
		SavedCounterFragment.refreshAdapter();
	}
	
	@Override
	public void onClick(View v) {
		// Auto-generated method stub
	}

}
