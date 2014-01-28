package com.raypold.raypoldcounter;

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

	private View inflatedView;

	public CounterSummaryFragment() {
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflatedView = inflater.inflate(R.layout.fragment_counter_summary, container,
				false);
		String[] items = {"one", "two", "three"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.summary_item, items);
		
		ListView list = (ListView)inflatedView.findViewById(R.id.listViewSummary);
		list.setAdapter(adapter);
	    
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

	
	public void refreshSummary() {
		
	}
}
