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
		// towrzymy zmienn� typu ContentValues
		ContentValues values = new ContentValues();
		// i wprowadzamy to niej dane, podaj�c jako klucz nazw� kolumny a drugi
		// argument to warto��
		values.put(dbHelper.getColName(), customer.getName());
		values.put(dbHelper.getColAddress(), customer.getAddress());
		values.put(dbHelper.getColWebsite(), customer.getWebsite());
		values.put(dbHelper.getColPhone(), customer.getPhone());
		values.put(dbHelper.getColEmail(), customer.getEmail());
		// i mo�emy teraz prost� metod� insert prowadzi� dane...
		db.insert(dbHelper.getCustomersTableName(), null, values);
	}

	public void addCustomerInvoice(CustomerInvoice c_invoice) {
		// towrzymy zmienn� typu ContentValues
		ContentValues values = new ContentValues();
		// i wprowadzamy to niej dane, podaj�c jako klucz nazw� kolumny a drugi
		// argument to warto��
		//values.put(dbHelper.getColCustomer(), c_invoice.getCustomer());
		// murzynienie wszystkich kolumn

		// i mo�emy teraz prost� metod� insert prowadzi� dane...
		db.insert(dbHelper.getCustomersTableName(), null, values);
	}

	private Customer cursorToCustomer(Cursor cursor) {
		// wykorzystamy konstruktor z klasy Customer
		// warto�� z danej kolumny w wierszu wskazywanym przez kursor
		// pozyskujemy metod� get<typ>(int index_kolumny)
		// przy czym numeracja kolumn jest od 0
		Customer customer = new Customer(cursor.getLong(0),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5));

		return customer;
	}

	public ArrayList<Customer> fetchData() {
		// tworzymy list� kt�ra zostanie zwr�cona
		ArrayList<Customer> customers = new ArrayList<Customer>();
		// ustawiamy kursor na pozycj� -1, czyli przed pierwszym wierszem
		// (numeracja od 0)
		Cursor cursor = db.query(dbHelper.getCustomersTableName(),
				dbHelper.getAllCols(), null, null, null, null, null);

		// dop�ki nie wyjdziemy poza tablic�
		while (cursor.moveToNext())
			// zamie� wiersz wskazywany przez cursor na Customer i dodaj go do
			// listy
			customers.add(cursorToCustomer(cursor));

		// zamknij cursor
		cursor.close();

		// zwr�� list�
		return customers;
	}

	public void removeCustomer(String name) {
		long idToRemove = 0;
		// analogicznie
		Cursor cursor = db.query(dbHelper.getCustomersTableName(),
				dbHelper.getAllCustomersCols(), null, null, null, null, null);

		// przesuwaj kursor dalej dop�ki nie wyjdziesz za tablic�
		// lub nie natrafisz na profil o zadanym name
		while (cursor.moveToNext()
				&& 0 != cursorToCustomer(cursor).getName().compareTo(name)) {
			;
		}

		// je�li znalaz�e� taki profil
		if (!cursor.isAfterLast()) {
			idToRemove = cursorToCustomer(cursor).getId();
			// to usu� wiersz o odpowiednim id
			db.delete(dbHelper.getCustomersTableName(), BaseColumns._ID + " = "
					+ idToRemove, null);
		}
	}

	public long rowsCount() {
		// jak poprzednio ustaw kursor przez pierwszym wierszem
		Cursor cursor = db.query(dbHelper.getCustomersTableName(),
				dbHelper.getCustomersColsWithoutID(), null, null, null, null,
				null); // ^m�wi�em, �e si� przyda:D
		// to b�dze zwracany licznik
		long i;

		// dop�ki jest nast�pny wiersz to ustaw nie niego kursor i zinkrementuj
		// licznik (takie ma�e a cieszy:)
		for (i = 0; cursor.moveToNext(); i++) {
			;
		}

		// zamknij kursor
		cursor.close();

		// zwr�� licznik
		return i;
	}
}
