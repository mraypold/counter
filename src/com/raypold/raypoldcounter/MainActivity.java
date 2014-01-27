/*
 * Author Michael Raypold
 * 
 * A copy of the license is available in LICENSE or at http://www.gnu.org/licenses/gpl-2.0.html
 * 
 * MainActivity initializes the application layout.
 *  - This includes setting up the action bar and tabs
 *  - Determine if this is the first time the application has run
 *  - Creating a counter if none exist on disk.
 * 
 * Tutorials used for code on this page include:
 * http://www.androidhive.info/2013/10/android-tab-layout-with-swipeable-views-1/
 * https://www.youtube.com/watch?v=VVahIc8yENk
 * https://www.youtube.com/watch?v=iEl0ylVvZho
 */

package com.raypold.raypoldcounter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements TabListener {

	/* NUMBEROFTABS must be the same as the size of the tabNames list!
	 * */
	public final static int NUMBEROFTABS = 3;
	public String[] tabNames = {};  // see setTabNames();       
													
	public ActionBar actionBar;
	public static ViewPager viewPager;
	public static SharedPreferences preferences, savedCounters;
	private static FragmentManager fragment;
	public static Context context; // Going to need to access this directly for the serialize class
	
	@Override
	protected void onCreate(Bundle arg0) {
		
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		
		context = getApplicationContext();
		setFragmentManager();
		detectFirstRun();
		
		/* Open the sharedPreference files for the actionBar functions extended in ActionBarHandler */
		preferences = getSharedPreferences("userPreferences", 0);
		savedCounters = getSharedPreferences("savedCounters", 0);
		
		/* Create the action bar */
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));

		setTabNames();
		
		/* Create the navigation tabs and add them to the action bar */
		for(String tabName : tabNames){
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
    
    /* Determine if this is the first time the application has run */
    private void detectFirstRun() {
    	
    	Preferences preferences = new Preferences(getBaseContext());
    	Boolean firstRun = preferences.isFirstRun();
    	
    	if(firstRun == true) {
    		createFirstCounter();
    		preferences.setFirstRun();
    		
    	}
    	
    }

    /* Create a default counter for the user on the first run */
    public void createFirstCounter() {
		String defaultCounterName = getString(R.string.defaultCounterName);

		CountersMap counters = new CountersMap();
		Counter counter = new Counter(defaultCounterName);
		counters.insertCounterObject(counter);
	
		Preferences preferences = new Preferences(getBaseContext());
		preferences.setLastOpenCounter(defaultCounterName);
		
		/* Serialize the counter and the counter map to prepare for CounterFragment*/
		Serialize serialize = new Serialize();
		serialize.serializeCountersMap(counters);
		serialize.serializeCounter(counter);

		Toast.makeText(getBaseContext(), getString(R.string.defaultCounterMessage), Toast.LENGTH_LONG).show();

    }

    /* Remember to change NUMBEROFTABS if adding/removing tabs here */
    private void setTabNames() {
    	tabNames = new String[] { getResources().getString(R.string.counter),
    							  getResources().getString(R.string.saved_counters),
    							  getResources().getString(R.string.summary)
    							};
    }
    
	/* Unfortunately, I couldn't get certain functions in ActionBarHandler to work without
     * the following getters and setters.
     * 
     * Ideally, I think there is an alternative, more elegant way to code this.
     */
    public void setFragmentManager() {
    	MainActivity.fragment = getFragmentManager();
    }
    
    public static FragmentManager getFragment () {
		return fragment;
    }
    
}
