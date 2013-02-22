package com.lacan.openerpmobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper
{
	public static final String DB_NAME = "example.db";
	public static final int DB_VERSION = 1;

	// TABLICA CUSTOMERÓW
	public static final String C_TABLE_NAME = "customers";
	public static final String COL_C_NAME = "name";
	public static final String COL_C_ADDRESS = "address";
	public static final String COL_C_WEBSITE = "website";
	public static final String COL_C_PHONE = "phone";
	public static final String COL_C_EMAIL = "email";
	// TABLE_CREATE to zapytanie SQL, kt髍e utworzy tablic�bazy danych...
	// zawiera ono nazwy i opcje poszczeg髄nych kolumn.
	private static final String C_TABLE_CREATE = "CREATE TABLE " + C_TABLE_NAME
			+ " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COL_C_NAME + " TEXT, " + COL_C_ADDRESS + " TEXT, "
			+ COL_C_WEBSITE + " TEXT, " + COL_C_PHONE + " TEXT, " + COL_C_EMAIL
			+ " TEXT);";

	// TABLICA FAKTUR
	public static final String CI_TABLE_NAME = "c_invoices";
	public static final String COL_CI_CUSTOMER = "customer";
	public static final String COL_CI_DATE = "date";
	public static final String COL_CI_NR = "number";
	public static final String COL_CI_TOTAL = "total";
	private static final String CI_TABLE_CREATE = "CREATE TABLE "
			+ CI_TABLE_NAME + " (" + BaseColumns._ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CI_CUSTOMER
			+ " INTEGER, " + COL_CI_DATE + " TEXT, " + COL_CI_NR + " TEXT, "
			+ COL_CI_TOTAL + " REAL);";

	// TABLICA OPPORTUNITIES
	public static final String O_TABLE_NAME = "opportunities";
	public static final String COL_O_OPPORTUNITY = "opportunity";
	public static final String COL_O_CUSTOMER = "customer";
	public static final String COL_O_STAGE = "stage";
	public static final String COL_O_EXPECTED_REVENUE = "expected_revenue";
	public static final String COL_O_SUCCESS_RATE = "success_rate";
	private static final String O_TABLE_CREATE = "CREATE TABLE " + O_TABLE_NAME
			+ " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COL_O_OPPORTUNITY + " TEXT, " + COL_O_CUSTOMER + " INTEGER, "
			+ COL_O_STAGE + " INTEGER, " + COL_O_EXPECTED_REVENUE + " REAL, "
			+ COL_O_SUCCESS_RATE + " REAL);";
	
	

	public MySQLiteHelper(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
	}

	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(C_TABLE_CREATE);
		db.execSQL(CI_TABLE_CREATE);
		db.execSQL(O_TABLE_CREATE);

	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(MySQLiteHelper.DB_NAME, "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " + C_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + CI_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + O_TABLE_NAME);

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

	public String[] getAllOpportunitiesCols()
	{
		String[] toReturn =
		{ BaseColumns._ID, MySQLiteHelper.COL_O_CUSTOMER,
				MySQLiteHelper.COL_O_OPPORTUNITY, MySQLiteHelper.COL_O_STAGE,
				MySQLiteHelper.COL_O_EXPECTED_REVENUE,
				MySQLiteHelper.COL_O_SUCCESS_RATE };
		return toReturn;
	}

	public String[] getAllOpportunitiesColsWithoutID()
	{
		String[] toReturn =
		{ MySQLiteHelper.COL_O_OPPORTUNITY, MySQLiteHelper.COL_O_STAGE,
				MySQLiteHelper.COL_O_EXPECTED_REVENUE,
				MySQLiteHelper.COL_O_SUCCESS_RATE };
		return toReturn;
	}
}
