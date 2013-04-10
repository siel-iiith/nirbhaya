package org.nirbhaya.trending;

import java.util.ArrayList;

public class catcontentSerialize {
     
	/**
	 * @param args
	 */
	
	public String type=null;
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
	 public catcontentSerialize(String type)
	 {
		 this.type=type;
	 }
	 public ArrayList<CategoryContent> getCatContent() {
		return catContent;
	}public void setCatContent(ArrayList<CategoryContent> catContent) {
		this.catContent = catContent;
	}

}
