package org.nirbhaya.verticalsearch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class QueryExpansion {
	
	public static Set<String> places;
	public static Set<String> grievanceWords;
	private BufferedReader bfr;
	
	public QueryExpansion() {
		
		places = new HashSet<String>();
		grievanceWords = new HashSet<String>();
		
		String nefile = "/home/clia/en_ne.txt";
		
		
		//String nefile = "/en_ne.txt";
		String line = "";

		try {
			bfr = new BufferedReader(new FileReader(nefile));
			while ( (line = bfr.readLine()) != null ) {
				places.add(line.toString().trim().toLowerCase());
			}
			
			nefile = "/home/clia/GrievanceWords.txt";
			//nefile="GrievanceWords.txt";
			bfr = new BufferedReader(new FileReader(nefile));
			line = "";
			while ( (line = bfr.readLine()) != null ) 
			{
				grievanceWords.add(line.toString().trim().toLowerCase());
			}
			System.out.println("Loading NEs");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String expandedQuery (String query) {
		String result = query;
		
		String[] temp = query.split(" ");
		
		boolean placeFlag = false;
		boolean grievanceFlag = false;
		
		for (String s : temp) {
			if ( grievanceWords.contains(s) ) {
				grievanceFlag = true;
			}
			if ( places.contains(s) ) {
				placeFlag = true;
			}
		}
		
		if ( grievanceFlag == false ) { //Pick top trending grievance words
			result += " crime"; 
		}

		if ( placeFlag == false ) { //Pick India
			result += " india"; 
		}		
		return result;
	}
}
