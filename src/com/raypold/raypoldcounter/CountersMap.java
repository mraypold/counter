/*
 *  Author: Michael Raypold
 *  
 *  A copy of the license is available in LICENSE
 *  
 *  CountersMap holds a map of all the counter names, their count and last update.
 *  It doesn't hold the actual counter since only the name and counter are the important information.
 *  
 *  The intention is to provide immediate O(1) access to the count for a counter when generating the saved counters and summary
 *  lists with the map data type.  
 */
package com.raypold.raypoldcounter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@SuppressWarnings("serial")
public class CountersMap implements Serializable {

	private Map<String, Integer> countersMap;

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

	public void setCount(String counterName, Integer count) {
		countersMap.put(counterName, count);
	}
	
	public void deleteCounter(String counterName) {
		countersMap.remove(counterName);
	}
	
	public Boolean isEmpty() {
		return countersMap.isEmpty();
	}
	
	/* Intended to be used during creation of new counters from the action bar so as to 
	 * not overwrite .dat serialized class files.
	 * */
	public Boolean containsCounter(String counterName) {
		return countersMap.containsKey(counterName);
	}
	
	/* Return the counter with highest count */
	public String getLargestCountName() {
		String counterName = null;
		Integer highestCount = 0;
		
		for (String key : countersMap.keySet()) {
			Integer keyCount = countersMap.get(key);
			
			if (keyCount >= highestCount) {
				counterName = key;
				highestCount = keyCount;
			}
			
		}
		
		return counterName;
	}
	
	// TODO 
	public Set<Entry<String, Integer>> getOrderedList() {
		Set<Entry<String, Integer>>counterSet = countersMap.entrySet();
		Set<Entry<String, Integer>>orderedSet = null;
		//http://javarevisited.blogspot.ca/2012/12/how-to-sort-hashmap-java-by-key-and-value.html		
		
		return orderedSet;
		
	}
}
