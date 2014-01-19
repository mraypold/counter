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
	
	public static final String USERPREFERENCES = "userPreferences";
	public static String workingCounterName;
	
	SharedPreferences preferences, savedCounters;
	
	Preferences userPreferences;
	Counter openCounter;
	
	View inflatedView;
		
	public CounterFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		inflatedView = inflater.inflate(R.layout.fragment_counter, container, false);
		
		preferences = this.getActivity().getSharedPreferences(USERPREFERENCES, 0);
		userPreferences = new Preferences(preferences);
		workingCounterName = userPreferences.getLastOpenCounter();
		
		savedCounters = this.getActivity().getSharedPreferences(workingCounterName, 0);
		openCounter = new Counter(workingCounterName, savedCounters);

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

	public void setTextDisplay(){				
		/* findViewById() doesn't work inside a fragment. Must use on inflatedView */
		TextView counterTextView = (TextView) inflatedView.findViewById(R.id.counterNameView);
		counterTextView.setText(workingCounterName);
	}
	
	public void displayCount(){
		String currentCount = String.valueOf(openCounter.getCurrentCount());	
		TextView countTextView = (TextView) inflatedView.findViewById(R.id.countDisplay);
		countTextView.setText(currentCount);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

}
