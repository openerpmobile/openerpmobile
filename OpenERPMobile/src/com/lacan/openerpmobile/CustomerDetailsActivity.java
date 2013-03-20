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
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String phone = intent.getStringExtra(CustomerData.C_PHONE);

		setContentView(R.layout.activity_customer_details);
		
		textViewPhone = (TextView) findViewById(R.id.phone_number);
		textViewPhone.setText(phone);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customer_details, menu);
		return true;
	}

}
