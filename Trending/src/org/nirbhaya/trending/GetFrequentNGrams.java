package org.nirbhaya.trending;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.gson.Gson;

/***
 * Class GetFrequentNGrams. This class operates on a directory containing content and finds the frequent words from the content.
 * @author kushal
 *
 */

public class GetFrequentNGrams 
{
	/**
	 * @param args
	 */
	private static HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
	private static ValueComparator bvc =  new ValueComparator(wordCount);
	private static TreeMap<String,Integer> sorted_map = new TreeMap<String, Integer>(bvc);
	private static TreeSet<String> stopWords = new TreeSet<String>();
	
	private static void loadStopWords(String path)
	{
		BufferedReader br = null;
		String line = null;
		
		try 
		{
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			Stemmer stem = new Stemmer();
			while((line = br.readLine()) != null)
			{
				stem.add(line.toCharArray(), line.length());
				stem.stem();
				stopWords.add(stem.toString());
			}
			System.err.println("Stop word size " + stopWords.size());
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		loadStopWords(args[1]);
		String categoryInputPath = args[0];
		File topCatDir = new File(categoryInputPath);
		if(!topCatDir.isDirectory())
		{
			System.out.println("First argument not a directory");
			System.exit(1);
		}
		File []categoryData = topCatDir.listFiles();
		for(int i = 0; i < categoryData.length; i++)
		{
			ReadDir(categoryData[i].getAbsolutePath());
		
			writeTopTrends(categoryData[i].getName());
			wordCount.clear();
			sorted_map.clear();
		}
	}
	
	/**
	 * This function reads the input directory recursively and looks for the content in the directory
	 * Parameters:
	 * 	1. path: path of the input directory
	 * Return: Null
	 */
	public static void ReadDir(String path)
	{
		File directoryWithTextFiles = new File(path);
		
		if(directoryWithTextFiles.isDirectory())
		{
			File []filesInDir = directoryWithTextFiles.listFiles();
			
			//Iterate over all the files in the directory
			for(int index = 0; index < filesInDir.length ; index++)
			{
				// If there are directories inside directories recurse for the content in the inner directories
				if(filesInDir[index].isDirectory())
				{
					ReadDir(filesInDir[index].getAbsolutePath());
				}
				updateWordCount(filesInDir[index].getAbsolutePath());
			}
		}
		else
		{
			updateWordCount(path);
		}
		
		System.out.println("wordcount size:"+wordCount.size());
		sorted_map.putAll(wordCount);
	}

	private static void writeTopTrends(String catName)
	{
		PrintWriter pr = null;
		try {
			pr = new PrintWriter("/home/sandeep/workspace/Trending/"+catName+"-Trends");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<String> iter = sorted_map.keySet().iterator();
		String key = null;
		ArrayList<CategoryContent> jscList = new ArrayList<CategoryContent>();
		CategoryContent jsc = null;
		String json = null;
		
		Gson gson = new Gson();
		int count = 0;
		while(iter.hasNext())
		{
			key = iter.next();
			jsc = new CategoryContent(key,"", "", "", "", "");
			count++;
			jscList.add(jsc);
			if(count == 10)
			{
				break;
			}
		}
		json=gson.toJson(jscList);
		pr.println("{\"catContent\":"+json+"}");
		pr.flush();
		pr.close();
	}
	
	/**
	 * This function reads the input file and updates the word frequency.
	 * It can be called from outside using the ReadDir function.
	 * Parameter:
	 * 	1. path: path of the input file.
	 */
	private static void updateWordCount(String path)
	{
		BufferedReader br = null;
		String line = null;
		//TokenizerModel model = null;
		
		try 
		{
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Stemmer stem = null;
		String stemmedToken = null;
		StringTokenizer stk = null;
		String token = null;
		String combined = null;
		int count = 0;
		try 
		{
			Gson gson = new Gson();
			SearchResult jsonContent = null;
			while((line = br.readLine()) != null)
			{
				line = line.toLowerCase();
				try{
				jsonContent=gson.fromJson(line, SearchResult.class);
				}catch(Exception e)
				{
					System.out.println(path);
					e.printStackTrace();
				}
				combined=jsonContent.getTitle()+" "+jsonContent.getSnippet();
				stk = new StringTokenizer(combined, " !@#$%^&*().,<>?-+=%:;'\"\t}{[]”|\\/_`");
				stem = new Stemmer();
				while(stk.hasMoreTokens())
				{
					token = stk.nextToken();
					token = token.replaceAll("[^a-zA-Z0-9]+","");
					if(token.length() <= 2)
					{
						continue;
					}
					stem.add(token.toCharArray(), token.length());
					stem.stem();
					stemmedToken = stem.toString();
					if(!stopWords.contains(stemmedToken))
					{
						if(wordCount.containsKey(stemmedToken))
						{
							count  = wordCount.get(stemmedToken);
							count++;
							wordCount.put(stemmedToken, count);
						}
						else
						{
							wordCount.put(stemmedToken, 1);
						}
					}
				}
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}