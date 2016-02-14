package org.verbot.core;

import org.verbot.spark.SparkHandler;
import org.verbot.tropo.TropoHandler;
import org.verbot.users.beans.Subject;

public class Punishment implements Runnable{

	private Subject subject;
	
	public Punishment(Subject s)
	{
		subject = s;
	}
	@Override
	public void run() {
		// Deduct points
		SparkHandler sH = SparkHandler.instance();
		TropoHandler tH = TropoHandler.instance();		
		subject.setPoints( subject.getPoints() - 4 );
		sH.updatePointsToRoomAndCommunity( subject );
		sH.send(subject.getName() + " Smoking kills! \n I am here to save you...", subject);
		sH.sendFile("https://i.ytimg.com/vi/ky6pu2G1cnk/hqdefault.jpg", subject);
		tH.call( subject, tH.getPunishmentMessageUrl( subject ));
		
	}

}
