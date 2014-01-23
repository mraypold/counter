package com.raypold.raypoldcounter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class CounterSave extends Activity{
    
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
	
	public void deserialize(String counterName, SharedPreferences preferences) {
		//Counter counter = null;
		try {
			FileInputStream fileStream = getApplicationContext().openFileInput(counterName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

/*		try {
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
			Log.v("stuff", counterName);
			e.printStackTrace();
		}
		catch (ClassNotFoundException c) {
			Log.v("stuff", counterName);
			c.printStackTrace();
		}*/
		
	}
	
}
