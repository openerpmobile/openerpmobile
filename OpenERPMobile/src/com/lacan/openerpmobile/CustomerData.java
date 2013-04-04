package com.lacan.openerpmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class CustomerData
{
	public static final String TAG = "CustomerData";

	public static final String DB_NAME = "customer.db";
	public static final int DB_VERSION = 1;

	public static final String TABLE = "customers";
	public static final String C_ID = BaseColumns._ID;
	public static final String C_NAME = "name";
	public static final String C_ADDRESS = "address";
	public static final String C_EMAIL = "email";
	public static final String C_PHONE = "phone";
	public static final String C_WEBISTE = "website";

	Context context;
	DbHelper dbHelper;
	SQLiteDatabase db;

	public CustomerData(Context context)
	{
		this.context = context;
		dbHelper = new DbHelper();
	}

	public void insert(Customer customer)
	{
		db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(C_ID, customer.getId());
		values.put(C_NAME, customer.getName());
		values.put(C_ADDRESS, customer.getAddress());
		values.put(C_EMAIL, customer.getEmail());
		values.put(C_PHONE, customer.getPhone());
		values.put(C_WEBISTE, customer.getWebsite());

		db.insertWithOnConflict(TABLE, null, values,
				SQLiteDatabase.CONFLICT_IGNORE);
	}

	public Cursor query()
	{
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE, null, null, null, null, null, null);
		return cursor;
	}

	public Cursor query(String selectionId)
	{
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE, null, C_ID + " = ?", new String[] { selectionId },
				null, null, null);
		
		return cursor;
	}

	class DbHelper extends SQLiteOpenHelper
	{

		public DbHelper()
		{
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			String sql = String
					.format("create table %s (%s int primary key, %s text, %s text, %s text, %s text, %s text)",
							TABLE, C_ID, C_NAME, C_ADDRESS, C_EMAIL, C_PHONE,
							C_WEBISTE);

			Log.d(TAG, "onCreate");
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			db.execSQL("drop if exists " + TABLE);
			onCreate(db);
		}

	}
}
