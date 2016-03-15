package org.verbot.core;

import org.verbot.spark.SparkHandler;
import org.verbot.tropo.TropoHandler;
import org.verbot.users.LevelHandler;
import org.verbot.users.beans.Level;
import org.verbot.users.beans.Subject;

public class Achivement implements Runnable{

	private Subject s;
	public Achivement(Subject s) {
		this.s = s;
	}
	@Override
	public void run() {
		/**
		 * Send message about achivement to all Spark Rooms
		 * Upgrade level to next level
		 * Send Welcome message to community
		 * Send Tropo call to user with loved one message
		 * Send SMS to loved one about achivements
		 */
		SparkHandler sH = SparkHandler.instance();
		LevelHandler lH = LevelHandler.instance();
		Level nL = lH.nextLevel( s.getLevel() );
		sH.updatePointsToRoomAndCommunity( s );
		sH.sendRoomAndCommunity("Its amazing.." + s.getName() + " you are doing just amazing."
				+ "\n You are not upgraded to " + nL.toString(), s);
		
		// Adding to next level community
		sH.upgradeLevel( s );
		
		// Do a Tropo Call
		TropoHandler tH = TropoHandler.instance();
		tH.call( s, tH.getAchivementMessageUrl( s ));
		
		
	}

}
