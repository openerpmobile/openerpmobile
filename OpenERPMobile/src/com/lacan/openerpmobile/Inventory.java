package com.lacan.openerpmobile;

public class Inventory
{
	private long productId;
	private double quantity;

	public Inventory(long productId, double quantity)
	{
		super();
		this.productId = productId;
		this.quantity = quantity;
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