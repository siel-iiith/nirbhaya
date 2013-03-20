package org.nirbhaya.trending;

import java.util.List;


public class CategoryTypes {	
	public List<Categories> categories;
	
	
	
	public static class Categories {
		public String name;
		public List<SubCategories> subcategories;
		
		public static class SubCategories {
			public String subname;
			
			public SubCategories() {
				
			}
		}
	}
	
	
	public CategoryTypes() {
		// TODO Auto-generated constructor stub
	}
}
