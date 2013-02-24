package com.lacan.openerpmobile;

public class Product
{
	private long id;
	private String name;
	private String internalReference;
	private double quantityOnHand;
	private double forecastedQuantity;
	private double price;

	public Product(long id, String name, String internalReference,
			double quantityOnHand, double forecastedQuantity, double price)
	{
		super();
		this.id = id;
		this.name = name;
		this.internalReference = internalReference;
		this.quantityOnHand = quantityOnHand;
		this.forecastedQuantity = forecastedQuantity;
		this.price = price;
	}

	public String getName()
	{
		return name;
	}

	public long getId()
	{
		return id;
	}

	public String getInternalReference()
	{
		return internalReference;
	}

	public double getQuantityOnHand()
	{
		return quantityOnHand;
	}

	public double getForecastedQuantity()
	{
		return forecastedQuantity;
	}

	public double getPrice()
	{
		return price;
	}
}