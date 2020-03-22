package com.sist.manager;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.vo.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MovieManager {
	MovieDAO dao=new MovieDAO();
    public ArrayList<MovieVO> movieListData()
    {
    	ArrayList<MovieVO> list=new ArrayList<MovieVO>();
    	
    	try
    	{
    	    int k=1;
    		//for(int i=1;i<=6;i++)
    		{
    			Document doc=Jsoup.connect("https://movie.daum.net/boxoffice/yearly").get();
    			Elements link=doc.select("div.info_tit a");
    			for(int j=0;j<link.size();j++)
    			{
    			  try
    			  {
    				Element elem=link.get(j);
    				//System.out.println((i)+"-"+elem.attr("href"));
    				String mLink="https://movie.daum.net"+elem.attr("href");
    				// /moviedb/main?movieId=127138
    				Document doc2=Jsoup.connect(mLink).get();
    				//System.out.println(doc2);
    				/*
    				 *  private String title;
					    private double score;
					    private String genre;
					    private String regdate;
					    private String time;
					    private String grade;
					    private String director;
					    private String actor;
					    private String story;
    				 */
					
					  Element title=doc2.select("div.subject_movie strong.tit_movie").get(0);
					  System.out.println(title.text());
					  Element score=doc2.selectFirst("div.subject_movie em.emph_grade");
					  System.out.println(score.text());
					  
					  
					  Element genre=doc2.select("dl.list_movie dd.txt_main").get(0);
					  System.out.println(genre.text());
					  Element regdate=doc2.select("dl.list_movie dd.txt_main").get(1);
					  System.out.println(regdate.text());
					  
					  Element time=doc2.select("dl.list_movie dd").get(3);
					  System.out.println(time.text());
					  
					  String temp=time.text();
					  StringTokenizer st=new StringTokenizer(temp,",");
					  String strTime=st.nextToken();
					  String strGrade=st.nextToken().trim();
					  
					  System.out.println(strTime);
					  System.out.println(strGrade);
					  
					  //Element time=doc2.selectFirst("div.subject_movie strong.tit_movie");; 
					  //Element grade=doc2.selectFirst("div.subject_movie strong.tit_movie");; 
					  Element director=doc2.select("dl.list_movie dd.type_ellipsis").get(0);
					  System.out.println(director.text());
					  Element actor=doc2.select("dl.list_movie dd.type_ellipsis").get(1);
					  System.out.println(actor.text());
					  //Element actor=doc2.selectFirst("div.subject_movie strong.tit_movie");; 
					  Element story=doc2.selectFirst("div.desc_movie p");
					  System.out.println(story.text());
					  Element poster=doc2.selectFirst("a.area_poster img.img_summary");
					
					  MovieVO vo=new MovieVO();
					  vo.setTitle(title.text());
					  vo.setScore(Double.parseDouble(score.text()));
					  vo.setGrade(strGrade);
					  vo.setTime(strTime);
					  vo.setActor(actor.text());
					  vo.setDirector(director.text());
					  vo.setGenre(genre.text());
					  vo.setRegdate(regdate.text());
					  vo.setStory(story.text());
					  vo.setPoster(poster.attr("src"));
					  vo.setType(5);
					  
					  dao.movieInsert(vo);
					  list.add(vo);
					  
					  System.out.println("k="+k);
					  k++;
    			  }catch(Exception ex) {}
					 
    			}
    		}
    		System.out.println("DataBase Insert End...");
    	}catch(Exception ex){}
    	return list;
    }

    public ArrayList<NewsVO> newsAllData()
    {
    	ArrayList<NewsVO> list=new ArrayList<NewsVO>();
    	Date date=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");// 20200218
    	String today=sdf.format(date);
    	try
    	{
    		for(int i=1;i<=18;i++)
    		{
    		  Document doc=Jsoup.connect("https://movie.daum.net/magazine/new?tab=nws&regdate="+today+"&page="+i).get();
    		  Elements title=doc.select("ul.list_line strong.tit_line a");
    		  Elements poster=doc.select("ul.list_line a.thumb_line span.thumb_img");
    		  Elements link=doc.select("ul.list_line strong.tit_line a");
    		  Elements temp=doc.select("span.cont_line span.state_line");
    		  Elements content=doc.select("span.cont_line a.desc_line");
    		  for(int j=0;j<title.size();j++)
    		  {
    			  System.out.println(title.get(j).text());
    			  
    			  String ss=poster.get(j).attr("style");
    			  ss=ss.substring(ss.indexOf("(")+1,ss.lastIndexOf(")"));
    			  System.out.println(ss);
    			  
    			  System.out.println(link.get(j).attr("href"));
    			  String str=temp.get(j).text();
    			  String author=str.substring(0,str.indexOf("・"));
    			  String regdate=str.substring(str.lastIndexOf("자")+1);
    			  System.out.println(author);
    			  System.out.println(regdate);
    			  System.out.println(content.get(j).text());
    			  System.out.println("==================================================");
    		      NewsVO vo=new NewsVO();
    		      vo.setTitle(title.get(j).text());
    		      vo.setLink(link.get(j).attr("href"));
    		      vo.setContent(content.get(j).text());
    		      vo.setPoster(ss);
    		      vo.setRegdate(regdate);
    		      vo.setAuthor(author);
    		      // 오라클 전송 
    		      dao.newsInsert(vo);
    		  }
    		}
    		System.out.println("Save End...");
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	return list;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        MovieManager m=new MovieManager();
        m.newsAllData();
        
	}

}





