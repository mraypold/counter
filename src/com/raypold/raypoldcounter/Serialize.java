package com.raypold.raypoldcounter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import android.app.Activity;

public class Serialize extends Activity {
	
	private final String COUNTERSFILE = "countersFile.dat";
    
	/* Serialize the counter class with the name of the counter as the filename */
	public void serializeCounter(Counter counter) {
		try {
			FileOutputStream fileStream = MainActivity.context.openFileOutput(
					counter.getCounterName().concat(".dat"), 0);
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

			objectStream.writeObject(counter);

			fileStream.flush();
			objectStream.flush();
			objectStream.close();
			fileStream.close();
		}
		catch (IOException e) {
			e.printStackTrace();
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
			FileInputStream fileStream = MainActivity.context.openFileInput(counterName.concat(".dat"));
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);

			counter = (Counter) objectStream.readObject();

			objectStream.close();
			fileStream.close();

			return counter;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		return counter;
	}

	public CountersMap deserializeCountersMap() {
		try {
			FileInputStream fileStream = MainActivity.context.openFileInput(COUNTERSFILE);
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);

			CountersMap map = (CountersMap) objectStream.readObject();

			objectStream.close();
			fileStream.close();
			
			return map;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		CountersMap map = new CountersMap();
		return map;
	}
	
	public void deleteCounterFile(String counterName) {
		try {
			File counter = new File(counterName.concat(".dat"));
			counter.delete();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
