package org.nirbhaya.trending;
import java.util.ArrayList;

/*public class JsonCategoryTrends {
	public List<CategoryContent> catContent = null;*/

public class CategoryContent 
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

//}