package org.maenolis.auctions.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.maenolis.auctions.services.literals.PropertyProvider;
import org.maenolis.auctions.services.retObj.MessageRetObject;

@Entity(name = "Message")
public class Message {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "messageText")
	private String messageText;

	@Column(name = "time")
	private String time;

	@ManyToOne
	@JoinColumn(name = "senderId", referencedColumnName = "id", nullable = false)
	private User sender;

	@ManyToOne
	@JoinColumn(name = "receiverId", referencedColumnName = "id", nullable = false)
	private User receiver;

	public Message(final int id, final String messageText, final String time,
			final User sender, final User receiver) {
		super();
		this.id = id;
		this.messageText = messageText;
		this.time = time;
		this.sender = sender;
		this.receiver = receiver;
	}

	public Message(final MessageRetObject message) {
		if (message != null) {
			this.messageText = message.getMessageText();
			this.receiver = User.getUser(message.getReceiverId());
			this.sender = User.getUser(message.getSenderId());
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			this.time = sdf.format(cal.getTime());
		}
	}

	public Message() {

	}

	public static Message getMessage(final int id) {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Message Where id=:id";
		Query query = session.createQuery(hql).setParameter("id", id);
		Message retMessage = (Message) query.uniqueResult();

		tx.commit();
		session.close();
		factory.close();

		return retMessage;
	}

	public static MessageRetObject transformToRetObject(final Message message) {
		MessageRetObject ret = new MessageRetObject();
		if (message != null) {
			ret.setId(message.getId());
			ret.setMessageText(message.getMessageText());
			ret.setReceiverId(message.getReceiver().getId());
			ret.setReceiverName(message.getReceiver().getFirstName() + " "
					+ message.getReceiver().getLastName());
			ret.setSenderId(message.getSender().getId());
			ret.setSenderName(message.getSender().getFirstName() + " "
					+ message.getSender().getLastName());
			ret.setStatus(PropertyProvider.OK);
			ret.setTime(message.getTime());
		}
		return ret;

	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(final String messageText) {
		this.messageText = messageText;
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

	public String getTime() {
		return time;
	}

	public void setTime(final String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [id=");
		builder.append(id);
		builder.append(", messageText=");
		builder.append(messageText);
		builder.append(", time=");
		builder.append(time);
		builder.append(", sender=");
		builder.append(sender);
		builder.append(", receiver=");
		builder.append(receiver);
		builder.append("]");
		return builder.toString();
	}

}
