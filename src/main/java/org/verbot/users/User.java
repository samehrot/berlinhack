package org.verbot.users;

import java.util.ArrayList;
import java.util.List;

public class User {

	private long id;
	
	private String username;
	
	private String address;
	
	private String email;
	
	private String deviceID;
	
	private String f1name;
	
	private String f1email;
	
	private String f1phone;
	
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public void setF1name(String f1name) {
		this.f1name = f1name;
	}

	public void setF1email(String f1email) {
		this.f1email = f1email;
	}

	public void setF1phone(String f1phone) {
		this.f1phone = f1phone;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public User(){
		id=0;
	}
	
	public User(long id, String username, String address, String email, String deviceID, String f1name, String f1email, String f1phone){
		this.id = id;
		this.username = username;
		this.address = address;
		this.email = email;
		this.deviceID = deviceID;
		
		this.f1name = f1name;
		this.f1email = f1email;
		this.f1phone = f1phone;
		
	}

	
	public User(long id, String username, String address, String email, String deviceID){
		this.id = id;
		this.username = username;
		this.address = address;
		this.email = email;
		this.deviceID = deviceID;
		
		this.f1name = f1name;
		this.f1email = f1email;
		this.f1phone = f1phone;
		
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", address=" + address
				+ ", email=" + email + "]";
	}
	

	
}