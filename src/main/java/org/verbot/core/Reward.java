package org.verbot.core;

import org.verbot.spark.SparkHandler;
import org.verbot.users.beans.Subject;

public class Reward implements Runnable{
	
	private Subject s;
	
	public Reward(final Subject s)
	{
		this.s = s;
	}
	@Override
	public void run() {
		/*
		 * Update points in Spark Room Label
		 * Send Image in Spark Room
		 * Set next Target
		 * Update Community Room
		 * Send SMS optional
		 */
		SparkHandler sH = SparkHandler.instance();
		sH.updatePointsToRoomAndCommunity( s );
		sH.send( "Wow " + s.getName() + " i have a reason to appriciate you. You have successfully completed the goal...", s);
		sH.send( "Come on every one... Give a token of appriciation..", s);
		sH.sendFile( getMotivationalImageUrl(), s );
		sH.send(" I know you can now go further.. Lets try another goal..", s);
		
		sH.sendToCommunity( s.getName() + " achived goal, he is moving towards next level.... Lets comptete", s);
		
		
	}
	
	private String getMotivationalImageUrl()
	{
		return null;
	}

}
