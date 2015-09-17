package org.maenolis.auctions.dao;

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

	@ManyToOne
	@JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
	private User sender;

	@ManyToOne
	@JoinColumn(name = "receiver_id", referencedColumnName = "id", nullable = false)
	private User receiver;

	public Message(final int id, final String messageText, final User sender,
			final User receiver) {
		super();
		this.id = id;
		this.messageText = messageText;
		this.sender = sender;
		this.receiver = receiver;
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
		ret.setId(message.getId());
		ret.setMessageText(message.getMessageText());
		ret.setReceiver_id(message.getReceiver().getId());
		ret.setReceiverName(message.getReceiver().getFirstName() + " "
				+ message.getReceiver().getLastName());
		ret.setSender_id(message.getSender().getId());
		ret.setSenderName(message.getSender().getFirstName() + " "
				+ message.getSender().getLastName());
		ret.setStatus(PropertyProvider.OK);
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [id=");
		builder.append(id);
		builder.append(", messageText=");
		builder.append(messageText);
		builder.append("]");
		return builder.toString();
	}

}
