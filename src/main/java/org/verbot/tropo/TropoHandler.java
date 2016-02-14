package org.verbot.tropo;

import org.verbot.users.beans.Person;

public class TropoHandler {
	
	private static TropoHandler handler = new TropoHandler();
	
	private TropoHandler(){
	}
	
	public static TropoHandler instance()
	{
		return handler;
	}
	
	public void call(final Person person, final String msg)
	{
		
	}
	
	public void sms(final Person person, final String msg)
	{
		
	}
	
	public String getAchivementMessageUrl(final Person person)
	{
		return "Dad i know you can do it. You are our Hero";
	}
	
	public String getPunishmentMessageUrl(final Person person)
	{
		return "Dad i need you around on my graduation ceremony, if you smoke how could you make it.";
	}

}
