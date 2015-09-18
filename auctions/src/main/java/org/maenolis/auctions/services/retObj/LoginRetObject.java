package org.maenolis.auctions.services.retObj;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class LoginRetObject.
 */
@XmlRootElement
public class LoginRetObject {

	/** The status. */
	private String status;

	/** The username. */
	private String username;

	/** The id. */
	private int id;

	/**
	 * Instantiates a new login ret object.
	 *
	 * @param status
	 *            the status
	 * @param username
	 *            the username
	 * @param id
	 *            the id
	 */
	public LoginRetObject(final String status, final String username,
			final int id) {

		this.status = status;
		this.username = username;
		this.id = id;
	}

	/**
	 * Instantiates a new login ret object.
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
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
