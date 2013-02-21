package com.lacan.openerpmobile;

public class SuppliersInvoices
{
	private Customer customer;
	private String date;
	private String number;
	private long total;

	public SuppliersInvoices(Customer customer, String date, String number,
			long total)
	{
		super();
		this.customer = customer;
		this.date = date;
		this.number = number;
		this.total = total;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public long getTotal()
	{
		return total;
	}

	public void setTotal(long total)
	{
		this.total = total;
	}
}
