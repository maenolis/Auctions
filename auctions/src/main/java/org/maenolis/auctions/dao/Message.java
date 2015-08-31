package org.maenolis.auctions.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Message")
public class Message {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "message")
	private String message;

	@ManyToOne
	@JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
	private User sender;

	@ManyToOne
	@JoinColumn(name = "receiver_id", referencedColumnName = "id", nullable = false)
	private User receiver;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getMessage() {

		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(final User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;

	}

	public void setReceiver(final User receiver) {
		this.receiver = receiver;
	}

	public Message(final String message, final User sender, final User receiver) {
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
	}

	public Message() {

	}
}
