package com.raypold.raypoldcounter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.util.Log;

public class Serialize extends Activity {
	
	private final String COUNTERSFILE = "countersFile.dat";
    
	/* Serialize the counter class with the name of the counter as the filename */
	public void serializeCounter(Counter counter) {
		try {
	    	Log.i("degbug", "s0");

			FileOutputStream fileStream = MainActivity.context.openFileOutput(
					counter.getCounterName().concat(".dat"), 0);
	    	Log.i("degbug", "s1");

			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
	    	Log.i("degbug", "s2");

			objectStream.writeObject(counter);
	    	Log.i("degbug", "s3");

			fileStream.flush();
			objectStream.flush();
			objectStream.close();
			fileStream.close();
	    	Log.i("degbug", "serialized correctly");


		}
		catch (IOException e) {
			e.printStackTrace();
			// TODO find android specific way of catching an error
	    	Log.i("degbug", "did not serialize");

		}
	}

	public void serializeCountersMap(CountersMap counters) {
		try {
			FileOutputStream fileStream = MainActivity.context.openFileOutput(COUNTERSFILE, 0);
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(counters);
			
			fileStream.flush();
			objectStream.flush();
			objectStream.close();
			fileStream.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public Counter deserializeCounter(String counterName) {
		Counter counter = new Counter(counterName);

		try {
	    	Log.e("degbug", "c0");

			// TODO not happy having to access context directly. Find another workaround.
			FileInputStream fileStream = MainActivity.context.openFileInput(counterName.concat(".dat"));
	    	Log.e("degbug", "c1");

			ObjectInputStream objectStream = new ObjectInputStream(fileStream);
	    	Log.e("degbug", "c2");

			counter = (Counter) objectStream.readObject();
	    	Log.e("degbug", "c3");

			objectStream.close();
			fileStream.close();
	    	Log.e("degbug", "deserialized counter correctly");

			return counter;
		}
		catch (IOException e) {
			e.printStackTrace();
	    	Log.e("degbug", "did not deserialize");

		}
		catch (ClassNotFoundException c) {
	    	Log.e("degbug", "did not deserialize 2");

			c.printStackTrace();
		}
		
		return counter;
	}

	public CountersMap deserializeCountersMap() {
		//CountersMap map = new CountersMap();
    	Log.e("degbug", "in deserializeCountersMap");

		try {
			FileInputStream fileStream = MainActivity.context.openFileInput(COUNTERSFILE);
	    	Log.e("degbug", "d1");

			ObjectInputStream objectStream = new ObjectInputStream(fileStream);
	    	Log.e("degbug", "d2");

			CountersMap map = (CountersMap) objectStream.readObject();
	    	Log.e("degbug", "d3");

			objectStream.close();
	    	Log.e("degbug", "d4");

			fileStream.close();
	    	Log.e("degbug", "d5");

			return map;
		}
		catch (IOException e) {
			e.printStackTrace();
	    	Log.e("degbug", "IO except");

		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
	    	Log.e("degbug", "class not found");

		}
		CountersMap map = new CountersMap();
		return map;
	}
	
}
