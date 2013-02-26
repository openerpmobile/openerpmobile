package com.lacan.openerpmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class InventoryData
{
	public static final String TAG = "InventoryData";
	
	public static final String DB_NAME = "inventory.db";
	public static final int DB_VERSION = 1;
	
	public static final String TABLE = "inventories";
	public static final String I_ID = BaseColumns._ID;
	public static final String I_PRODUCT_ID = "productId";
	public static final String I_QUANTITY = "quantity";
	
	Context context;
	DbHelper dbHelper;
	SQLiteDatabase db;
	
	public InventoryData(Context context)
	{
		this.context=context;
		dbHelper = new DbHelper();
	}
	
	public void insert(Inventory inventory)
	{
		db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(I_ID, inventory.getId());
		values.put(I_PRODUCT_ID, inventory.getProductId());
		values.put(I_QUANTITY, inventory.getQuantity());

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
					.format("create table %s (%s int primary key, %s int, %s double)",
							TABLE, I_ID, I_PRODUCT_ID, I_QUANTITY);

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
