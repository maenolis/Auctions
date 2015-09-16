package org.maenolis.auctions.services.retObj;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageRetObject {

	private String status;

	private int id;

	private int sender_id;

	private int receiver_id;

	private String messageText;

	public MessageRetObject(final String status, final int sender_id,
			final int receiver_id, final String messageText, final int id) {
		super();
		this.status = status;
		this.sender_id = sender_id;
		this.receiver_id = receiver_id;
		this.messageText = messageText;
		this.id = id;
	}

	public MessageRetObject() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public int getSender_id() {
		return sender_id;
	}

	public void setSender_id(final int sender_id) {
		this.sender_id = sender_id;
	}

	public int getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(final int receiver_id) {
		this.receiver_id = receiver_id;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(final String messageText) {
		this.messageText = messageText;
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
		builder.append("MessageRetObject [status=");
		builder.append(status);
		builder.append(", id=");
		builder.append(id);
		builder.append(", sender_id=");
		builder.append(sender_id);
		builder.append(", receiver_id=");
		builder.append(receiver_id);
		builder.append(", messageText=");
		builder.append(messageText);
		builder.append("]");
		return builder.toString();
	}

}
