package shopping.cart.service;

public class MySearchResult {        
	String jBingTitle;
	String jBingURL;
	String jBingDesc;

	public MySearchResult() {
		// TODO Auto-generated constructor stub
	}

	public MySearchResult(String title, String url, String snippet) {
		super();
		this.jBingTitle = title;
		this.jBingURL = url;
		this.jBingDesc = snippet;
	}

	public String getTitle() {
		return jBingTitle;
	}

	public void setTitle(String title) {
		this.jBingTitle = title;
	}

	public String getUrl() {
		return jBingURL;
	}

	public void setUrl(String url) {
		this.jBingURL = url;
	}

	public String getDesc() {
		return jBingDesc;
	}

	public void setDesc (String desc) {
		this.jBingDesc = desc;
	}
	
}
