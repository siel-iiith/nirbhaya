package org.nirbhaya.heatmap;

public class Stat {
	Location location;
	String url;
	String snippet;
	double value;
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Stat(Location location, String url, String snippet, double value) {
		super();
		this.location = location;
		this.url = url;
		this.snippet = snippet;
		this.value = value;
	}
	public Stat() {
		
	}
}
