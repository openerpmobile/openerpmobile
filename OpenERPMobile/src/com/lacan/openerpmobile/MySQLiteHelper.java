package com.lacan.openerpmobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper
{
	private static final String DB_NAME = "erp.db";
	private static final int DB_VERSION = 1;

	private static final String C_TABLE_NAME = "customers";
	private static final String COL_C_NAME = "name";
	private static final String COL_C_ADDRESS = "address";
	private static final String COL_C_WEBSITE = "website";
	private static final String COL_C_PHONE = "phone";
	private static final String COL_C_EMAIL = "email";
	// TABLE_CREATE to zapytanie SQL, kt髍e utworzy tablic�bazy danych...
	// zawiera ono nazwy i opcje poszczeg髄nych kolumn.
	private static final String C_TABLE_CREATE = "CREATE TABLE " + C_TABLE_NAME
			+ " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COL_C_NAME + " TEXT, " + COL_C_ADDRESS + " TEXT, "
			+ COL_C_WEBSITE + " TEXT, " + COL_C_PHONE + " TEXT, " + COL_C_EMAIL
			+ " TEXT);";

	private static final String CI_TABLE_NAME = "c_invoices";
	private static final String COL_CI_CUSTOMER = "customer";
	private static final String COL_CI_DATE = "date";
	private static final String COL_CI_NR = "number";
	private static final String COL_CI_TOTAL = "total";
	private static final String CI_TABLE_CREATE = "CREATE TABLE "
			+ CI_TABLE_NAME + " (" + BaseColumns._ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CI_CUSTOMER
			+ " INTEGER, " + COL_CI_DATE + " TEXT, " + COL_CI_NR + " TEXT, "
			+ COL_CI_TOTAL + " REAL);";

	public static String getDbName()
	{
		return DB_NAME;
	}

	public static String getCTableName()
	{
		return C_TABLE_NAME;
	}

	public static String getCiTableName()
	{
		return CI_TABLE_NAME;
	}

	public static String getColCName()
	{
		return COL_C_NAME;
	}

	public static String getColCAddress()
	{
		return COL_C_ADDRESS;
	}

	public static String getColCWebsite()
	{
		return COL_C_WEBSITE;
	}

	public static String getColCPhone()
	{
		return COL_C_PHONE;
	}

	public static String getColCEmail()
	{
		return COL_C_EMAIL;
	}

	public static String getColCiCustomer()
	{
		return COL_CI_CUSTOMER;
	}

	public static String getColCiDate()
	{
		return COL_CI_DATE;
	}

	public static String getColCiNr()
	{
		return COL_CI_NR;
	}

	public static String getColCiTotal()
	{
		return COL_CI_TOTAL;
	}

	public MySQLiteHelper(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
	}

	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(C_TABLE_CREATE);
		db.execSQL(CI_TABLE_CREATE);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(getDbName(), "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " + C_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + CI_TABLE_NAME);

		onCreate(db);
	}

	public String[] getAllCustomersCols()
	{
		String[] toReturn =
		{ BaseColumns._ID, MySQLiteHelper.COL_C_ADDRESS,
				MySQLiteHelper.COL_C_WEBSITE, MySQLiteHelper.COL_C_PHONE,
				MySQLiteHelper.COL_C_EMAIL };
		return toReturn;
	}

	public String[] getCustomersColsWithoutID()
	{
		String[] toReturn =
		{ MySQLiteHelper.COL_C_ADDRESS, MySQLiteHelper.COL_C_WEBSITE,
				MySQLiteHelper.COL_C_PHONE, MySQLiteHelper.COL_C_EMAIL };
		return toReturn;
	}

	public String[] getAllCustomersInvoicesCols()
	{
		String[] toReturn =
		{ BaseColumns._ID, MySQLiteHelper.COL_CI_CUSTOMER,
				MySQLiteHelper.COL_CI_DATE, MySQLiteHelper.COL_CI_NR,
				MySQLiteHelper.COL_CI_TOTAL };
		return toReturn;
	}

	public String[] getAllCustomersInvoicesColsWithoutID()
	{
		String[] toReturn =
		{ MySQLiteHelper.COL_CI_CUSTOMER, MySQLiteHelper.COL_CI_DATE,
				MySQLiteHelper.COL_CI_NR, MySQLiteHelper.COL_CI_TOTAL };
		return toReturn;
	}
}
