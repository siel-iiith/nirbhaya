package com.solutions;

class Department
{
	String dept_name, Address, url, location;
	long departmentid;
	public enum department {CENTRAL, STATE, NGO};
	department dept_type;
	public void setDeptName(String names)
	{
		this.dept_name = names;
	}
	public void setDepartmentId(long deptid)
	{
		this.departmentid = deptid;
	}
	public void setAddress(String addr)
	{
		this.Address = addr;
	}
	public void setLocation(String loc)
	{
		this.location = loc;
	}
	public void setDeptURL(String url)
	{
		this.url = url;
	}
	public void setDepartmentType(department dept)
	{
		this.dept_type = dept;
	}
	public String getDeptName()
	{
		return dept_name;
	}
	public long getDepartmentId()
	{
		return departmentid;
	}
	public String getAddress()
	{
		return Address;
	}
	public String getLocation()
	{
		return location;
	}
	public String getDeptURL()
	{
		return url;
	}
	public department getDepartmentType()
	{
		return dept_type;
	}
		
	public static void main(String[] args)
	{
		Department D = new Department();
		D.setDeptName("electrical");
		System.out.println(D.getDeptName());
	}
}
