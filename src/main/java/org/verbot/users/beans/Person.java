package org.verbot.users.beans;

import java.io.Serializable;

import com.ciscospark.Membership;

public class Person implements Serializable{

	@Override
	public String toString() {
		return "Person [name=" + name + ", email=" + email + ", phone=" + phone + ", sparkRoom=" + sparkRoom + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Membership getSparkRoom() {
		return sparkRoom;
	}
	public void setSparkRoom(Membership sparkRoom) {
		this.sparkRoom = sparkRoom;
	}
	
	
	
	private String name;
	private String email;
	private String phone;
	private Membership sparkRoom;
}
