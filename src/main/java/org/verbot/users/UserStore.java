package org.verbot.users;

import java.util.HashMap;
import java.util.Map;

import org.verbot.users.beans.Subject;

public class UserStore {

	// Device ID to Subject
	private static Map<String, Subject> userMap = new HashMap<String, Subject>();
	
	private static UserStore store = new UserStore();
	
	private UserStore(){		
	}
	
	public static UserStore getInstance()
	{
		return store;
	}
	
	public Subject addSubject(final Subject subject)
	{
		userMap.put( subject.getDeviceId(), subject );
		return subject;
	}
	
	public Subject getSubject(final String deviceId)
	{
		return userMap.get( deviceId );
	}
	
	public Map<String, Subject> getAll()
	{
		return userMap;
	}
} 
