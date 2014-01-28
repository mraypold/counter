package com.raypold.raypoldcounter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CounterArrayAdapter extends ArrayAdapter<String>{

	private List<String> counters = null;
	
	public CounterArrayAdapter(Context context, int resource, List<String> items) {
		super(context, resource, items);
		this.counters = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View lineToDisplay = convertView;
				
		if (lineToDisplay == null) {
			LayoutInflater inflater = (LayoutInflater)MainActivity.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			lineToDisplay = inflater.inflate(R.layout.summary_saved_counter, null);
		}
		
		String item = counters.get(position);
		
		if(item != null) {
			TextView name = (TextView)lineToDisplay.findViewById(R.id.counterListName);
			
			Serialize serialize = new Serialize();
			CountersMap map = new CountersMap();
			map = serialize.deserializeCountersMap();
			TextView count = (TextView)lineToDisplay.findViewById(R.id.counterListCount);
			
			name.setText(item);
			count.setText(String.valueOf(map.getCount(item)));
			
		}
		
		return lineToDisplay;
				
	}
	
	@Override  
	public String getItem(int position) {     
	    return counters.get(position);  
	}  

}