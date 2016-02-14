package org.verbot.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.scheduling.annotation.Scheduled;
import org.verbot.users.UserStore;
import org.verbot.users.beans.Subject;

public class SubjectMonitor {

	private BlockingQueue<Subject> q = new LinkedBlockingQueue<Subject>();
	
	public SubjectMonitor() {
		new Thread(new SmokePatternMonitor(q)).start();
	}
	
	@Scheduled(fixedDelay=30000)
	public void scheduledRun()
	{
		System.out.println("Scanning all Users");
		UserStore store = UserStore.getInstance();
		store.getAll().forEach( (device, subject) -> {
			q.add( subject );
		});
	}

}
