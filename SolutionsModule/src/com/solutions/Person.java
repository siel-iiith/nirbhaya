package com.solutions;

class Person
{
	String name,designation,emailid,phone,perdeptname,location;
	public void setName(String names)
	{
		this.name = names;
	}
	public void setPerDeptName(String perdeptnames)
	{
		this.perdeptname = perdeptnames;
	}
	public void setDesignation(String desig)
	{
		this.designation = desig;
	}
	public void setPhone(String phonenum)
	{
		this.phone = phonenum;
	}
	public void setEmail(String email)
	{
		this.emailid = email;
	}
	public void setLocation(String location)
	{
		this.location = location;
	}
	public String getName()
	{
		return name;
	}
	public String getDesignation()
	{
		return designation;
	}
	public String getPhone()
	{
		return phone;
	}
	public String getEmail()
	{
		return emailid;
	}
	public String getLocation()
	{
		return location;
	}
	public String getDeptName()
	{
		return perdeptname;
	}
	
	
	public static void main(String[] args)
	{	
		Person p = new Person();
		p.setName("aditya");
		p.setDesignation("eng");
//		p.setDepartmentId(12121);
		p.setEmail("aditya@rt.com");
		p.setPhone("132131");
		System.out.println(p.getEmail());
	}
}
