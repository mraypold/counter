package com.raypold.raypoldcounter;

import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class SavedCounterFragment extends Fragment {

	public static View inflatedView;
	public static List<String> items;
	public static ListView list;
	public static CounterArrayAdapter adapter;
	public static Activity activity;
	public SavedCounterFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflatedView = inflater.inflate(R.layout.fragment_saved_counter, container,
				false);

		activity = this.getActivity();
		
		refreshAdapter();
		
		return inflatedView;
	}
	
	/* SLOW... Find a better solution if time. Perhaps only update view if fragment selected */
	public static void refreshAdapter() {
		CountersMap counters = new CountersMap();
		Serialize serialize = new Serialize();
		counters = serialize.deserializeCountersMap();

		items = counters.getOrderedList();
		adapter = new CounterArrayAdapter(activity, R.layout.summary_saved_counter, items);

		list = (ListView)inflatedView.findViewById(R.id.listViewSavedCounters);
		list.setAdapter(adapter);
	}
	
}