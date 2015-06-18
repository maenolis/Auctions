package org.maenolis.auctions.dao;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConfirmedUser {

	private String username;
	
	private String sessionId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public ConfirmedUser(String username, String sessionId) {
		this.username = username;
		this.sessionId = sessionId;
	}

	public ConfirmedUser() {
		
	}

	@Override
	public String toString() {
		return "ConfirmedUser [username=" + username + ", sessionId="
				+ sessionId + "]";
	}
}
