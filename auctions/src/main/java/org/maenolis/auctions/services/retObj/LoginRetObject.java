package org.maenolis.auctions.services.retObj;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginRetObject {

	private String status;

	private String username;

	private int id;

	public LoginRetObject(final String status, final String username,
			final int id) {

		this.status = status;
		this.username = username;
		this.id = id;
	}

	public LoginRetObject() {

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginRetObject [status=");
		builder.append(status);
		builder.append(", username=");
		builder.append(username);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

}