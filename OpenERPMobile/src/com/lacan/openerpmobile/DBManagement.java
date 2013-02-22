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
		values.put(MySQLiteHelper.COL_C_NAME, customer.getName());
		values.put(MySQLiteHelper.COL_C_ADDRESS, customer.getAddress());
		values.put(MySQLiteHelper.COL_C_WEBSITE, customer.getWebsite());
		values.put(MySQLiteHelper.COL_C_PHONE, customer.getPhone());
		values.put(MySQLiteHelper.COL_C_EMAIL, customer.getEmail());
		// i możemy teraz prostą metodą insert wprowadzić dane...
		db.insert(MySQLiteHelper.C_TABLE_NAME, null, values);
	}

	public void addCustomerInvoice(CustomerInvoice customerInvoice)
	{
		ContentValues values = new ContentValues();

		values.put(MySQLiteHelper.COL_CI_CUSTOMER,
				customerInvoice.getCustomerId());
		values.put(MySQLiteHelper.COL_CI_DATE, customerInvoice.getDate());
		values.put(MySQLiteHelper.COL_CI_NR, customerInvoice.getNumber());
		values.put(MySQLiteHelper.COL_CI_TOTAL, customerInvoice.getTotal());

		db.insert(MySQLiteHelper.CI_TABLE_NAME, null, values);
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

		Cursor cursor = db.query(MySQLiteHelper.C_TABLE_NAME,
				dbHelper.getAllCustomersCols(), null, null, null, null, null);

		while (cursor.moveToNext())
			customers.add(cursorToCustomer(cursor));

		cursor.close();

		return customers;
	}

	public ArrayList<CustomerInvoice> fetchCustomersInvoicesData()
	{
		ArrayList<CustomerInvoice> customersInvoices = new ArrayList<CustomerInvoice>();

		Cursor cursor = db.query(MySQLiteHelper.CI_TABLE_NAME,
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

		Cursor cursor = db.query(MySQLiteHelper.C_TABLE_NAME,
				dbHelper.getAllCustomersCols(), null, null, null, null, null);

		while (cursor.moveToNext()
				&& 0 != cursorToCustomer(cursor).getName().compareTo(name))
		{
			;
		}

		if (!cursor.isAfterLast())
		{
			idToRemove = cursorToCustomer(cursor).getId();

			db.delete(MySQLiteHelper.C_TABLE_NAME, BaseColumns._ID + " = "
					+ idToRemove, null);
		}
	}

	public void removeCustomerInvoice(String number)
	{
		long idToRemove = 0;

		Cursor cursor = db.query(MySQLiteHelper.CI_TABLE_NAME,
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

			db.delete(MySQLiteHelper.CI_TABLE_NAME, BaseColumns._ID + " = "
					+ idToRemove, null);
		}
	}

	public long customersTableRowsCount()
	{
		Cursor cursor = db.query(MySQLiteHelper.C_TABLE_NAME,
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
		Cursor cursor = db.query(MySQLiteHelper.CI_TABLE_NAME,
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
