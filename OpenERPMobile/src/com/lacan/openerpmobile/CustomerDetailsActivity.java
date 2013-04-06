package com.lacan.openerpmobile;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class CustomerDetailsActivity extends Activity
{
	TextView textViewName;
	TextView textViewPhone;
	TextView textViewMail;
	TextView textViewWeb;
	TextView textViewAddress;

	String name;
	String phone;
	String email;
	String web;
	String address;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		// phone = intent.getStringExtra(CustomerData.C_PHONE);
		Long id = Long.parseLong(intent.getStringExtra(CustomerData.C_ID));

		Cursor cursor = ((OpenERPApp) getApplication()).customerData.query(id
				.toString());

		cursor.moveToFirst();
		name = cursor.getString(cursor.getColumnIndex(CustomerData.C_NAME));
		phone = cursor.getString(cursor.getColumnIndex(CustomerData.C_PHONE));
		email = cursor.getString(cursor.getColumnIndex(CustomerData.C_EMAIL));
		web = cursor.getString(cursor.getColumnIndex(CustomerData.C_WEBISTE));
		address = cursor.getString(cursor
				.getColumnIndex(CustomerData.C_ADDRESS));

		setContentView(R.layout.activity_customer_details);
	}

	@Override
	protected void onResume()
	{
		textViewName = (TextView) findViewById(R.id.textName);
		textViewName.setText(name);

		textViewPhone = (TextView) findViewById(R.id.textPhone);
		textViewPhone.setText(phone);

		textViewMail = (TextView) findViewById(R.id.textMail);
		textViewMail.setText(email);

		textViewWeb = (TextView) findViewById(R.id.textWeb);
		textViewWeb.setText(web);

		textViewAddress = (TextView) findViewById(R.id.textAdress);
		textViewAddress.setText(address);

		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customer_details, menu);
		return true;
	}

	public void onPhoneButtonClick(View view)
	{
		Intent intent = new Intent(Intent.ACTION_CALL);

		intent.setData(Uri.parse("tel:" + phone));
		startActivity(Intent.createChooser(intent, "Phone call..."));
		//startActivity(intent);
	}

	public void onMailButtonClick(View view)
	{
		final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

		/* Fill it with Data */
		emailIntent.setType("plain/html");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{this.email});
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(""));

		/* Send it off to the Activity-Chooser */
		startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	}

	public void onWebButtonClick(View view)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + this.web));
		startActivity(Intent.createChooser(intent, "Web browser..."));
	}

}
