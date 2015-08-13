/*
 *  Author: Michael Raypold
 *  
 *  A copy of the license is available in LICENSE
 * 
 *  Contains all the UI functionality to display counts by their date.
 *  
 *  If users doesn't specify list order, it is assumed that order is by hour.
 */
package com.raypold.raypoldcounter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CounterSummaryFragment extends Fragment {

	private static View inflatedView;
	private static ListView list;
	private static ArrayAdapter<String> adapter;
	private static ArrayList<String> items;
	private static Activity activity;

	public CounterSummaryFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflatedView = inflater.inflate(R.layout.fragment_counter_summary,
				container, false);

		activity = this.getActivity();
		refreshSummary();

		Button changeList = (Button) inflatedView
				.findViewById(R.id.changeDateView);
		changeList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/* Launch and alert dialog asking for how to display content */
				AlertChangeListSummary changeView = new AlertChangeListSummary();
				changeView.show(MainActivity.getFragment(), "ChangeListView");
				refreshSummary();
			}

		});

		return inflatedView;
	}

	public static void refreshSummary() {
		/* Get currently open counter */
		Preferences preferences = new Preferences(MainActivity.context);
		String counterName = preferences.getLastOpenCounter();

		/* Open appropriate counter file */
		Serialize serialize = new Serialize();
		Counter counter = serialize.deserializeCounter(counterName);

		/* Print the date */
		PrettyDate date = new PrettyDate(counter);
		switch (preferences.getDisplayCountsType()) {
		case 0:
			items = date.prettyDatesByHour();
			break;
		case 1:
			items = date.prettyDatesByDay();
			break;
		case 2:
			items = date.prettyDatesByWeek();
			break;
		case 3:
			items = date.prettyDatesByMonth();
			break;
		}

		adapter = new ArrayAdapter<String>(activity, R.layout.summary_item,
				items);

		list = (ListView) inflatedView.findViewById(R.id.listViewSummary);
		list.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}
