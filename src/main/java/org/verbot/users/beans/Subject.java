package org.verbot.users.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ciscospark.Membership;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Service
@JsonInclude( Include.NON_NULL )
public class Subject extends Person implements Serializable{
	
	public List<Person> getConnection() {
		return connection;
	}

	public void setConnection(List<Person> connection) {
		this.connection = connection;
	}

	public Person getConsulor() {
		return consulor;
	}

	public void setConsulor(Person consulor) {
		this.consulor = consulor;
	}

	

	public Date getOnboardDate() {
		return onboardDate;
	}

	public void setOnboardDate(Date onboardDate) {
		this.onboardDate = onboardDate;
	}

	public Date getLastSmokeDate() {
		return lastSmokeDate;
	}

	public void setLastSmokeDate(Date lastSmokeDate) {
		this.lastSmokeDate = lastSmokeDate;
	}

	public Date getLastCheckDate() {
		return lastCheckDate;
	}

	public void setLastCheckDate(Date lastCheckDate) {
		this.lastCheckDate = lastCheckDate;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Membership getCommunityMembership() {
		return communityMembership;
	}

	public void setCommunityMembership(Membership communityMembership) {
		this.communityMembership = communityMembership;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	private String deviceId;
	
	private Date onboardDate = new Date(System.currentTimeMillis());
	
	private Date lastSmokeDate = new Date(System.currentTimeMillis());;
	
	private Date lastCheckDate = new Date(System.currentTimeMillis());;
	
	private int points = 0;
	
	private Membership communityMembership;
	
	private Level level = Level.LEVEL0;
	
	private List<Person> connection;
	
	private Person consulor;

	@Override
	public String toString() {
		return "Subject [onboardDate=" + onboardDate + ", lastSmokeDate=" + lastSmokeDate + ", lastCheckDate="
				+ lastCheckDate + ", points=" + points + ", communityMembership=" + communityMembership + ", level="
				+ level + ", connection=" + connection + ", consulor=" + consulor + ", toString()=" + super.toString()
				+ "]";
	}
	
	

}
