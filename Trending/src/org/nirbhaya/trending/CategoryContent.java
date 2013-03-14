package org.nirbhaya.trending;
import java.util.List;

/*public class JsonCategoryTrends {
	public List<CategoryContent> catContent = null;*/

public class CategoryContent 
{
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

	public CategoryContent() {

	}
}

//}