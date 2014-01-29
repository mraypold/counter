package com.raypold.raypoldcounter;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CounterSummaryFragment extends Fragment {

	private static View inflatedView;
	private static ListView list;
	private static ArrayList<String> items;
	private static ArrayAdapter<String> adapter;	

	public CounterSummaryFragment() {
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflatedView = inflater.inflate(R.layout.fragment_counter_summary, container,
				false);

		/* Get currently open counter */
		Preferences preferences = new Preferences(MainActivity.context);
		String counterName = preferences.getLastOpenCounter();
		
		/* Open appropriate counter file */
		Serialize serialize = new Serialize();
		Counter counter = serialize.deserializeCounter(counterName);
		
		/* Print the date */
		PrettyDate date = new PrettyDate(counter);
		switch(preferences.getDisplayCountsType()) {
		case 0:
			items = date.prettyDatesByHour();
		case 1:
			items = date.prettyDatesByDay();
		case 2:
			items = date.prettyDatesByWeek();
		case 3:
			items = date.prettyDatesByMonth();
		}
		
		adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.summary_item, items);

		list = (ListView)inflatedView.findViewById(R.id.listViewSummary);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener()
		{
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		    {
				Toast.makeText(MainActivity.context, "hi", 
						Toast.LENGTH_SHORT).show();

		    }
		});
		
		Button changeList = (Button) inflatedView.findViewById(R.id.changeDateView);
		changeList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/* Launch and alert dialog asking for how to display content */
				AlertChangeListSummary changeView = new AlertChangeListSummary();
				changeView.show(MainActivity.getFragment(), "ChangeListView");
			}
			
		});
		
		return inflatedView;
	}
	
}
