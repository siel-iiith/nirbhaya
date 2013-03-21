package org.nirbhaya.trending;
import opengraph.*;
public class GetImageFromUrl {
	/**
	 * @param args
	 */
	public String getImageUrlForATrend(String uri) {
		String imageUrlForATrend=null;
		try
		{
			OpenGraph url = null;
			System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
		    System.setProperty("http.proxyPort", "8080");
		    try {
		     url = new OpenGraph(uri, true);
			
				imageUrlForATrend =url.getContent("image");
			} catch (Exception e) {
				if(url == null)
				{
					System.out.println("URL is null; @#$%#@#$%#$%#$SDRTET$^");
				}
				imageUrlForATrend = "";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    return imageUrlForATrend;
	}

}
