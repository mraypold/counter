package com.raypold.raypoldcounter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/* Adapter will take data and display it to screen */
class FragmentAdapter extends FragmentPagerAdapter{
	
	public FragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment = null;
		switch(arg0){
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