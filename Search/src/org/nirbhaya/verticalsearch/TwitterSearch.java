/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nirbhaya.verticalsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
*
* @author ajay, nikhil, jayant
*/


@Path("/TwitterSearch")
public class TwitterSearch {      
    
    public static List<TwitterData> getOldTweets(String QueryQ, ConfigurationBuilder cb) throws TwitterException, IOException{
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        List<TwitterData> Vec = new ArrayList<TwitterData>();
        Query query = new Query(QueryQ);
        query.setCount(20);
        int counter=0;
        QueryResult result = twitter.search(query);
        while(counter++ < 1){
            for (Status status : result.getTweets()) {
                TwitterData TData = new TwitterData(QueryQ,status.getText(),status.getId(),status.getCreatedAt(),
                        status.getUser().getName(), status.getUser().getScreenName(),
                        status.getUser().getLocation(), status.getUser().getProfileImageURLHttps());
                Vec.add(TData);
            }
            if(result.hasNext()){
                query=result.nextQuery();
                result=twitter.search(query);
            }
            else {
                break;
            }
        }
        return Vec;
    }
    
    public static void getLiveStream(String QueryQ,ConfigurationBuilder cb){
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        FilterQuery fq = new FilterQuery();
        final String keywords[] = {QueryQ};
        fq.track(keywords);
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                TwitterData TData = new TwitterData(keywords[0],status.getText(),status.getId(),status.getCreatedAt(),
                        status.getUser().getName(), status.getUser().getScreenName(),
                        status.getUser().getLocation(), status.getUser().getProfileImageURLHttps());
                try {
                    TData.writeToTSV();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }                
            }
            @Override public void onException(Exception arg0) {}
            
            @Override public void onDeletionNotice(StatusDeletionNotice arg0) {}
            
            @Override public void onScrubGeo(long arg0, long arg1) {}
            
            @Override public void onStallWarning(StallWarning arg0) {}
            
            @Override public void onTrackLimitationNotice(int arg0) {}
        };
        
        listener.onTrackLimitationNotice(100);
        twitterStream.addListener(listener);
        twitterStream.filter(fq);
    }
    
    public static ConfigurationBuilder setTwitterConfiguration(){
        System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
        System.setProperty("http.proxyPort", "8080");
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("uU1JaPBieACxUtnmdrN7Yg");
        cb.setOAuthConsumerSecret("f15diPZqmuYX1A9NGeYgQkZgTLITNBq0cCPnQ9tqc");
        cb.setOAuthAccessToken("73280561-IPqX0EtB6PcCvu3of44gsFcb7ZneroRmN1MBBtfUV");
        cb.setOAuthAccessTokenSecret("1AxiyQ21CdGVcocEa2bZOP1v9y7MgUHVpaxEXWGU");        
        return cb;
    }
      
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String searchTwitter (@QueryParam("q") String query, @QueryParam("callback") String callback) throws TwitterException, IOException {        
        ConfigurationBuilder cb = setTwitterConfiguration();
        List<TwitterData> tweetData = getOldTweets("india", cb);
        
        String result = "{\"searchResults\": [";
        
        for ( TwitterData current : tweetData ) {
        	String t1 =current.getID().toString();
        	String t2=current.getTweet();
        	String t3=current.getLocation();
        	
        	t1=t1.replaceAll("<[^>]*", "");
        	t1=t1.replaceAll("\"", "&quot;");
        	t1=t1.replaceAll("\'", " ");
        	
        	t2=t2.replaceAll("<[^>]*", "");
        	t2=t2.replaceAll("\"", "&quot;");
        	t2=t2.replaceAll("\'", " ");
        	
        	t3=t3.replaceAll("<[^>]*", "");
        	t3=t3.replaceAll("\"", "&quot;");
        	t3=t3.replaceAll("\'", " ");       
        	
        	result += "{ " + "\"jTweetId\":\"" + t1 +
                    "\" , \"jTweetText\":\"" + t2 +
                    "\" , \"jTweetLocation\":\"" + t3 +
                  "\" }," + "\n";
        }
        
        result=result.substring(0, result.length()-2);
        result += "]" + "\n" + "}";
        result = callback+"("+result+");";
        
        return result;
    }

}
