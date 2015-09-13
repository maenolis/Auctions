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
	private ConfirmedUser sender;

	@ManyToOne
	@JoinColumn(name = "receiver_id", referencedColumnName = "id", nullable = false)
	private ConfirmedUser receiver;

	public Message(final int id, final String message,
			final ConfirmedUser sender, final ConfirmedUser receiver) {
		super();
		this.id = id;
		this.message = message;
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

	public ConfirmedUser getSender() {
		return sender;
	}

	public void setSender(final ConfirmedUser sender) {
		this.sender = sender;
	}

	public ConfirmedUser getReceiver() {
		return receiver;
	}

	public void setReceiver(final ConfirmedUser receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [id=");
		builder.append(id);
		builder.append(", message=");
		builder.append(message);
		builder.append(", sender=");
		builder.append(sender);
		builder.append(", receiver=");
		builder.append(receiver);
		builder.append("]");
		return builder.toString();
	}

}
