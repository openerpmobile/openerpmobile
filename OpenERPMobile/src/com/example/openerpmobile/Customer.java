package com.example.openerpmobile;

public class Customer
{
	private long id;
	private String name;
	private String address;
	private String website;
	private String phone;
	private String email;
	
	public Customer (long _id, String _name, String _address, String _website, String _phone, String _email)
	{
		this.setId(_id);
		this.setName(_name);
		this.setAddress(_address);
		this.setWebsite(_website);
		this.setPhone(_phone);
		this.setEmail(_email);
	}
	
	//Jeœli w Javie jest coœ takiego jak w³aœciwoœæ, to mam nadziejê, ¿e...
	//poni¿szym kawa³kiem kodu nie straci³em zbyt wiele w waszych oczach.
	//Tak czy inaczej myœlê, ¿e to mo¿e zostaæ;
	//jak kogoœ to bardzo razi i ma za du¿o wolnego czasu, to mo¿e dorobiæ w³aœciwoœci..:>
	//MB
	
	public void setId (long _id)
	{
		this.id = _id;
	}
	
	public long getId ()
	{
		return this.id;
	}
	
	public void setName (String _name)
	{
		this.name = _name;
	}
	
	public String getName ()
	{
		return this.name;
	}
	
	public void setAddress (String _address)
	{
		this.address = _address;
	}
	
	public String getAddress ()
	{
		return this.address;
	}
	
	public void setWebsite (String _website)
	{
		this.website = _website;
	}
	
	public String getWebsite ()
	{
		return this.website;
	}
	
	public void setPhone (String _phone)
	{
		this.phone = _phone;
	}
	
	public String getPhone ()
	{
		return this.phone;
	}
	
	public void setEmail (String _email)
	{
		this.email = _email;
	}
	
	public String getEmail ()
	{
		return this.email;
	}
}
