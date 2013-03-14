package org.nirbhaya.verticalsearch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class TwitterData {
	String Query;
	String tweet;
	Long ID;
	Date timeStamp;
	String userName;
	String screenName;
	String location;
	String profileImageURL;

	public TwitterData() {
		// TODO Auto-generated constructor stub
	}

	public TwitterData(String Query, String tweet, Long iD, Date timeStamp,
			String userName, String screenName, String location,
			String profileImageURL) {
		super();
		this.Query=Query;
		this.tweet = tweet.replaceAll("\n", " ");
		this.ID = iD;
		this.timeStamp = timeStamp;
		this.userName = userName;
		this.screenName = screenName;
		this.location = location;
		this.profileImageURL = profileImageURL;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProfileImageURL() {
		return profileImageURL;
	}

	public void setProfileImageURL(String profileImageURL) {
		this.profileImageURL = profileImageURL;
	}

	public void writeToTSV() throws IOException{
		File file = new File("searchedData.tsv");
		System.out.println();
		FileWriter fstream = new FileWriter(file,true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(this.Query + "\t");
		out.write(this.tweet + "\t");
		out.write(this.ID.toString() + "\t");
		out.write(this.timeStamp.toString() + "\t");
		out.write(this.userName + "\t");
		out.write(this.screenName + "\t");
		out.write(this.location + "\t");
		out.write(this.profileImageURL + "\n");
		out.close();
	}


}
