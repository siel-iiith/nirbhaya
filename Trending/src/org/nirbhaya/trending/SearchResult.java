package org.nirbhaya.trending;

public class SearchResult {	
	String title;
	String url;
	String snippet;
	String date;
	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}
	String source;
	
	public String getSource() {
		return source;
	}



	public void setSource(String source) {
		this.source = source;
	}



	public SearchResult() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public SearchResult(String title, String url, String snippet,String date,String source) {
		super();
		this.title = title;
		this.url = url;
		this.snippet = snippet;
		this.date=date;
		this.source=source;
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
