package org.verbot.spark;

import java.net.URI;

import org.verbot.users.LevelHandler;
import org.verbot.users.beans.Person;
import org.verbot.users.beans.Subject;

import com.ciscospark.Membership;
import com.ciscospark.Message;
import com.ciscospark.Room;
import com.ciscospark.Spark;

public class SparkHandler {

	private static SparkHandler sparkHandler = new SparkHandler();

	private final String accessToken = "MjQ5YmYxMGMtZjI5Ny00YzdjLWI5ZjEtOTliMTk4ZTg5Zjg1OWZjY2QzZTctN2Zk";

	private Spark spark = Spark.builder().baseUrl(URI.create("https://api.ciscospark.com/v1")).accessToken(accessToken)
			.build();
	
	private static final String WELCOME = "Lots of Sparks from VerBot, this room is created on the behalf of"
			+ " %s and for a Good Cause. \n%s wants to Quit Smoke and seeking your help. Help, Motivate, or Guide %s in quiting..";

	private static final String WELCOME_COMMUNITY = "%s is another bravo to start quit smoking.\n So the compitition is going bigger.";
	
	private static final String ROOM_LABEL = "%s' Room [Point : %s]";
	private SparkHandler() {
	}

	public static SparkHandler instance() {
		return sparkHandler;
	}

	// Methods to send Messages on Spark
	public void send(String message, Subject subject) {
		Message msg = new Message();
		msg.setRoomId(subject.getSparkRoom().getRoomId());
		msg.setText(message);
		spark.messages().post(msg);
	}
	
	public void sendToCommunity(String message, Subject subject) {
			Message msg = new Message();
			msg.setRoomId(subject.getCommunityMembership().getRoomId());
			msg.setText(message);
			spark.messages().post(msg);
		}

	public void sendFile(String fileUrl, Subject subject) {
		Message msg = new Message();
		msg.setRoomId(subject.getSparkRoom().getRoomId());
		msg.setFile(fileUrl);
		spark.messages().post(msg);
	}

	public void sendRoomAndCommunity(String message, Subject subject) {
		Message msg = new Message();
		msg.setRoomId(subject.getSparkRoom().getRoomId());
		msg.setText(message);
		spark.messages().post(msg);

		msg.setRoomId(subject.getCommunityMembership().getRoomId());
		spark.messages().post(msg);

	}

	// On boarding a User
	public void onboard(final Subject subject) {
		System.out.println("Adding " + subject + " to Room with VerBot and Friends");
		Room room = new Room();
		room.setTitle( String.format( ROOM_LABEL, subject.getName(), subject.getPoints()) );
		room = spark.rooms().post(room);
		System.out.println(subject.getName() + " room created.. " + room.getId());

		// Create Membership
		Membership m = new Membership();
		m.setPersonEmail(subject.getEmail());
		m.setRoomId(room.getId());
		m = spark.memberships().post(m);

		subject.setSparkRoom(m);

		if (null != subject.getConnection() && !subject.getConnection().isEmpty()) {
			for (Person person : subject.getConnection()) {
				System.out.println("Adding Friends....");
				Membership mC = new Membership();
				mC.setPersonEmail(person.getEmail());
				mC.setRoomId( room.getId() );
				spark.memberships().post( mC );
			}
		}
		
		this.send( String.format( WELCOME, subject.getName(), subject.getName(), subject.getName()), subject);
		
		// Join him to community.....
		LevelHandler lH = LevelHandler.instance();
		Room lR = lH.getRoomForLevel( subject.getLevel() );
		try{
			Membership lM = new Membership();
			lM.setRoomId( lR.getId() );
			lM.setPersonEmail( subject.getEmail() );
			lM = spark.memberships().post( lM );
			subject.setCommunityMembership( lM );
		}
		catch(Exception ex){
			ex.printStackTrace();
			Membership lM = new Membership();
			lM.setRoomId( lR.getId() );
			lM.setPersonEmail( subject.getEmail() );
			subject.setCommunityMembership( lM );
		}
		sendToCommunity(String.format( WELCOME_COMMUNITY, subject.getName(), subject.getName()), subject);
		
	}
	
	public void addToCommunityRoom(final Subject subject, final String roomId)
	{
		Membership m = new Membership();
		m.setPersonEmail( subject.getEmail() );
		m.setRoomId( roomId );
		m = spark.memberships().post( m );
		subject.setCommunityMembership( m );
	}
	
	public void updatePointsToRoomAndCommunity(final Subject subject)
	{
		Room room = new Room();
		room.setTitle( String.format( ROOM_LABEL, subject.getName(), subject.getPoints()) );
		room.setId( subject.getSparkRoom().getRoomId() );
		room = spark.rooms().path( "/" + room.getId() ).put( room );
		
		sendToCommunity( subject.getName() + " earned points. Total Points : " + subject.getPoints(), subject);
	}
	
	// Method to upgrade user to next Level
	public void upgradeLevel(final Subject subject)
	{
		System.out.println("Deleting from community....");
		Membership m = subject.getCommunityMembership();
		spark.memberships().path( "/" + m.getId() ).delete();
		
		LevelHandler lH = LevelHandler.instance();
		String roomId = lH.getRoomForLevel( lH.nextLevel( subject.getLevel())).getId();
		subject.setLevel( lH.nextLevel( subject.getLevel()) );
		addToCommunityRoom(subject, roomId);
		sendToCommunity(String.format( WELCOME_COMMUNITY, subject.getName(), subject.getName()), subject);
	}
	
	public Room createEmptyRoom(String label)
	{
		Room r = new Room();
		r.setTitle( label );
		r = spark.rooms().post( r );
		return r;
	}

}
