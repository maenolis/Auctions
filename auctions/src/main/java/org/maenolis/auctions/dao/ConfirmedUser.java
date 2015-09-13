package org.maenolis.auctions.dao;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Entity(name = "ConfirmedUser")
public class ConfirmedUser extends User {

	private String sessionId;

	@OneToMany(mappedBy = "sender")
	private Set<Message> sentMessages;

	@OneToMany(mappedBy = "receiver")
	private Set<Message> receivedMessages;

	@OneToMany(mappedBy = "owner")
	private Set<Auction> ownedAuctions;

	@OneToMany(mappedBy = "bidder")
	private Set<Bid> bids;

	public ConfirmedUser(final int id, final String username,
			final String firstName, final String lastName, final String email,
			final String password, final String country, final String town,
			final String address, final String telephone,
			final String postalCode, final String taxRegistrationNumber,
			final float latitude, final float longtitude,
			final String sessionId, final Set<Message> sentMessages,
			final Set<Message> receivedMessages,
			final Set<Auction> ownedAuctions, final Set<Bid> bids) {
		super(id, username, firstName, lastName, email, password, country,
				town, address, telephone, postalCode, taxRegistrationNumber,
				latitude, longtitude);
		this.sessionId = sessionId;
		this.sentMessages = sentMessages;
		this.receivedMessages = receivedMessages;
		this.ownedAuctions = ownedAuctions;
		this.bids = bids;
	}

	public ConfirmedUser() {

	}

	public static ConfirmedUser getConfirmedUser(final int id) {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From ConfirmedUser Where id=:id";
		Query query = session.createQuery(hql).setParameter("id", id);
		ConfirmedUser retConfirmedUser = (ConfirmedUser) query.uniqueResult();

		tx.commit();
		session.close();
		factory.close();

		return retConfirmedUser;
	}

	public static ConfirmedUser getConfirmedUser(final String email) {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From ConfirmedUser Where email=:email";
		Query query = session.createQuery(hql).setParameter("email", email);
		ConfirmedUser retConfirmedUser = (ConfirmedUser) query.uniqueResult();

		tx.commit();
		session.close();
		factory.close();

		return retConfirmedUser;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

	public Set<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(final Set<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public Set<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(final Set<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public Set<Auction> getOwnedAuctions() {
		return ownedAuctions;
	}

	public void setOwnedAuctions(final Set<Auction> ownedAuctions) {
		this.ownedAuctions = ownedAuctions;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(final Set<Bid> bids) {
		this.bids = bids;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfirmedUser [username=");
		builder.append(", sessionId=");
		builder.append(sessionId);
		builder.append(", sentMessages=");
		builder.append(sentMessages);
		builder.append(", receivedMessages=");
		builder.append(receivedMessages);
		builder.append(", ownedAuctions=");
		builder.append(ownedAuctions);
		builder.append(", bids=");
		builder.append(bids);
		builder.append("]");
		return builder.toString();
	}

}
