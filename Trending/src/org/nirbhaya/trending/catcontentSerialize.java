package org.nirbhaya.trending;

import java.util.ArrayList;

public class catcontentSerialize {
     
	/**
	 * @param args
	 */
	
	 public ArrayList<CategoryContent> catContent;
	 
	 public static class CategoryContent 
	 {
	 	String trendName = null;
	 	String imageURL = null;
	 	ArrayList<Trend> trendArray = null;
	 	
	 	public CategoryContent(String trendName, String imageURL,
	 			ArrayList<Trend> trendArray) {
	 		super();
	 		this.trendName = trendName;
	 		this.imageURL = imageURL;
	 		this.trendArray = trendArray;
	 	}
	 	
	 	public CategoryContent() {

	 	}
	 }
	 public catcontentSerialize()
	 {
		 
	 }
	 public ArrayList<CategoryContent> getCatContent() {
		return catContent;
	}public void setCatContent(ArrayList<CategoryContent> catContent) {
		this.catContent = catContent;
	}
/*	public String trendName;
	public String imageURL;
	public ArrayList<Trend> trendArray;
	
	public static class Trend {
		public String contentTitle;
		public String sourceURL;
		public String snippet;
		public String imageURL;
	public Trend() {
		
	}
	}
public catcontentSerialize()
{
	
}*/
}
