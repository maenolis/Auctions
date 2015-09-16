package org.maenolis.auctions.services.retObj;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LogoutRetObject {

	private String status;

	private String message;

	public LogoutRetObject() {

	}

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
