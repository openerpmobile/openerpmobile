package com.lacan.openerpmobile;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import com.lacan.openerpmobile.R.layout;

public class CustomerListActivity extends ListActivity
{
	public static final String TAG = "CustomerListActivity";

	public static final String[] FROM =
	{ CustomerData.C_NAME, CustomerData.C_EMAIL };
	public static final int[] TO =
	{ R.id.list_text_name, R.id.list_text_email };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_list);
		// Show the Up button in the action bar.
		// getActionBar().setDisplayHomeAsUpEnabled(true);

		Cursor cursor = ((OpenERPApp) getApplication()).customerData.query();

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				layout.list_row, cursor, FROM, TO, 0);

		getListView().setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_customer_list, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.menu_home:
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			return true;
		case R.id.menu_logout:
			startActivity(new Intent(this, LoginActivity.class));
			return true;
		default:
			return false;
		}
	}

	public void onClickButtonAdd(View view)
	{
		//startActivity(new Intent(this, CustomerAddActivity.class));
	}

	public void onClickButtonHome(View view)
	{
		startActivity(new Intent(this, MainActivity.class));
	}
}
