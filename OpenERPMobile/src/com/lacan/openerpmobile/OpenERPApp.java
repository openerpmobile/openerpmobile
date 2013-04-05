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
		
		customerData.insert(new Customer(1, "Jak Kowalski", "Ghi 1", "google.com", "123456789", "abc@gmail.com"));
		customerData.insert(new Customer(2, "Test Test", "Test 0", "google.ua", "987654321", "test@gmail.com"));
		customerData.insert(new Customer(3, "Pan Prezes", "Riviera", "hajs.pl", "1", "Prezes@Pan.com"));
		customerData.insert(new Customer(4, "testa", "Warszawa", "dsa", "111222333", "sda"));
		customerData.insert(new Customer(5, "testb", "Warszawa", "dsa", "111222333", "sda"));
		customerData.insert(new Customer(6, "testc", "Warszawa", "dsa", "111222333", "sda"));
		customerData.insert(new Customer(7, "testd", "Warszawa", "dsa", "111222333", "sda"));
		customerData.insert(new Customer(8, "teste", "Warszawa", "dsa", "111222333", "sda"));
		customerData.insert(new Customer(9, "testf", "Warszawa", "dsa", "111222333", "sda"));
		customerData.insert(new Customer(10, "testg", "Warszawa", "dsa", "111222333", "sda"));
		customerData.insert(new Customer(11, "testh", "Warszawa", "dsa", "111222333", "sda"));
		customerData.insert(new Customer(12, "testi", "Warszawa", "dsa", "111222333", "sda"));
		customerData.insert(new Customer(13, "testj", "Warszawa", "dsa", "111222333", "sda"));
	}

}
