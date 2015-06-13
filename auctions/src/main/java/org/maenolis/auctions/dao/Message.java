package org.maenolis.auctions.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Message")
public class Message {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "senderUserName")
	private String senderUserName;
	
	@Column(name = "receiverUserName")
	private String receiverUserName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReceiverUserName() {
		return receiverUserName;
	}

	public void setReceiverUserName(String receiverUserName) {
		this.receiverUserName = receiverUserName;
	}
	
	public Message(String message, String senderUserName,
			String receiverUserName) {
		this.message = message;
		this.senderUserName = senderUserName;
		this.receiverUserName = receiverUserName;
	}
	
	public Message() {
		
	}
}
