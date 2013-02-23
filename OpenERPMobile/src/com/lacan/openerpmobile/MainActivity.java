package com.lacan.openerpmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

//to bedzie glowne menu
public class MainActivity extends Activity
{
	public static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d(TAG, "onCreate");
	}

	@Override
	protected void onRestart()
	{
		super.onRestart();
		Log.d(TAG, "onRestart");
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		Log.d(TAG, "onResume");
	}

	public void onClickButtonSales(View view)
	{
		startActivity(new Intent(this, SalesActivity.class));
	}
	
	public void onClickButtonLogout(View view)
	{
		startActivity(new Intent(this, LoginActivity.class));
	}
	
	public void onClickButtonHome(View view)
	{
		
	}
}
