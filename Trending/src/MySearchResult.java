
public class MySearchResult {	
	String title;
	String url;
	String snippet;
	
	public MySearchResult() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public MySearchResult(String title, String url, String snippet) {
		super();
		this.title = title;
		this.url = url;
		this.snippet = snippet;
	}



	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	

}
