package com.lacan.openerpmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnItemSelectedListener
{
	public static final String TAG = "MainActivity";

	Spinner dbSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Log.d(TAG, "onCreate");

		dbSpinner = (Spinner) findViewById(R.id.loginAct_dbspinner);

		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.loginAct_db_arr,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		dbSpinner.setAdapter(adapter);

		new EditTextPasswordController(
				(EditText) findViewById(R.id.loginAct_text_pswd));
		// kontroluje poprawne wyœwietlanie hinta w loginAct_text_pswd
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		dbSpinner.setOnItemSelectedListener(this);

		Log.d(TAG, "onResume");

	}

	@Override
	protected void onRestart()
	{
		super.onRestart();
		Log.d(TAG, "onRestart");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	// Method invoked when user selects an item from the menu
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.menu_settings: // user selected settings
				// TODO Launch settings activity
				return true;
			default:
				return false;
		}
	}

	@SuppressWarnings("unused")
	private static void loginAttempt() throws Exception // TODO napisac klase
														// wyjatkow logowania
	{
		// TODO check user id and password

		// wrong input:
		if (1 == 0)
			throw new Exception(); // na razie do testowania da³em zawsze
									// mozliwosc zalogowania sie

	}

	public void onClickButtonLogin(View view)
	{
		try
		{
			loginAttempt();
			startActivity(new Intent(this, MainActivity.class));

			finish();

		}
		catch (Exception e) // TODO potem zmienic Exception na LoginException
							// czy cos takiego
		{
			Log.e(TAG, "Wrong login or password!");
			e.printStackTrace();
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id)
	{
		// TODO To na razie tylko prezentacja tego co sie dzieje po wybraniu
		// czegos
		switch (pos)
		{
			case 0:
				Toast.makeText(this, "Selected pos 0", Toast.LENGTH_LONG)
						.show();
				return;
			case 1:
				Toast.makeText(this, "Selected pos 1", Toast.LENGTH_LONG)
						.show();
				return;
			case 2:
				Toast.makeText(this, "Selected pos 2", Toast.LENGTH_LONG)
						.show();
				return;
			default:
				Toast.makeText(this, "dunno", Toast.LENGTH_LONG).show();
				return;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent)
	{
		// TODO Auto-generated method stub

	}
}
