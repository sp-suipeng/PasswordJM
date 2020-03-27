package com.sp.passwordjm;
//Ãû³Æ£¬ÕËºÅ£¬ÃÜÂëÀà
public class nap {
	private String name;
	private String account;
	private String password;
	public void setter(String n,String a,String p)
	{
		this.name=n;
		this.account=a;
		this.password=p;
	}
	public nap(String n,String a,String p)
	{
		this.name=n;
		this.account=a;
		this.password=p;
	}
	public String getName()
	{
		return this.name;
	}
	public String getAccount()
	{
		return this.account;
	}
	public String getPassword()
	{
		return this.password;
	}
	

}
