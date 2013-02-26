package com.lacan.openerpmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class CustomerInvoiceData
{
	public static final String TAG = "CustomerInvoiceData";

	public static final String DB_NAME = "customerInvoice.db";
	public static final int DB_VERSION = 1;

	public static final String TABLE = "customersInvoices";
	public static final String CI_ID = BaseColumns._ID;
	public static final String CI_CUSTOMER_ID = "customerId";
	public static final String CI_DATE = "date";
	public static final String CI_NUMBER = "number";
	public static final String CI_TOTAL = "total";

	Context context;
	DbHelper dbHelper;
	SQLiteDatabase db;

	public CustomerInvoiceData(Context context)
	{
		this.context = context;
		dbHelper = new DbHelper();
	}

	public void insert(CustomerInvoice customerInvoice)
	{
		db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CI_ID, customerInvoice.getId());
		values.put(CI_CUSTOMER_ID, customerInvoice.getCustomerId());
		values.put(CI_DATE, customerInvoice.getDate());
		values.put(CI_NUMBER, customerInvoice.getNumber());
		values.put(CI_TOTAL, customerInvoice.getTotal());

		db.insertWithOnConflict(TABLE, null, values,
				SQLiteDatabase.CONFLICT_IGNORE);
	}

	public Cursor query()
	{
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE, null, null, null, null, null, null);
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
					.format("create table %s (%s int primary key, %s integer, %s text, %s text, %s double)",
							TABLE, CI_ID, CI_CUSTOMER_ID, CI_DATE, CI_NUMBER,
							CI_TOTAL);

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
