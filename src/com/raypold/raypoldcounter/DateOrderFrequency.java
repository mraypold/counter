package com.raypold.raypoldcounter;

import java.util.HashMap;
import java.util.LinkedHashSet;

/*
 * Entire purpose of this class is to pass objects that weren't deemed appropriate to pass as concatenated strings
 */

public class DateOrderFrequency {
	
	private LinkedHashSet<String> dateSet;;
	private HashMap<String, Integer> dateMap;
	
	public DateOrderFrequency(LinkedHashSet<String> dateSet, HashMap<String, Integer> dateMap) {
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
