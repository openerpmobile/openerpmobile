package com.lacan.openerpmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class CustomerDetailsActivity extends Activity
{
	TextView textViewPhone;
	String phone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		phone = intent.getStringExtra(CustomerData.C_PHONE);

		setContentView(R.layout.activity_customer_details);
	}
	

	@Override
	protected void onResume()
	{
		textViewPhone = (TextView) findViewById(R.id.phone_number);
		textViewPhone.setText(phone);
		super.onResume();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customer_details, menu);
		return true;
	}

}
