package org.verbot.core;

import java.util.List;

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
    private static int i = 1;	
	private String getMotivationalImageUrl()
	{
		String image1 = "http://rlv.zcache.com/1st_anniversary_of_quitting_smoking_greeting_card-r1cc900225da34861b3abc8341f25ed22_xvy9b_1024.jpg";
		String image2 = "http://images.google.de/imgres?imgurl=http%3A%2F%2Fwww.eciggiesreviews.com%2Fwp-content%2Fuploads%2F2013%2F12%2Fbenefits-of-quitting-smoking-timeline.jpg";
		String image3 = "http://www.smokeremedy.com/wp-content/uploads/2012/08/Fotolia_42741713_XS.jpg";
		String image4 = "http://msh-media.s3-us-west-2.amazonaws.com/wp-content/uploads/2015/11/19180000/11_19_smoking_infographic.jpg";
		String image5 = "http://www.myphenomfitness.com/wp-content/uploads/2012/10/best-way-to-quit-smoking-is-through-exercise.jpg";
		String[] images = {image1, image2, image3, image4, image5};
		if ( i > 4 )
			i = 1;
		return images[i++];
		
	}

}
