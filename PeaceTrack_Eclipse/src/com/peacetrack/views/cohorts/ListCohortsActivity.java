/**
 * 
 */
package com.peacetrack.views.cohorts;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.peacetrack.R;
import com.peacetrack.backend.cohorts.CohortsDAO;
import com.peacetrack.models.cohorts.Cohorts;
import com.peacetrack.views.welcome.WelcomeActivity;

/**
 * @author Pooja
 * 
 */
public class ListCohortsActivity extends ActionBarActivity {
	protected ListView cohortsListView;
	protected ArrayList<Cohorts> allCohorts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listcohorts);
	}

	@Override
	public void onResume() {
		super.onResume();
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		cohortsListView = (ListView) findViewById(R.id.cohortslistView);
		createCohortsList();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.allcohortsmenu, menu);

		// When user types i.e. query for the item in the search bar, how would
		// the search bar behave

		SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO
				return true;
			}
		};

		MenuItem searchItem = menu.findItem(R.id.action_search);
		SearchView searchView = (SearchView) MenuItemCompat
				.getActionView(searchItem);
		searchView.setOnQueryTextListener(queryTextListener);

		final MenuItem addCohort = menu.findItem(R.id.action_addcohort);

		getSupportActionBar().setDisplayShowTitleEnabled(true);
		return true;
	}

	/*
	 * Select the new screen when any icon in action bar is selected.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == android.R.id.home) {
			// app icon in action bar clicked; go home
			Intent intent = new Intent(this, WelcomeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		} else if (itemId == R.id.action_addcohort) {
			Intent intent = new Intent(this, AddCohortActivity.class);
			startActivity(intent);
		} else {
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	public void createCohortsList() {
		CohortsDAO cohortsDAO = new CohortsDAO(getApplicationContext());
		allCohorts = cohortsDAO.getAllCohorts();
		int i = 0;
		String[] cohortNames = new String[allCohorts.size()];

		for (Cohorts cohort : allCohorts) {
			cohortNames[i++] = cohort.getName();
		}
		/*
		 * String[] cohortNames = new String[] { "Android", "iPhone",
		 * "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7",
		 * "Max OS X", "Linux", "OS/2" };
		 */
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, cohortNames);
		cohortsListView.setAdapter(adapter);
	}

}
