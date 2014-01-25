package com.raypold.raypoldcounter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class Serialize extends Activity{
	
	private final String COUNTERSFILE = "countersFile";
    
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
			// TODO find android specific way of catching an error
		}
	}

	public void serializeCountersMap(CountersMap counters) {
		try {
			FileOutputStream fileStream = new FileOutputStream(COUNTERSFILE);
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
		Counter counter = null;
/*		try {
			FileInputStream fileStream = getApplicationContext().openFileInput(counterName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		try {
			//FileInputStream fileStream = new FileInputStream(counterName);
			//MainActivity.setContext();
			
			FileInputStream fileStream = getApplicationContext().openFileInput(counterName);
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
		CountersMap map = null;
/*		try {
			FileInputStream fileStream = getApplicationContext().openFileInput(counterName);
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}*/
		
		// Adding context seemed to have fixed this...
		try {
			FileInputStream fileStream = MainActivity.context.openFileInput(COUNTERSFILE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

/*		try {
			FileInputStream fileStream = getApplicationContext().openFileInput(COUNTERSFILE);
			//FileInputStream fileStream = getApplicationContext().openFileInput(COUNTERSFILE);

			ObjectInputStream objectStream = new ObjectInputStream(fileStream);

			map = (CountersMap) objectStream.readObject();
			
			objectStream.close();
			fileStream.close();
			return map;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
		}*/
		
		return map;
	}
	
}
