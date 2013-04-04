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
		
		customerData.insert(new Customer(1, "Abc Def", "Ghi 1", "google.com", "123456789", "abc@gmail.com"));
		customerData.insert(new Customer(2, "Test Test", "Test 0", "google.ua", "987654321", "test@gmail.com"));
		customerData.insert(new Customer(3, "Pan Prezes", "Riviera", "hajs.pl", "1", "Prezes@Pan.com"));
		customerData.insert(new Customer(4, "testa", "Warszawa", "", "111222333", ""));
		customerData.insert(new Customer(5, "testb", "Warszawa", "", "111222333", ""));
		customerData.insert(new Customer(6, "testc", "Warszawa", "", "111222333", ""));
		customerData.insert(new Customer(7, "testd", "Warszawa", "", "111222333", ""));
		customerData.insert(new Customer(8, "teste", "Warszawa", "", "111222333", ""));
		customerData.insert(new Customer(9, "testf", "Warszawa", "", "111222333", ""));
		customerData.insert(new Customer(10, "testg", "Warszawa", "", "111222333", ""));
		customerData.insert(new Customer(11, "testh", "Warszawa", "", "111222333", ""));
		customerData.insert(new Customer(12, "testi", "Warszawa", "", "111222333", ""));
		customerData.insert(new Customer(13, "testj", "Warszawa", "", "111222333", ""));
	}

}
