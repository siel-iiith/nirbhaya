import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.TreeMap;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

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
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		ReadDir(args[0]);
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
		sorted_map.putAll(wordCount);
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
		TokenizerModel model = null;
		
		try 
		{
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InputStream modelIn = null;
		try 
		{
			modelIn = new FileInputStream("en-token.bin");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try 
		{
			  model = new TokenizerModel(modelIn);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelIn != null) {
		    try {
		      modelIn.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
		Stemmer stem = null;
		String stemmedToken = null;
		int count = 0;
		try 
		{
			Tokenizer tokenizer = new TokenizerME(model);
			while((line = br.readLine()) != null)
			{
				String tokens[] = tokenizer.tokenize(line);
				stem = new Stemmer();
				for(int i = 0 ; i < tokens.length ; i++)
				{
					stem.add(tokens[i].toCharArray(), tokens[i].length());
					stem.stem();
					stemmedToken = stem.toString();
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
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
