/*
 * Author: Michael Raypold
 * 
 * Concatenates counter counts with dates.
 * 
 * Extends DateRetriever which returns String of dates per time period.
 * 
 * PrettyDate with not return as string if the count == 0 for that time period.
 * 
 */
package com.raypold.raypoldcounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class PrettyDate extends DateRetriever {

	public PrettyDate(Counter counter) {
		super(counter);
	}

	/*
	 * Takes getDatesByHour and formats strings according to project
	 * specifications
	 */
	public ArrayList<String> prettyDatesByHour() {
		ArrayList<String> datesByHour = new ArrayList<String>(
				super.getDatesByHour());

		/* Find the frequency of each date so that we can concat date with count */
		DateOrderFrequency dateObject = findFrequency(datesByHour);
		datesByHour = concatStrings(dateObject);

		return datesByHour;

	}

	/*
	 * Takes getDatesByHour and formats strings according to project
	 * specifications
	 */
	public ArrayList<String> prettyDatesByDay() {
		ArrayList<String> datesByDay = new ArrayList<String>(
				super.getDatesByDay());

		/* Find the frequency of each date so that we can concat date with count */
		DateOrderFrequency dateObject = findFrequency(datesByDay);
		datesByDay = concatStrings(dateObject);

		return datesByDay;

	}

	/*
	 * Takes getDatesByWeek and formats strings according to project
	 * specifications
	 */
	public ArrayList<String> prettyDatesByWeek() {
		ArrayList<String> datesByWeek = new ArrayList<String>(
				super.getDatesByWeek());

		/* Find the frequency of each date so that we can concat date with count */
		DateOrderFrequency dateObject = findFrequency(datesByWeek);
		datesByWeek = concatStrings(dateObject);

		/* Concat with Week on the front of the returned list */
		datesByWeek = concatWithPrefix(datesByWeek, "Week of ");

		return datesByWeek;

	}

	/*
	 * Takes getDatesByMonth and formats strings according to project
	 * specifications
	 */
	public ArrayList<String> prettyDatesByMonth() {
		ArrayList<String> datesByMonth = new ArrayList<String>(
				super.getDatesByMonth());

		/* Find the frequency of each date so that we can concat date with count */
		DateOrderFrequency dateObject = findFrequency(datesByMonth);
		datesByMonth = concatStrings(dateObject);

		/* Concat with Month on the front of the returned list */
		datesByMonth = concatWithPrefix(datesByMonth, "Month of ");

		return datesByMonth;

	}

	/* Finds the frequency of counts per specified time period */
	public DateOrderFrequency findFrequency(ArrayList<String> datesPerPeriod) {
		/* Remove duplicates by creating a set while preserving order */
		LinkedHashSet<String> dateSet = new LinkedHashSet<String>(
				datesPerPeriod);
		HashMap<String, Integer> dateMap = new HashMap<String, Integer>();

		/* Use the set to count multiple dates in datesPerPeriod */
		for (String date : dateSet) {
			Integer occurance = Collections.frequency(datesPerPeriod, date);
			dateMap.put(date, occurance);
		}

		return new DateOrderFrequency(dateSet, dateMap);
	}

	public ArrayList<String> concatStrings(DateOrderFrequency dateObject) {
		ArrayList<String> datesFinal = new ArrayList<String>();
		LinkedHashSet<String> dateSet = dateObject.getDateSet();
		HashMap<String, Integer> dateMap = dateObject.getDateMap();

		for (String date : dateSet) {
			String finalDate;
			finalDate = date.concat(" -- ");
			/* Concat the number of counts */
			finalDate = finalDate.concat(String.valueOf(dateMap.get(date)));
			datesFinal.add(finalDate);
		}

		return datesFinal;
	}

	private ArrayList<String> concatWithPrefix(ArrayList<String> list,
			String prefix) {
		ArrayList<String> withPrefix = new ArrayList<String>();

		for (String date : list) {
			withPrefix.add(prefix.concat(date));
		}

		return withPrefix;
	}
}
