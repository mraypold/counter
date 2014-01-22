package com.raypold.raypoldcounter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.SharedPreferences;

public class CounterSave {

	/* Serialize the counter class with the name of the counter as the filename */
	public void serializeCounter(Counter counter) {
		try {
			FileOutputStream fileStream = new FileOutputStream(counter.getCounterName());
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(counter);
			
			fileStream.flush();
			objectStream.flush();
			objectStream.close();
			fileStream.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			// TODO maybe shut down android program?
		}
	}
	
	public Counter deserialize(String counterName, SharedPreferences preferences) {
		Counter counter = null;
		
		try {
			FileInputStream fileStream = new FileInputStream(counterName);
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);

			counter = (Counter) objectStream.readObject();
			
			objectStream.close();
			fileStream.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		return counter;
	}
	
}
