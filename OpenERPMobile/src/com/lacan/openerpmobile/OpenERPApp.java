package com.lacan.openerpmobile;

import android.app.Application;
import android.util.Log;

public class OpenERPApp extends Application
{
	public static final String TAG = "OpenERPApp";

	CustomerData customerData;

	@Override
	public void onCreate()
	{
		super.onCreate();
		Log.d(TAG, "onCreate");

		customerData = new CustomerData(this);
		
		customerData.insert(new Customer(1,"a", "b","c","d","e"));
	}

}
