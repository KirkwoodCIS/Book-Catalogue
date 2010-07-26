/*
 * @copyright 2010 Evan Leybourn
 * @license GNU General Public License
 * 
 * This file is part of Book Catalogue.
 *
 * Book Catalogue is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Book Catalogue is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Book Catalogue.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.eleybourn.bookcatalogue;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * 
 * This is the Administration tab host. It contains three tabs 
 * 1. About this app - links to my website and email
 * 2. Functions to export and import books and functions to manage bookshelves.
 * 3. Donate tab
 * 
 * @author Evan Leybourn
 */
public class Administration extends TabActivity {
	public static final String TAB = "tab";
	public static final int TAB_FUNCTIONS = 0;
	public static final int TAB_DONATE = 1;
	public static final int TAB_ABOUT = 2;
	public int currentTab = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost);
		
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost();  // The activity TabHost
		TabHost.TabSpec spec;  // Resusable TabSpec for each tab
		Intent intent;  // Reusable Intent for each tab
		
		//get the passed parameters
		Bundle extras = getIntent().getExtras();
		
		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, Help.class);
		if (extras != null) {
			intent.putExtras(extras);
		}
		// Initialise a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("help").setIndicator(res.getString(R.string.help)).setContent(intent);
		tabHost.addTab(spec);
		
		// Do the same for the other tabs
		intent = new Intent().setClass(this, AdministrationFunctions.class);
		if (extras != null) {
			intent.putExtras(extras);
		}
		// Initialise a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("admin_functions").setIndicator(res.getString(R.string.administration_label)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, AdministrationDonate.class);
		if (extras != null) {
			intent.putExtras(extras);
		}
		spec = tabHost.newTabSpec("admin_donate").setIndicator(res.getString(R.string.donate_label)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, AdministrationAbout.class);
		if (extras != null) {
			intent.putExtras(extras);
		}
		spec = tabHost.newTabSpec("admin_about").setIndicator(res.getString(R.string.about_label)).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(currentTab);
	}
	
	/**
	 * This is a straight passthrough
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		setResult(resultCode, intent);
		finish();
	}

}