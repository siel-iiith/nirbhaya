package org.nirbhaya.trending;

public class Trend
{
	String contentTitle;
	String sourceURL;
	String snippet;
	String imageURL;
	
	public Trend(String contentTitle, String sourceURL, String snippet,
			String imageURL) {
		super();
		this.contentTitle = contentTitle;
		this.sourceURL = sourceURL;
		this.snippet = snippet;
		this.imageURL = imageURL;
	}

}