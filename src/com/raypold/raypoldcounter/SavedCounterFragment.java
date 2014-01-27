package com.raypold.raypoldcounter;

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
public class SavedCounterFragment extends Fragment {

	View inflatedView;
	
	public SavedCounterFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflatedView = inflater.inflate(R.layout.fragment_saved_counter, container,
				false);
		
		String[] items = {"one", "two", "three"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), 
				R.layout.summary_saved_counter, items);
		
		ListView list = (ListView)inflatedView.findViewById(R.id.listViewSavedCounters);
		list.setAdapter(adapter);
		
		return inflatedView;
	}

}