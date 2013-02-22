package com.lacan.openerpmobile;

public class Opportunity
{
	public static final int NEW = 1;
	public static final int QUALIFICATION = 2;
	public static final int POPOSITION = 4;
	public static final int NEGOTIATION = 8;
	public static final int WON = 16;
	public static final int LOST = 0;
	
	private long id;
	private String opportunity;
	private int customerId;
	private int stage;
	private double expectedRevenue;
	private double successRate;
	
	public Opportunity(long id, String opportunity, int customerId, int stage,
			double expectedRevenue, double successRate)
	{
		super();
		this.id = id;
		this.opportunity = opportunity;
		this.customerId = customerId;
		this.stage = stage;
		this.expectedRevenue = expectedRevenue;
		this.successRate = successRate;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getOpportunity()
	{
		return opportunity;
	}

	public void setOpportunity(String opportunity)
	{
		this.opportunity = opportunity;
	}

	public int getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(int customerId)
	{
		this.customerId = customerId;
	}

	public int getStage()
	{
		return stage;
	}

	public void setStage(int stage)
	{
		this.stage = stage;
	}

	public double getExpectedRevenue()
	{
		return expectedRevenue;
	}

	public void setExpectedRevenue(double expectedRevenue)
	{
		this.expectedRevenue = expectedRevenue;
	}

	public double getSuccessRate()
	{
		return successRate;
	}

	public void setSuccessRate(double successRate)
	{
		this.successRate = successRate;
	}

}
