/*
 *  Author: Michael Raypold
 *  
 *  A copy of the license is available in LICENSE
 */
package com.raypold.raypoldcounter;

import java.util.HashMap;
import java.util.LinkedHashSet;

/*
 * Entire purpose of this class is to pass objects that weren't deemed appropriate to pass as concatenated strings
 */

public class DateOrderFrequency {

	/* LinkedHashSet is used to maintain order of dates */
	/*
	 * HashMap is for speed since we will know the name when iterating over
	 * LinkedHashSet
	 */
	private LinkedHashSet<String> dateSet;;
	private HashMap<String, Integer> dateMap;

	public DateOrderFrequency(LinkedHashSet<String> dateSet,
			HashMap<String, Integer> dateMap) {
		super();
		this.dateSet = dateSet;
		this.dateMap = dateMap;
	}

	public LinkedHashSet<String> getDateSet() {
		return dateSet;
	}

	public HashMap<String, Integer> getDateMap() {
		return dateMap;
	}

}
