package org.nirbhaya.trending;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.util.List;
>>>>>>> 0aac96ad7173c7e92591075bbd5ca04bbedad34e

/*public class JsonCategoryTrends {
	public List<CategoryContent> catContent = null;*/

public class CategoryContent 
{
<<<<<<< HEAD
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
	
=======
	String title = null;
	String url = null;
	String image = null;
	String timestamp = null;
	String location = null;
	String source = null;

	public CategoryContent(String title, String url, String image,
			String timestamp, String location, String source) {
		super();
		this.title = title;
		this.url = url;
		this.image = image;
		this.timestamp = timestamp;
		this.location = location;
		this.source = source;
	}

>>>>>>> 0aac96ad7173c7e92591075bbd5ca04bbedad34e
	public CategoryContent() {

	}
}

//}