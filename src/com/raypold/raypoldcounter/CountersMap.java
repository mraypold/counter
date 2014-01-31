/*
 *  Author: Michael Raypold
 *  
 *  A copy of the license is available in LICENSE
 *  
 *  CountersMap holds a map of all the counter names, their count and last update.
 *  It doesn't hold the actual counter since only the name and counter are the important information.
 *  
 *  The intention is to provide immediate O(1) access to the count for a counter when generating the saved counters and summary
 *  lists with the map data type. Hopefully this is quicker and uses significantly less memory then holding an ArrayList of Counters()  
 */
package com.raypold.raypoldcounter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class CountersMap implements Serializable {

	protected HashMap<String, Integer> countersMap;

	/* See note on class design in header on why not hold an array of Counters() */
	public CountersMap() {
		super();
		this.countersMap = new HashMap<String, Integer>();
	}
	
	/* There exist two ways to save a counter */
	/* First method - Pass the counter object */
	public void insertCounterObject(Counter counter) {
		String counterName = counter.getCounterName();
		Integer count = counter.getCurrentCount();
		countersMap.put(counterName, count);
	}
	
	/* Second method - Manually pass counter name and the count */
	public void insertCounter(String counterName, Integer count) {
		countersMap.put(counterName, count);
	}

	public Integer getCount(String counterName) {
		return this.countersMap.get(counterName);
	}
	
	public void setCount(String counterName, Integer count) {
		countersMap.put(counterName, count);
	}
	
	public void deleteCounter(String counterName) {
		countersMap.remove(counterName);
	}
	
	public Boolean isEmpty() {
		return countersMap.isEmpty();
	}
	
	/* Not case sensitive.. Need to see how android file system handles case */
	/* Intended to be used during creation of new counters from the action bar so as to 
	 * not overwrite .dat serialized class files.
	 * */
	public Boolean contains(String counterName) {
		return countersMap.containsKey(counterName);
	}
	
	/* Return the counter with highest count */
	public String getLargestCountName() {
		String counterName = null;
		Integer highestCount = 0;
		
		/* Iteratate over set of keys and compare each value to the saved
		 * highestCount.
		 */
		for (String key : countersMap.keySet()) {
			Integer keyCount = countersMap.get(key);
			
			if (keyCount >= highestCount) {
				counterName = key;
				highestCount = keyCount;
			}
			
		}
		
		return counterName;
	}
	
	public ArrayList<String> getOrderedList() {
		/* Copy the map so we aren't destroying it */
		// This shallow copy bug took me over an hour to find :(
		HashMap<String, Integer> copiedMap = new HashMap<String, Integer>(countersMap);
		ArrayList<String> orderedCounters = new ArrayList<String>();
		
		Integer size = countersMap.size();
		
		/* Add the next largest counter to the orderedCounters array until none left */
		while(size > 0) {
			String largestCounter = getLargestCountName();
			orderedCounters.add(largestCounter);
			this.countersMap.remove(largestCounter);
			size--;
		}
		
		/* Restore the old map */
		countersMap = new HashMap<String, Integer>(copiedMap);
		
		return orderedCounters;
		
	}
}
