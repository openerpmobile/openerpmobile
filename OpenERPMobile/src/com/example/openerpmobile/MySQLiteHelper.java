package com.example.openerpmobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper
{
	private static final String DB_NAME = "customers.db";
	private static final int DB_VERSION = 1;
	private static final String TABLE_NAME = "customers";
	private static final String COL_C_NAME = "name";
	private static final String COL_C_ADDRESS = "address";
	private static final String COL_C_WEBSITE = "website";
	private static final String COL_C_PHONE = "phone";
	private static final String COL_C_EMAIL = "email";
        //TABLE_CREATE to zapytanie SQL, kt�re utworzy tablic� bazy danych...
        //zawiera ono nazwy i opcje poszczeg�lnych kolumn.
	private static final String TABLE_CREATE =	"CREATE TABLE " + TABLE_NAME + " (" +
							BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							COL_C_NAME + " TEXT, " +
							COL_C_ADDRESS + " TEXT, " +
							COL_C_WEBSITE + " TEXT, " +
							COL_C_PHONE + " TEXT, " +
							COL_C_EMAIL + " TEXT);";
	
	public String getColName () { return MySQLiteHelper.COL_C_NAME; }
	public String getColAddress () { return MySQLiteHelper.COL_C_ADDRESS; }
	public String getColWebsite () { return MySQLiteHelper.COL_C_WEBSITE; }
	public String getColPhone () { return MySQLiteHelper.COL_C_PHONE; }
	public String getColEmail () { return MySQLiteHelper.COL_C_EMAIL; }
	
	//konstruktor klasy MySQLiteHelper
	public MySQLiteHelper (Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
	}

	//metoda onCreate tworz�ca tablic� bazy, wcze�niej utworzonym zapytaniem SQL
	public void onCreate (SQLiteDatabase db)
	{
		db.execSQL(TABLE_CREATE);
	}

	//virtualna metoda klasy SQLiteOpenHelper, kt�r� trzeba zaimplementowa�...
	//mo�e ona nic nie robi�, ale jak ju� jest, to nast�puj�ce rozwi�zanie jest eleganckie
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(getDatabaseName(), 
				"Upgrading database from version " + oldVersion + " to "
	                        + newVersion + ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

		onCreate(db);
	} 
	
	//tablica string�w z nazwami wszystkich kolumn
	public String[] getAllCols ()
	{
		String[] toReturn = { BaseColumns._ID, MySQLiteHelper.COL_C_ADDRESS, MySQLiteHelper.COL_C_WEBSITE,
				MySQLiteHelper.COL_C_PHONE, MySQLiteHelper.COL_C_EMAIL };
		return toReturn;
	}

	//tablica string�w z nazwami wszystkich kolumn, opr�cz _id (przyda si� [mo�e])
	public String[] getColsWithoutID ()
	{
		String[] toReturn = { MySQLiteHelper.COL_C_ADDRESS, MySQLiteHelper.COL_C_WEBSITE,
				MySQLiteHelper.COL_C_PHONE, MySQLiteHelper.COL_C_EMAIL };
		return toReturn;
	}

	//string z nazw� bazy danych (nie myli� z nazw� tablicy bazy)
	public String getDatabaseName ()
	{
		return MySQLiteHelper.DB_NAME;
	}

	//string z nazw� tablicy (nie myli� z nazw� bazy)
	public String getTableName ()
	{
		return MySQLiteHelper.TABLE_NAME;
	}
}
