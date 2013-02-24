package com.lacan.openerpmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class OpportunityData
{
	public static final String TAG = "OpportunityData";

	public static final String DB_NAME = "opportunity.db";
	public static final int DB_VERSION = 1;

	public static final String TABLE = "opportunities";
	public static final String O_ID = BaseColumns._ID;
	public static final String O_OPPORTUNITY = "opportunity";
	public static final String O_CUSTOMER_ID = "customerId";
	public static final String O_STAGE = "stage";
	public static final String O_EXPECTED_REVENUE = "expectedRevenue";
	public static final String O_SUCCESS_RATE = "successRate";

	Context context;
	DbHelper dbHelper;
	SQLiteDatabase db;

	public OpportunityData(Context context)
	{
		this.context = context;
		dbHelper = new DbHelper();
	}

	public void insert(Opportunity opportunity)
	{
		db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(O_OPPORTUNITY, opportunity.getOpportunity());
		values.put(O_CUSTOMER_ID, opportunity.getCustomerId());
		values.put(O_STAGE, opportunity.getStage());
		values.put(O_EXPECTED_REVENUE, opportunity.getExpectedRevenue());
		values.put(O_SUCCESS_RATE, opportunity.getSuccessRate());

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
					.format("create table %s (%s int primary key, %s text, %s integer, %s integer, %s real, %s real)",
							TABLE, O_ID, O_OPPORTUNITY, O_CUSTOMER_ID, O_STAGE,
							O_EXPECTED_REVENUE, O_SUCCESS_RATE);

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
