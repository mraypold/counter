package com.raypold.raypoldcounter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
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
	    
		return inflatedView;
	}

}
