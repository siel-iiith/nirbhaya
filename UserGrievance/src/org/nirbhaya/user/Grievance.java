package org.nirbhaya.user;

public class Grievance {
	String name;
	String type;
	String location;
	String comments;
	
	public Grievance() {

	}

	public Grievance(String name, String type, String location, String comments) {
		this.name = name;
		this.type = type;
		this.location = location;
		this.comments = comments;
	}

	public String getComments() {
		return comments;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
