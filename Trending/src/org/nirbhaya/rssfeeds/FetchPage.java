package org.nirbhaya.rssfeeds;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author arpit
 */
public class FetchPage {
    
    public static void saveUrlPage(String pageUrl, String fileName) throws MalformedURLException, IOException, InterruptedException
    {
        System.getProperties().setProperty("https.proxyHost", "proxy.iiit.ac.in");
        System.getProperties().setProperty("https.proxyPort", "8080");
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.iiit.ac.in", 8080));

        URL url = new URL(pageUrl);

 
        URLConnection c = (URLConnection) url.openConnection(proxy);
        c.connect();

        BufferedReader buff = new BufferedReader(new InputStreamReader(c.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = buff.readLine()) != null) {
            sb.append(line+"\n");
        }

        buff.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));  // query is contains both
        bw.write(sb.toString());                                                        // filename and word, therefore
        bw.close();        
        Thread.sleep(800);
    }
    
    public static Map<String, String> getTOIUrlLocations(String file) throws FileNotFoundException, IOException{
        Map<String, String> urlList = new HashMap<String, String>();
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        
        String completeText = "", line;
        while((line = bufferedReader.readLine()) != null){
            completeText += line;
        }
        
        bufferedReader.close();
        
        // Cities Section
        Pattern sectionP = Pattern.compile("<b>Cities</b><p>(.*?)</p>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE | Pattern.UNIX_LINES);
        
        Matcher sectionM = sectionP.matcher(completeText);
        
        String sectionText = "";
        if(sectionM.find()){
            sectionText = sectionM.group(1);
        }
        
        // Every row
        Pattern cityP = Pattern.compile("<tr>(.*?)</tr>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE | Pattern.UNIX_LINES);
        
        Matcher cityM = cityP.matcher(sectionText);
        
        String cityText = "";
        while(cityM.find()){
            cityText = cityM.group(1);
            String arr[] = parseTOIRow(cityText);
            urlList.put(arr[1], arr[0]);
        }
        
        return urlList;
    }
    
    public static String[] parseTOIRow(String row){
        Pattern urlP = Pattern.compile("<a href=\"(.+?)\" id=\"(.+?)\"", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE | Pattern.UNIX_LINES);
        
        Matcher urlM = urlP.matcher(row);
        String [] array = new String[2];
        if(urlM.find()){
            array[0] = urlM.group(1);
            array[1] = urlM.group(2);
        }
        return array;
    }
    
    public static Map<String, String> getHinduUrlLocations(String file) throws FileNotFoundException, IOException{
        Map<String, String> urlList = new HashMap<String, String>();
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        
        String completeText = "", line;
        while((line = bufferedReader.readLine()) != null){
            completeText += line;
        }
        
        bufferedReader.close();
        
        // Metro Plus
        Pattern sectionP = Pattern.compile("<B>Metro Plus</B></FONT><BR>(.*?)<p>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE | Pattern.UNIX_LINES);
        
        Matcher sectionM = sectionP.matcher(completeText);
        
        String sectionText = "";
        if(sectionM.find()){
            sectionText = sectionM.group(1);
        }
        
        // Every row
        Pattern cityP = Pattern.compile("<a(.*?)</a>(.*?)<a(.*?)</a>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE | Pattern.UNIX_LINES);
        
        Matcher cityM = cityP.matcher(sectionText);
        
        String cityText = "";
        while(cityM.find()){
            cityText = cityM.group(3);
          //  System.out.println(cityText);
            String arr[] = parseHinduRow(cityText);
            urlList.put(arr[1], arr[0]);
        }
        return urlList;
    }

    public static String[] parseHinduRow(String row){
        Pattern urlP = Pattern.compile("\"(.+?)\">(.*)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE | Pattern.UNIX_LINES);
        
        Matcher urlM = urlP.matcher(row);
        String [] array = new String[2];
        if(urlM.find()){
            array[0] = urlM.group(1);
            array[1] = urlM.group(2);
        }
        //System.out.println(array[0] + " " + array[1]);
        return array;
    }
}