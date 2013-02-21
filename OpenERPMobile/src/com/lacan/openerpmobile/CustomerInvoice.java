package com.lacan.openerpmobile;

public class CustomerInvoice
{
	private long id;
	private int customerId;
	private String date;
	private String number;
	private double total;

	public CustomerInvoice(long id, int customer, String date, String number,
			double total)
	{
		super();
		this.setId(id);
		this.customerId = customer;
		this.date = date;
		this.number = number;
		this.total = total;
	}

	public int getCustomerId()
	{
		return customerId;
	}

	public void setCustomer(int customerId)
	{
		this.customerId = customerId;
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

	public double getTotal()
	{
		return total;
	}

	public void setTotal(long total)
	{
		this.total = total;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
}
