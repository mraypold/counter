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
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
	
	public ActionBar actionBar;
	public ViewPager viewPager;
	public static SharedPreferences preferences, savedCounters;
	
	@Override
	protected void onCreate(Bundle arg0) {
		
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);

		/* Open the sharedPreference files for the actionBar functions extended in ActionBarHandler */
		preferences = getSharedPreferences("userPreferences", 0);
		savedCounters = getSharedPreferences("savedCounters", 0);
		
		/* Create the action bar */
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));


		/* Create the navigation tabs and add them to the action bar */
		for(String tabName : TABNAMES){
			actionBar.addTab(actionBar.newTab().setText(tabName).setTabListener(this));
		}
				
		/* Synchronize the the swiping and the highlighted tab view */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int arg0) {
				actionBar.setSelectedNavigationItem(arg0);			
			}
		});			
	}

	/* Start: Define what happens when a tab is selected */
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		/* When user clicks a tab move to that view */
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
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
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		ActionBarHandler handler = new ActionBarHandler(item, getApplicationContext());
    	return handler.getAction();
    }
    
}
