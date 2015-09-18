package org.maenolis.auctions.services.retObj;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class LogoutRetObject.
 */
@XmlRootElement
public class LogoutRetObject {

	/** The status. */
	private String status;

	/** The message. */
	private String message;

	/**
	 * Instantiates a new logout ret object.
	 */
	public LogoutRetObject() {

	}

	/**
	 * Instantiates a new logout ret object.
	 *
	 * @param status
	 *            the status
	 * @param message
	 *            the message
	 */
	public LogoutRetObject(final String status, final String message) {

		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LogoutRetObject [status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
}
