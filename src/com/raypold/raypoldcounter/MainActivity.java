/*
 * MainActivity.java initializes the application layout.
 * This includes setting up the action bar and tabs
 * 
 * 
 * Tutorials used for code on this page include:
 * http://www.androidhive.info/2013/10/android-tab-layout-with-swipeable-views-1/
 * https://www.youtube.com/watch?v=VVahIc8yENk
 * https://www.youtube.com/watch?v=iEl0ylVvZho
 * 
 */

package com.raypold.raypoldcounter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;

public class MainActivity extends FragmentActivity implements TabListener {

	/* Constant Declarations:
	 * 
	 * This uses slightly more memory, but will make adding/subtracting tabs easier
	 * in the event the the UI changes later on.
	 * 
	 * NUMBEROFTABS must be the same as the size of the TABNAMES list!
	 * 
	 */
	public final static int NUMBEROFTABS = 3;
	public final static String[] TABNAMES = {"Counter",
											 "Saved Counters",
											 "Summary" 
											};
	
	ActionBar actionBar;
	ViewPager viewPager;
	
	@Override
	protected void onCreate(Bundle arg0) {
		
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		
		/* Create the action bar */
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new fragmentAdapter(getSupportFragmentManager()));

		/* Create the navigation tabs and add them to the action bar */
		for(String tabName : TABNAMES){
			actionBar.addTab(actionBar.newTab().setText(tabName).setTabListener(this));
		}
				
		/* Syncs the the swiping and the highlighted tab view */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onPageSelected(int arg0) {
				/* When user swipes the arg0 is updated. This syncs the tab highlighted
				 * with currently shown view resulting from the swipe.
				 */
				actionBar.setSelectedNavigationItem(arg0);			
			}
		});			
	}

	/* Start: Define what happens when a tab is selected */
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		/* When user clicks a tab move to that view */
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}
	/* End: Define what happens when a tab is selected */
	
	/* onCreateOptionsMenu creates the menu above the tabs */
	/* see res/menu/activity_main_actions.xml for layout */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

/* Adapter will take data and display it to screen */
class fragmentAdapter extends FragmentPagerAdapter{

	/* Constructor */
	public fragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		/* Returns the UI for the requested fragment */
		/* The corresponding .java class handles the UI functionality */
		Fragment fragment = null;
		switch(arg0){
		case 0:
			return fragment = new CounterFragment();
		case 1:
			return fragment = new SavedCounterFragment();
		case 2:
			return fragment = new CounterSummaryFragment();
		}
		
		/* This return should never execute */
		// TODO error checking to ensure following line doesn't execute
		return fragment;
	}

	@Override
	public int getCount() {
		return MainActivity.NUMBEROFTABS;
	}
	
}