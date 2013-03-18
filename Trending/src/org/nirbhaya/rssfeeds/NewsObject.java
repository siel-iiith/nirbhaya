package org.nirbhaya.rssfeeds;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author arpit
 */
public class NewsObject {

    public NewsObject() {
        title = "";
        description = "";
        guid = "";
        link = "";
        pubDate = "";
    }
    
    public NewsObject(String t, String d, String g, String l, String p){
        title = t;
        description = d;
        guid  = g;
        link = l;
        pubDate = p;
    }
    
    public String title;
    public String description;
    public String guid;
    public String link;
    public String pubDate;
}