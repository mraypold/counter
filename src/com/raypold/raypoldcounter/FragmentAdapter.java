/*
 * Author: Michael Raypold
 *  
 * A copy of the license is available in LICENSE
 *  
 * The only responsibility of this adapter is to return the appropriate custom fragment classes.
 */
package com.raypold.raypoldcounter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/* Adapter will take data and display fragments to screen */
class FragmentAdapter extends FragmentPagerAdapter {

	public FragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment = null;
		switch (arg0) {
		case 0:
			return fragment = new CounterFragment();
		case 1:
			return fragment = new SavedCounterFragment();
		case 2:
			return fragment = new CounterSummaryFragment();
		}

		return fragment;
	}

	@Override
	public int getCount() {
		return MainActivity.NUMBEROFTABS;
	}

}