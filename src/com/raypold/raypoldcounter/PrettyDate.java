package com.raypold.raypoldcounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class PrettyDate extends DateRetriever {

	public PrettyDate(Counter counter) {
		super(counter);
	}

	/* Takes getDatesByHour and formats strings according to project specifications */
	public ArrayList<String> prettyDatesByHour() {
		ArrayList<String> datesByHour = new ArrayList<String>(super.getDatesByHour());
				
		/* Find the frequency of each date so that we can concat date with count */
		DateOrderFrequency dateObject = findFrequency(datesByHour);
		
		/* Concat the date and count together */
		datesByHour = concatStrings(dateObject);
		
		return datesByHour;
		
	}
	
	/* Finds the frequency of counts per specified time period */
	public DateOrderFrequency findFrequency(ArrayList<String> datesPerPeriod) {
		/* Remove duplicates by creating a set while preserving order */
		LinkedHashSet<String> dateSet = new LinkedHashSet<String>(datesPerPeriod);
		HashMap<String, Integer> dateMap = new HashMap<String, Integer>();
		
		/* Use the set to count multiple dates in datesPerPeriod */
		for(String date : dateSet) {
			Integer occurance = Collections.frequency(datesPerPeriod, date);
			dateMap.put(date, occurance);
		}
				
		return new DateOrderFrequency(dateSet, dateMap);
	}
	
	public ArrayList<String> concatStrings(DateOrderFrequency dateObject) {
		ArrayList<String> datesFinal = new ArrayList<String>();
		LinkedHashSet<String> dateSet = dateObject.getDateSet();
		HashMap<String, Integer> dateMap = dateObject.getDateMap();
		
		for(String date : dateSet) {
			String finalDate;
			finalDate = date.concat(" -- ");
			/* Concat the number of counts */
			finalDate = finalDate.concat(String.valueOf(dateMap.get(date)));
			datesFinal.add(finalDate);
		}
		
		return datesFinal;
	}
}
