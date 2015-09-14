package org.maenolis.auctions.dao;

import java.util.Date;

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

@Entity(name = "Bid")
public class Bid {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "bidder_id", referencedColumnName = "id", nullable = false)
	private User bidder;

	@Column(name = "time")
	private Date time;

	@Column(name = "ammount")
	private float ammount;

	public Bid(final int id, final User bidder, final Date time,
			final float ammount) {
		super();
		this.id = id;
		this.bidder = bidder;
		this.time = time;
		this.ammount = ammount;
	}

	public Bid() {

	}

	public static Bid getBid(final int id) {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Bid Where id=:id";
		Query query = session.createQuery(hql).setParameter("id", id);
		Bid retBid = (Bid) query.uniqueResult();

		tx.commit();
		session.close();
		factory.close();

		return retBid;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public User getBidder() {
		return bidder;
	}

	public void setBidder(final User bidder) {
		this.bidder = bidder;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(final Date time) {
		this.time = time;
	}

	public float getAmmount() {
		return ammount;
	}

	public void setAmmount(final float ammount) {
		this.ammount = ammount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bid [id=");
		builder.append(id);
		builder.append(", bidder=");
		builder.append(bidder);
		builder.append(", time=");
		builder.append(time);
		builder.append(", ammount=");
		builder.append(ammount);
		builder.append("]");
		return builder.toString();
	}

}
