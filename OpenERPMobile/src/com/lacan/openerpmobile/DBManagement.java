package com.lacan.openerpmobile;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBManagement
{
	private SQLiteDatabase db;
	private MySQLiteHelper dbHelper;

	public DBManagement(Context context)
	{
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException
	{
		db = dbHelper.getWritableDatabase();
	}

	public void close()
	{
		dbHelper.close();
	}

	public void addCustomer(Customer customer)
	{
		// towrzymy zmienną typu ContentValues
		ContentValues values = new ContentValues();
		// i wprowadzamy to niej dane, podając jako klucz nazwę kolumny a drugi
		// argument to wartość
		values.put(MySQLiteHelper.getColCName(), customer.getName());
		values.put(MySQLiteHelper.getColCAddress(), customer.getAddress());
		values.put(MySQLiteHelper.getColCWebsite(), customer.getWebsite());
		values.put(MySQLiteHelper.getColCPhone(), customer.getPhone());
		values.put(MySQLiteHelper.getColCEmail(), customer.getEmail());
		// i możemy teraz prostą metodą insert wprowadzić dane...
		db.insert(MySQLiteHelper.getCTableName(), null, values);
	}

	public void addCustomerInvoice(CustomerInvoice customerInvoice)
	{
		ContentValues values = new ContentValues();

		values.put(MySQLiteHelper.getColCiCustomer(),
				customerInvoice.getCustomerId());
		values.put(MySQLiteHelper.getColCiDate(), customerInvoice.getDate());
		values.put(MySQLiteHelper.getColCiNr(), customerInvoice.getNumber());
		values.put(MySQLiteHelper.getColCiTotal(), customerInvoice.getTotal());

		db.insert(MySQLiteHelper.getCTableName(), null, values);
	}

	private Customer cursorToCustomer(Cursor cursor)
	{
		Customer customer = new Customer(cursor.getLong(0),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5));

		return customer;
	}

	private CustomerInvoice cursorToCustomerInvoice(Cursor cursor)
	{
		CustomerInvoice customerInvoice = new CustomerInvoice(
				cursor.getLong(0), cursor.getInt(1), cursor.getString(2),
				cursor.getString(3), cursor.getDouble(4));

		return customerInvoice;
	}

	public ArrayList<Customer> fetchCustomersData()
	{
		ArrayList<Customer> customers = new ArrayList<Customer>();

		Cursor cursor = db.query(MySQLiteHelper.getCTableName(),
				dbHelper.getAllCustomersCols(), null, null, null, null, null);

		while (cursor.moveToNext())
			customers.add(cursorToCustomer(cursor));

		cursor.close();

		return customers;
	}

	public ArrayList<CustomerInvoice> fetchCustomersInvoicesData()
	{
		ArrayList<CustomerInvoice> customersInvoices = new ArrayList<CustomerInvoice>();

		Cursor cursor = db.query(MySQLiteHelper.getCiTableName(),
				dbHelper.getAllCustomersInvoicesCols(), null, null, null, null,
				null);

		while (cursor.moveToNext())
			customersInvoices.add(cursorToCustomerInvoice(cursor));

		cursor.close();

		return customersInvoices;
	}

	public void removeCustomer(String name)
	{
		long idToRemove = 0;

		Cursor cursor = db.query(MySQLiteHelper.getCTableName(),
				dbHelper.getAllCustomersCols(), null, null, null, null, null);

		while (cursor.moveToNext()
				&& 0 != cursorToCustomer(cursor).getName().compareTo(name))
		{
			;
		}

		if (!cursor.isAfterLast())
		{
			idToRemove = cursorToCustomer(cursor).getId();

			db.delete(MySQLiteHelper.getCTableName(), BaseColumns._ID + " = "
					+ idToRemove, null);
		}
	}

	public void removeCustomerInvoice(String number)
	{
		long idToRemove = 0;

		Cursor cursor = db.query(MySQLiteHelper.getCiTableName(),
				dbHelper.getAllCustomersInvoicesCols(), null, null, null, null,
				null);

		while (cursor.moveToNext()
				&& 0 != cursorToCustomerInvoice(cursor).getNumber().compareTo(
						number))
		{
			;
		}

		if (!cursor.isAfterLast())
		{
			idToRemove = cursorToCustomerInvoice(cursor).getId();

			db.delete(MySQLiteHelper.getCiTableName(), BaseColumns._ID + " = "
					+ idToRemove, null);
		}
	}

	public long customersTableRowsCount()
	{
		Cursor cursor = db.query(MySQLiteHelper.getCTableName(),
				dbHelper.getCustomersColsWithoutID(), null, null, null, null,
				null);
		
		long i;

		for (i = 0; cursor.moveToNext(); i++)
		{
			;
		}

		cursor.close();

		return i;
	}
	
	public long customersInvoicesTableRowsCount()
	{
		Cursor cursor = db.query(MySQLiteHelper.getCiTableName(),
				dbHelper.getCustomersColsWithoutID(), null, null, null, null,
				null);
		
		long i;

		for (i = 0; cursor.moveToNext(); i++)
		{
			;
		}

		cursor.close();

		return i;
	}
}
