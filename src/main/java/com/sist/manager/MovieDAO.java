package com.sist.manager;
/*
 * private int mno;
    private String title;
    private String poster;
    private double score;
    private String genre;
    private String regdate;
    private String time;
    private String grade;
    private String director;
    private String actor;
    private String story;
    private int type;
 */
import com.mongodb.*;
import com.sist.vo.*;
public class MovieDAO {
    private MongoClient mc;
    private DB db;
    private DBCollection dbc;
    
    public MovieDAO()
    {
    	try
    	{
    		mc=new MongoClient("localhost",27017);
    		db=mc.getDB("mydb");
    		dbc=db.getCollection("movie");
    	}catch(Exception ex){}
    }
    
    public void movieInsert(MovieVO vo)
    {
    	try
    	{
    		DBCursor cursor=dbc.find();
    		int max=0;
    		while(cursor.hasNext())
    		{
    			BasicDBObject obj=(BasicDBObject)cursor.next();
    			int no=obj.getInt("mno");
    			if(max<no)
    				max=no;
    		}
    		cursor.close();
    		BasicDBObject obj=new BasicDBObject();
    		obj.put("mno", max+1);
    		obj.put("title", vo.getTitle());
    		obj.put("poster", vo.getPoster());
    		obj.put("score", vo.getScore());
    		obj.put("genre", vo.getGenre());
    		obj.put("regdate", vo.getRegdate());
    		obj.put("time", vo.getTime());
    		obj.put("grade", vo.getGrade());
    		obj.put("director", vo.getDirector());
    		obj.put("actor", vo.getActor());
    		obj.put("story", vo.getStory());
    		obj.put("type", vo.getType());
    		
    		dbc.insert(obj);
    	}catch(Exception ex){}
    }
    /*
     *   private String title;
		   private String poster;
		   private String link;
		   private String author;
		   private String regdate;
		   private String content;
     */
    public void newsInsert(NewsVO vo)
    {
    	try
    	{
    		BasicDBObject obj=new BasicDBObject();
    		obj.put("title", vo.getTitle());
    		obj.put("poster", vo.getPoster());
    		obj.put("link", vo.getLink());
    		obj.put("author", vo.getAuthor());
    		obj.put("regdate", vo.getRegdate());
    		obj.put("content", vo.getContent());
    		dbc.insert(obj);
    	}catch(Exception ex){}
    }
}

















