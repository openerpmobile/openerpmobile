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
		
		customerData.insert(new Customer(1, "Pawel Kobojek", "Zalesie 86a", "google.com", "69-43", "PawelKobojek@gmail.com"));
		customerData.insert(new Customer(2, "Maciej Dlus", "Ukraina 0", "google.ua", "NULL", "Maciek@napewnoniegmail.com"));
		customerData.insert(new Customer(3, "Pan Prezes", "Riviera", "hajs.pl", "1", "Prezes@Pan.com"));
	}

}
