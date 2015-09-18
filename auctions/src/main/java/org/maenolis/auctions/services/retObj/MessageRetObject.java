package org.maenolis.auctions.services.retObj;

import javax.xml.bind.annotation.XmlRootElement;

import org.maenolis.auctions.dao.Message;
import org.maenolis.auctions.services.literals.PropertyProvider;

/**
 * The Class MessageRetObject.
 */
@XmlRootElement
public class MessageRetObject {

	/** The status. */
	private String status;

	/** The id. */
	private int id;

	/** The sender id. */
	private int senderId;

	/** The sender name. */
	private String senderName;

	/** The receiver id. */
	private int receiverId;

	/** The receiver name. */
	private String receiverName;

	/** The message text. */
	private String messageText;

	/** The time. */
	private String time;

	/**
	 * Instantiates a new message ret object.
	 *
	 * @param status
	 *            the status
	 * @param id
	 *            the id
	 * @param senderId
	 *            the sender id
	 * @param senderName
	 *            the sender name
	 * @param receiverId
	 *            the receiver id
	 * @param receiverName
	 *            the receiver name
	 * @param messageText
	 *            the message text
	 * @param time
	 *            the time
	 */
	public MessageRetObject(final String status, final int id,
			final int senderId, final String senderName, final int receiverId,
			final String receiverName, final String messageText,
			final String time) {
		super();
		this.status = status;
		this.id = id;
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiverId = receiverId;
		this.receiverName = receiverName;
		this.messageText = messageText;
		this.time = time;
	}

	/**
	 * Instantiates a new message ret object.
	 *
	 * @param message
	 *            the message
	 */
	public MessageRetObject(final Message message) {
		this.status = PropertyProvider.OK;
		this.id = message.getId();
		this.senderId = message.getSender().getId();
		this.senderName = message.getSender().getFirstName() + " "
				+ message.getSender().getLastName();
		this.receiverId = message.getReceiver().getId();
		this.receiverName = message.getReceiver().getFirstName() + " "
				+ message.getReceiver().getLastName();
		this.messageText = message.getMessageText();
		this.time = message.getTime();
	}

	/**
	 * Instantiates a new message ret object.
	 */
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

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(final int senderId) {
		this.senderId = senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(final int receiverId) {
		this.receiverId = receiverId;
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

	public String getTime() {
		return time;
	}

	public void setTime(final String time) {
		this.time = time;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageRetObject [status=");
		builder.append(status);
		builder.append(", id=");
		builder.append(id);
		builder.append(", sender_id=");
		builder.append(senderId);
		builder.append(", senderName=");
		builder.append(senderName);
		builder.append(", receiver_id=");
		builder.append(receiverId);
		builder.append(", receiverName=");
		builder.append(receiverName);
		builder.append(", messageText=");
		builder.append(messageText);
		builder.append(", time=");
		builder.append(time);
		builder.append("]");
		return builder.toString();
	}

}
