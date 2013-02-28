/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sielproject;

/**
 *
 * @author ajay
 */
public class BingSearchResult {
    
    String URL;
    String title;
    String snippet;
    
    public BingSearchResult(String s1, String s2, String s3) {
        URL = s1;
        title = s2;
        snippet = s3;
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
