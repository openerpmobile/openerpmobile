package com.lacan.openerpmobile;

public class Inventory
{
	private Product product;
	private double quantity;

	public Inventory(Product product, double quantity)
	{
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct()
	{
		return product;
	}

	public double getQuantity()
	{
		return quantity;
	}
}