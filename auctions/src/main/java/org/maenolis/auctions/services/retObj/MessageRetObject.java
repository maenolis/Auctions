package org.maenolis.auctions.services.retObj;

import javax.xml.bind.annotation.XmlRootElement;

import org.maenolis.auctions.services.literals.PropertyProvider;

@XmlRootElement
public class MessageRetObject {

	private String status;

	private int id;

	private int sender_id;

	private String senderName;

	private int receiver_id;

	private String receiverName;

	private String messageText;

	public MessageRetObject(final String status, final int id,
			final int sender_id, final String senderName,
			final int receiver_id, final String receiverName,
			final String messageText) {
		super();
		this.status = status;
		this.id = id;
		this.sender_id = sender_id;
		this.senderName = senderName;
		this.receiver_id = receiver_id;
		this.receiverName = receiverName;
		this.messageText = messageText;
	}

	public MessageRetObject() {
		super();
		this.status = PropertyProvider.NOK;
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

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(final String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(final String receiverName) {
		this.receiverName = receiverName;
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
		builder.append(", senderName=");
		builder.append(senderName);
		builder.append(", receiver_id=");
		builder.append(receiver_id);
		builder.append(", receiverName=");
		builder.append(receiverName);
		builder.append(", messageText=");
		builder.append(messageText);
		builder.append("]");
		return builder.toString();
	}

}
