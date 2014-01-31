/*
 * Author: Michael Raypold
 * 
 * Responsible for saving/loading/deleting counters and countersmap
 *  - Serializes objects in .dat to the android file system
 *  - No legitimate reason for .dat other then I got it working before JSON
 * 	- Error checking needs to be handled better. Right now, just an exception is thrown.
 *  - Error will be thrown if user physically deletes file from disk instead of having the app do it. 
 */
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

	/* Serialize the counter map containing a map of all created counters */
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
	
	/* Return a counter after deserializing with the specified counter name */
	/* Counter name is first retrieved either from CountersMap of Preferences */
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
	
	/* Permanently delete counter file from disk */
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
