package org.maenolis.auctions.dao;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Entity(name = "Auction")
public class Auction {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "productName")
	private String productName;

	@Column(name = "Categories")
	private String categories;

	@Column(name = "buyPrice")
	private float buyPrice;

	@Column(name = "firstBid")
	private float firstBid;

	@Column(name = "startTime")
	private String startTime;

	@Column(name = "endTime")
	private String endTime;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "owner", referencedColumnName = "id", nullable = false)
	private User owner;

	@OneToMany(mappedBy = "auction")
	private Set<Bid> bids;

	public Auction(final int id, final String productName,
			final String categories, final float buyPrice,
			final float firstBid, final String startTime, final String endTime,
			final String description, final User owner) {
		super();
		this.id = id;
		this.productName = productName;
		this.categories = categories;
		this.buyPrice = buyPrice;
		this.firstBid = firstBid;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.owner = owner;
	}

	public Auction() {

	}

	public static Auction getAuction(final int id) {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Auction Where id=:id";
		Query query = session.createQuery(hql).setParameter("id", id);
		Auction retAuction = (Auction) query.uniqueResult();

		tx.commit();
		session.close();
		factory.close();

		return retAuction;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(final String categories) {
		this.categories = categories;
	}

	public float getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(final float buyPrice) {
		this.buyPrice = buyPrice;
	}

	public float getFirstBid() {
		return firstBid;
	}

	public void setFirstBid(final float firstBid) {
		this.firstBid = firstBid;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(final String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(final String endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(final User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Auction [id=");
		builder.append(id);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", categories=");
		builder.append(categories);
		builder.append(", buyPrice=");
		builder.append(buyPrice);
		builder.append(", firstBid=");
		builder.append(firstBid);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", description=");
		builder.append(description);
		builder.append(", owner=");
		builder.append(owner);
		builder.append("]");
		return builder.toString();
	}

}
