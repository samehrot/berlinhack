package org.verbot.core;

import java.util.concurrent.BlockingQueue;

import org.verbot.users.LevelHandler;
import org.verbot.users.beans.Subject;

public class SmokePatternMonitor implements Runnable{
	
	private BlockingQueue<Subject> q;
	
	public SmokePatternMonitor(BlockingQueue<Subject> q)
	{
		this.q = q;
	}
	
	private static long rewardSpan = 60 * 1000;
	private static int rewardPoint = 2;

	@Override
	public void run() {
		LevelHandler lH = LevelHandler.instance();
		while ( true )
		{
			try
			{
				Subject s = q.take();
				long cT = System.currentTimeMillis();
				long lT = s.getLastSmokeDate().getTime();
				if ( cT - lT >= rewardSpan )
				{
					System.out.println( s.getName() + " is now eligible for reward");
					s.setPoints( s.getPoints() + rewardPoint );
					
					// First check whether its a reward or achievement
					if ( lH.getPointsForLevel( lH.nextLevel( s.getLevel())) <= s.getPoints() )
					{
						System.out.println( "Wow its next level points......");
						new Thread( new Achivement( s ) ).start();
					}
					else
					{
						new Thread( new Reward(s)).start();
					}
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
	}
	
	
	
	

}
