package com.lacan.openerpmobile;

public class Inventory
{
	private long id;
	private long productId;
	private double quantity;

	public Inventory(long id, long productId, double quantity)
	{
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public long getId()
	{
		return id;
	}

	public long getProductId()
	{
		return productId;
	}

	public double getQuantity()
	{
		return quantity;
	}
}