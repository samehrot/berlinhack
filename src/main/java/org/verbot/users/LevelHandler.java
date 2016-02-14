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
		levelRoomMapping.put( Level.LEVEL0, SparkHandler.instance().createEmptyRoom( Level.LEVEL0.toString()));
		levelRoomMapping.put( Level.LEVEL1, SparkHandler.instance().createEmptyRoom( Level.LEVEL1.toString()));
		levelRoomMapping.put( Level.LEVEL2, SparkHandler.instance().createEmptyRoom( Level.LEVEL2.toString()));
		levelRoomMapping.put( Level.LEVEL3, SparkHandler.instance().createEmptyRoom( Level.LEVEL3.toString()));
		levelRoomMapping.put( Level.LEVEL4, SparkHandler.instance().createEmptyRoom( Level.LEVEL4.toString()));
		//TODO: Add level to Room
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
