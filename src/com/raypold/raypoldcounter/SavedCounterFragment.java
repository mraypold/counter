package com.raypold.raypoldcounter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class SavedCounterFragment extends Fragment {

	private static View inflatedView;
	private static ArrayList<String> items;
	private static ListView list;
	private static ArrayAdapter<String> adapter;
	private static Activity activity;
	
	public SavedCounterFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflatedView = inflater.inflate(R.layout.fragment_saved_counter, container,
				false);

		activity = this.getActivity();

		refreshAdapter();
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int selectedCounter,
					long arg3) {

				/* Open the ordered list of counters */
				Serialize serialize = new Serialize();
				CountersMap counters = serialize.deserializeCountersMap();
				
				ArrayList<String>orderedList = counters.getOrderedList();

				String counterName = orderedList.get(selectedCounter);
				
				/* Set the new open counter */
				Preferences userPreferences = new Preferences(MainActivity.context);
				userPreferences.setLastOpenCounter(counterName);
				
				/* Referesh the displays */
				CounterFragment.refreshDisplay();
				SavedCounterFragment.refreshAdapter();
				CounterSummaryFragment.refreshSummary();
				
				/* Display switched message */
				Toast.makeText(MainActivity.context, getString(R.string.switchedCounters) + 
						" " + counterName, Toast.LENGTH_SHORT).show();
				
			}	
		});
		
		return inflatedView;
	}
	
	public static void refreshAdapter() {
		//CountersMap counters = new CountersMap();
		Serialize serialize = new Serialize();
		CountersMap counters = serialize.deserializeCountersMap();
		
		items = counters.getOrderedList();
		
		/* Concatenate the strings so we don't need a custom array adapter for a simple alteration */
		ArrayList<String> concatItems = new ArrayList<String>();
		
		for(int i = 0; i < items.size(); i++){
			String counterName = items.get(i);
			concatItems.add(i, 
					counterName +
					"       " +
					counters.getCount(counterName));
		}
		
		items = concatItems;
		
		adapter = new ArrayAdapter<String>(activity, R.layout.saved_item, items);
		
		list = (ListView)inflatedView.findViewById(R.id.listViewSavedCounters);
		list.setAdapter(adapter);
		
		adapter.notifyDataSetChanged();
	}
	
}