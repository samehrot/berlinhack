package org.verbot.users;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.verbot.spark.SparkHandler;
import org.verbot.users.beans.Level;

import com.ciscospark.Room;

public class LevelHandler {
	
	private static LinkedList<Level> levelList = new LinkedList<Level>();
	private static Map<Level, Room> levelRoomMapping = new HashMap<Level, Room>();
	private static Map<Level, Integer> levelPointMapping = new HashMap<Level, Integer>();
	
	private static LevelHandler handler = new LevelHandler();
	
	public static LevelHandler instance()
	{
		return handler;
	}
	private LevelHandler()
	{
		levelList.add( Level.LEVEL0 );
		levelList.add( Level.LEVEL1 );
		levelList.add( Level.LEVEL2 );
		levelList.add( Level.LEVEL3 );
		levelList.add( Level.LEVEL4 );
		levelPointMapping.put(Level.LEVEL0, 0);
		levelPointMapping.put(Level.LEVEL1, 10);
		levelPointMapping.put(Level.LEVEL2, 20);
		levelPointMapping.put(Level.LEVEL3, 40);
		levelPointMapping.put(Level.LEVEL4, 80);
		
		levelRoomMapping.put( Level.LEVEL0, getLevelRoom("Y2lzY29zcGFyazovL3VzL1JPT00vOTViODJkNDAtZDJlNC0xMWU1LTg4ZjctM2I4NTY0ZDA3MmNj", "LEVEL0"));
		levelRoomMapping.put( Level.LEVEL1, getLevelRoom("Y2lzY29zcGFyazovL3VzL1JPT00vOTY4YzQ2MjAtZDJlNC0xMWU1LTkzZGMtYTcxMTEwZmQ4NTYw", "LEVEL1"));
		levelRoomMapping.put( Level.LEVEL2, getLevelRoom("Y2lzY29zcGFyazovL3VzL1JPT00vOTdhMDljNTAtZDJlNC0xMWU1LThmMWYtOWI5MDE0YWM5Y2Iw", "LEVEL2"));
		levelRoomMapping.put( Level.LEVEL3, getLevelRoom("Y2lzY29zcGFyazovL3VzL1JPT00vOTg2ZDYyMzAtZDJlNC0xMWU1LWFhMWQtZDM1MTA2MTI4NTky", "LEVEL3"));
		levelRoomMapping.put( Level.LEVEL4, getLevelRoom("Y2lzY29zcGFyazovL3VzL1JPT00vOWExY2JmZTAtZDJlNC0xMWU1LTljMGEtODUxZjE3YzM3N2Nj", "LEVEL4"));
	}
	
	private Room getLevelRoom(final String roomId, final String title)
	{
		Room r = new Room();
		r.setId( roomId );
		r.setTitle( title );
		return r;
	}
	
	public Room getRoomForLevel(Level level)
	{
		return levelRoomMapping.get( level );
	}	
	
	public Level nextLevel(Level level)
	{
		int index = levelList.indexOf(level);
		if ( index >= levelList.size() )
		{
			return level;
		}
		else 
		{
			return levelList.get( index + 1 );
		}
	}
	
	public int getPointsForLevel(Level level)
	{
		return levelPointMapping.get( level );
	}
}
