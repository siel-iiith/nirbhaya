package org.nirbhaya.trending;

import java.util.List;

public class JsonCategoryTrends {
	public List<CategoryContent> catContent;


	public static class CategoryContent {
		String title;
		String url;
		String image;
		String timestamp;
		String location;
		String source;

		
/*		public CategoryContent(String title, String url, String image,
				String timestamp, String location, String source) {
			super();
			this.title = title;
			this.url = url;
			this.image = image;
			this.timestamp = timestamp;
			this.location = location;
			this.source = source;
		}*/

		public CategoryContent() {

		}
	}

	public JsonCategoryTrends() {

	}

}