package com.lacan.openerpmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class ProductData
{
	public static final String TAG = "ProductData";
	
	public static final String DB_NAME = "product.db";
	public static final int DB_VERSION = 1;
	
	public static final String TABLE = "products";
	public static final String P_ID = BaseColumns._ID;
	public static final String P_NAME = "name";
	public static final String P_INTERNAL_REFERENCE = "internalReference";
	public static final String P_QUANTITY_ON_HAND = "quantityOnHand";
	public static final String P_FORECASTED_QUANTITY = "forecastedQuantity";
	public static final String P_PRICE = "price";
	
	Context context;
	DbHelper dbHelper;
	SQLiteDatabase db;
	
	public ProductData(Context context)
	{
		this.context=context;
		dbHelper = new DbHelper();
	}
	
	public void insert(Product product)
	{
		db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(P_ID, product.getId());
		values.put(P_NAME, product.getName());
		values.put(P_INTERNAL_REFERENCE, product.getInternalReference());
		values.put(P_QUANTITY_ON_HAND, product.getQuantityOnHand());
		values.put(P_FORECASTED_QUANTITY, product.getForecastedQuantity());
		values.put(P_PRICE, product.getPrice());

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
					.format("create table %s (%s int primary key, %s text, %s text, %s text, %s text, %s text)",
							TABLE, P_ID, P_NAME, P_INTERNAL_REFERENCE, P_QUANTITY_ON_HAND, P_FORECASTED_QUANTITY,
							P_PRICE);

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















