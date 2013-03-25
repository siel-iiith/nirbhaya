package org.nirbhaya.trending;
/**
 * 
 */

/**
 * @author sandeep
 *
 */
public class BingSearchResult {
    
    String URL;
    String title;
    String snippet;
    String date;
    String source;
    
    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BingSearchResult(String s1, String s2, String s3, String s4,String s5) {
        URL = s1;
        title = s2;
        snippet = s3;
        date = s4;
        source = s5;
    }
    
    String getURL() {
        return URL;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getSnippet() {
        return snippet;
    }
    
    public void setURL(String url1) {
        URL=url1;
    }
    
    public void setTitle(String title1) {
        title=title1;
    }
    
    public void setSnippet(String snippet1) {
        snippet=snippet1;
    }

}
