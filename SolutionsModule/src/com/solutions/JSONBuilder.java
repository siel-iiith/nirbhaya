package com.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import com.google.gson.Gson;

class ContactList {
//	Department department = new Department();
//	String dept_name = department.getDeptName();
//	long departmentid = department.getDepartmentId();
//	String address = department.getAddress();
//	String location = department.getLocation();
//	String RTI_Info = department.getRTIInfo();
//	department dept_type = department.getDepartmentType();
	String dept_name;
	String departmentid;
//	String designation = "Student";
	String address;
	String location;
	String dept_type;
	String phone;
	String tag;
	String emailId;
	
	public ContactList(String dept_name, String departmentid, String address, String location, String dept_type, String phone, String emailId) throws IOException {
		this.dept_name = dept_name;
		this.departmentid = departmentid;
		this.address = address;
		this.location = location;
		this.dept_type = dept_type;
		this.phone = phone;
		this.emailId = emailId;
	}
}

@Path("/Solution")
public class JSONBuilder {
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public static String outputJSON (@QueryParam("q") String query, @QueryParam("callback") String callback) throws IOException, JSONException {
		
//		String tag = "";
//		String dept_name = "";
//		String departmentid = "";
//		String designation = "Student";
//		String address = "";
//		String location = "";
//		String dept_type = "";
//		String phone = "";
		Gson gson =new Gson();
		ArrayList<ContactList> contactArray = new ArrayList<ContactList>();
//		if (query.equals("electricity")) {
//		}
		BufferedReader contact=new BufferedReader(new FileReader(new File("contacts.txt")));
		String str = "";
		while ((str=contact.readLine()) != null) {
			if (str.split("\t")[0].equals(query)) {
//				JSONObject jsonObj = new JSONObject();
				ContactList contactList = new ContactList(str.split("\t")[1],str.split("\t")[2],str.split("\t")[3],str.split("\t")[4],str.split("\t")[5],str.split("\t")[6],str.split("\t")[7]);
				contactArray.add(contactList);
//				contactList.put(gson.toJson(jsonObj));
			}
		}
		contact.close();
		String jsonArray = null;
		jsonArray = gson.toJson(contactArray);	
//		try {
//			//write converted json data to a file named "file.json"
//			FileWriter writer = new FileWriter("file.json");
//			writer.append(json1);
//			writer.append("\n");
//			writer.close();
//	 
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	 
//		System.out.println(json);
//		return callback +"("+jsonArray+")";
		return callback +"({\"contactList\":"+jsonArray+"})";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		outputJSON("person");
//		outputJSON("electricity");
	}

}
