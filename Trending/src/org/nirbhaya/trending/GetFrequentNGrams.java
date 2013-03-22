package org.nirbhaya.trending;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.gson.Gson;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

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
	private static HashMap<String, Integer> bigramCount = new HashMap<String, Integer>();
	private static ValueComparator bvc =  new ValueComparator(wordCount);
	private static ValueComparator bigrambvc =  new ValueComparator(bigramCount);
	private static TreeMap<String,Integer> sorted_map = new TreeMap<String, Integer>(bvc);
	private static TreeMap<String,Integer> bigramSorted_map = new TreeMap<String, Integer>(bigrambvc);
	private static TreeSet<String> stopWords = new TreeSet<String>();
	private static Properties prop = new Properties();
	private static HashMap<String, ArrayList<Trend>> trendToTrendData = new HashMap<String, ArrayList<Trend>>();

	private static void loadStopWords()
	{
		BufferedReader br = null;
		String line = null;

		try 
		{
			br = new BufferedReader(new FileReader(prop.getProperty("stopwordFilePath")));
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
		loadProperties();
		loadStopWords();
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
			bigramCount.clear();
			bigramSorted_map.clear();
			trendToTrendData.clear();
		}
	}

	private static void loadProperties() 
	{
		// TODO Auto-generated method stub
		try 
		{
			//load a properties file
			prop.load(new FileInputStream("trending.properties"));
			//get the property value and print it out
		} catch (IOException ex) 
		{
			System.out.println("No trending.properties file\n");
			ex.printStackTrace();
			System.exit(-1);
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
		bigramSorted_map.putAll(bigramCount);
	}

	private static void writeTopTrends(String catName)
	{
		GetImageFromUrl image=new GetImageFromUrl();
		PrintWriter pr = null;
		int topTrendsToShow = Integer.parseInt(prop.getProperty("topTrendsToShow"));
		int percentageBigrams = Integer.parseInt(prop.getProperty("percentageBigrams"));
		try 
		{
			pr = new PrintWriter("/home/sandeep/workspace/Trending/"+catName+"-Trends");
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<String> unigramIter = sorted_map.keySet().iterator();
		Iterator<String> bigramIter = bigramSorted_map.keySet().iterator();
		
		String key = null;
		CategoryContent jsc = null;
		String json = null;
		String imgURL = null;
		ArrayList<Trend> trend = null;
		ArrayList<CategoryContent> jscList = new ArrayList<CategoryContent>();
		Gson gson = new Gson();
		ArrayList<String> imageUrls = new ArrayList<String>();

		int index = 0;
		for(int count = 1; count <= topTrendsToShow ; count++)
		{
			if(count < percentageBigrams)
			{
				key = bigramIter.next();
			}
			else
			{
				key = unigramIter.next();
			}
			
			trend = trendToTrendData.get(key);

			while((imgURL = trend.get(index).imageURL) != null)
			{
				index++;
			}
			index = 0;
			jsc = new CategoryContent(key, imgURL, trend);
            jscList.add(jsc);
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
		String url = null;
		Stemmer stem = null;
		String stemmedToken = null;
		String bigram = null;
		String prevToken = null;
		StringTokenizer stk = null;
		//String token = null;
		String combined = null;
		int count = 0;
		try 
		{
			Gson gson = new Gson();
			GetImageFromUrl image=new GetImageFromUrl();
			SearchResult jsonContent = null;
			Properties props = new Properties();
			props.put("annotators", "tokenize, ssplit, pos, lemma"); 
			StanfordCoreNLP pipeline = new StanfordCoreNLP(props, false);

			while((line = br.readLine()) != null)
			{
				line = line.toLowerCase();
				try
				{
					jsonContent=gson.fromJson(line, SearchResult.class);
				}
				catch(Exception e)
				{
					System.out.println(path);
					e.printStackTrace();
				}
				Trend trend;

				trend = new Trend(jsonContent.getTitle(), jsonContent.getUrl(), jsonContent.getSnippet(), image.getImageUrlForATrend(jsonContent.getUrl()));

				combined=jsonContent.getTitle()+" "+jsonContent.getSnippet();
				url=jsonContent.getUrl();
				//urlIdToURL.put(urlId++, url);
				Annotation document = pipeline.process(combined);

				for(CoreMap sentence: document.get(SentencesAnnotation.class)) 
				{    
					for(CoreLabel element: sentence.get(TokensAnnotation.class)) 
					{       
						stemmedToken = element.get(LemmaAnnotation.class);

						stemmedToken = stemmedToken.replaceAll("[^a-zA-Z0-9]+","");

						if(stemmedToken.length() <= 2)
						{
							continue;
						}

						ArrayList<Trend> tempURLIdArray = null;

						if(!stopWords.contains(stemmedToken))
						{
							if(trendToTrendData.containsKey(stemmedToken))
							{
								tempURLIdArray = trendToTrendData.get(stemmedToken);
								tempURLIdArray.add(trend);
								trendToTrendData.put(stemmedToken, tempURLIdArray);
							}
							else
							{
								tempURLIdArray = new ArrayList<Trend>();
								tempURLIdArray.add(trend);
								trendToTrendData.put(stemmedToken, tempURLIdArray);
							}

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

							if(prevToken != null)
							{
								bigram = prevToken + " " + stemmedToken;
								
								if(trendToTrendData.containsKey(bigram))
								{
									tempURLIdArray = trendToTrendData.get(bigram);
									tempURLIdArray.add(trend);
									trendToTrendData.put(bigram, tempURLIdArray);
								}
								else
								{
									tempURLIdArray = new ArrayList<Trend>();
									tempURLIdArray.add(trend);
									trendToTrendData.put(bigram, tempURLIdArray);
								}
								
								if(bigramCount.containsKey(bigram))
								{
									count  = bigramCount.get(bigram);
									count++;
									bigramCount.put(bigram, count);
								}
								else
								{
									bigramCount.put(bigram, 1);
								}
								prevToken = stemmedToken;
							}
							else
							{
								prevToken = stemmedToken;
							}
						}
						//System.out.println("lemmatized version :" + lemma);
					} 
				}
				/*while(stk.hasMoreTokens())
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

						if(prevToken != null)
						{
							bigram = prevToken + " " + stemmedToken;
							if(bigramCount.containsKey(bigram))
							{
								count  = bigramCount.get(bigram);
								count++;
								bigramCount.put(bigram, count);
							}
							else
							{
								bigramCount.put(bigram, 1);
							}
						}
						else
						{
							prevToken = token;
						}

					}
				}*/
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}