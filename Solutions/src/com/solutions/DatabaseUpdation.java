package com.solutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.solutions.Department.department;

public class DatabaseUpdation {
	
	public static class PersonConstruct {
		Person person = new Person();
		String name = person.getName();
		String designation = person.getDesignation();
		String phone = person.getPhone();
		String emailId = person.getEmail();
		long departmentId = person.getDepartmentId();
//		Person person = new Person();
//		String name = "Harshit";
//		String designation = "CEO";
//		String phone = "9000405902";
//		String emailId = "xyz@123";
//		long departmentId = 123546;
	}
	
	public static class DepartmentConstruct {
		Department department = new Department();
		String dept_name = department.getDeptName();
		long departmentid = department.getDepartmentId();
		String address = department.getAddress();
		String location = department.getLocation();
		String RTI_Info = department.getRTIInfo();
		department dept_type = department.getDepartmentType();
	}

	public static void outputJSON (String className) {
		Gson gson =new Gson();
		String json = null;
		if (className.equals("Person")) {
			PersonConstruct person = new PersonConstruct();
			json = gson.toJson(person);	
		}
		else if (className.equals("department")) {
			PersonConstruct department = new PersonConstruct();
			json = gson.toJson(department);	
		}
		try {
			//write converted json data to a file named "file.json"
			BufferedWriter bw = new BufferedWriter (new FileWriter(new File("file.json")));
			bw.write(json);
			bw.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		System.out.println(json);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		outputJSON("Person");
	}

}
