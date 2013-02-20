package com.example.openerpmobile;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBManagement {
	private SQLiteDatabase db;
	private MySQLiteHelper dbHelper;

	public DBManagement(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void addCustomer(Customer customer) {
		// towrzymy zmienn¹ typu ContentValues
		ContentValues values = new ContentValues();
		// i wprowadzamy to niej dane, podaj¹c jako klucz nazwê kolumny a drugi
		// argument to wartoœæ
		values.put(dbHelper.getColName(), customer.getName());
		values.put(dbHelper.getColAddress(), customer.getAddress());
		values.put(dbHelper.getColWebsite(), customer.getWebsite());
		values.put(dbHelper.getColPhone(), customer.getPhone());
		values.put(dbHelper.getColEmail(), customer.getEmail());
		// i mo¿emy teraz prost¹ metod¹ insert prowadziæ dane...
		db.insert(dbHelper.getCustomersTableName(), null, values);
	}

	public void addCustomerInvoice(CustomerInvoice c_invoice) {
		// towrzymy zmienn¹ typu ContentValues
		ContentValues values = new ContentValues();
		// i wprowadzamy to niej dane, podaj¹c jako klucz nazwê kolumny a drugi
		// argument to wartoœæ
		//values.put(dbHelper.getColCustomer(), c_invoice.getCustomer());
		// murzynienie wszystkich kolumn

		// i mo¿emy teraz prost¹ metod¹ insert prowadziæ dane...
		db.insert(dbHelper.getCustomersTableName(), null, values);
	}

	private Customer cursorToCustomer(Cursor cursor) {
		// wykorzystamy konstruktor z klasy Customer
		// wartoœæ z danej kolumny w wierszu wskazywanym przez kursor
		// pozyskujemy metod¹ get<typ>(int index_kolumny)
		// przy czym numeracja kolumn jest od 0
		Customer customer = new Customer(cursor.getLong(0),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5));

		return customer;
	}

	public ArrayList<Customer> fetchData() {
		// tworzymy listê która zostanie zwrócona
		ArrayList<Customer> customers = new ArrayList<Customer>();
		// ustawiamy kursor na pozycjê -1, czyli przed pierwszym wierszem
		// (numeracja od 0)
		Cursor cursor = db.query(dbHelper.getCustomersTableName(),
				dbHelper.getAllCols(), null, null, null, null, null);

		// dopóki nie wyjdziemy poza tablicê
		while (cursor.moveToNext())
			// zamieñ wiersz wskazywany przez cursor na Customer i dodaj go do
			// listy
			customers.add(cursorToCustomer(cursor));

		// zamknij cursor
		cursor.close();

		// zwróæ listê
		return customers;
	}

	public void removeCustomer(String name) {
		long idToRemove = 0;
		// analogicznie
		Cursor cursor = db.query(dbHelper.getCustomersTableName(),
				dbHelper.getAllCustomersCols(), null, null, null, null, null);

		// przesuwaj kursor dalej dopóki nie wyjdziesz za tablicê
		// lub nie natrafisz na profil o zadanym name
		while (cursor.moveToNext()
				&& 0 != cursorToCustomer(cursor).getName().compareTo(name)) {
			;
		}

		// jeœli znalaz³eœ taki profil
		if (!cursor.isAfterLast()) {
			idToRemove = cursorToCustomer(cursor).getId();
			// to usuñ wiersz o odpowiednim id
			db.delete(dbHelper.getCustomersTableName(), BaseColumns._ID + " = "
					+ idToRemove, null);
		}
	}

	public long rowsCount() {
		// jak poprzednio ustaw kursor przez pierwszym wierszem
		Cursor cursor = db.query(dbHelper.getCustomersTableName(),
				dbHelper.getCustomersColsWithoutID(), null, null, null, null,
				null); // ^mówi³em, ¿e siê przyda:D
		// to bêdze zwracany licznik
		long i;

		// dopóki jest nastêpny wiersz to ustaw nie niego kursor i zinkrementuj
		// licznik (takie ma³e a cieszy:)
		for (i = 0; cursor.moveToNext(); i++) {
			;
		}

		// zamknij kursor
		cursor.close();

		// zwróæ licznik
		return i;
	}
}
