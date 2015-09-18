package org.maenolis.auctions.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.maenolis.auctions.services.literals.PropertyProvider;
import org.maenolis.auctions.services.retObj.AuctionRetObject;
import org.maenolis.auctions.services.retObj.AuctionSearchObject;
import org.maenolis.auctions.services.retObj.BidRetObject;

/**
 * The Class Auction.
 */
@Entity(name = "Auction")
public class Auction {

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	/** The alive. */
	@Column(name = "alive")
	private boolean alive;

	/** The product name. */
	@Column(name = "productName")
	private String productName;

	/** The categories. */
	@Column(name = "Categories")
	private String categories;

	/** The buy price. */
	@Column(name = "buyPrice")
	private float buyPrice;

	/** The first bid. */
	@Column(name = "firstBid")
	private float firstBid;

	/** The start time. */
	@Column(name = "startTime")
	private String startTime;

	/** The end time. */
	@Column(name = "endTime")
	private String endTime;

	/** The description. */
	@Column(name = "description")
	private String description;

	/** The owner. */
	@ManyToOne
	@JoinColumn(name = "owner", referencedColumnName = "id", nullable = false)
	private User owner;

	/** The bids. */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "auction")
	private Set<Bid> bids;

	/**
	 * Instantiates a new auction.
	 *
	 * @param id
	 *            the id
	 * @param productName
	 *            the product name
	 * @param categories
	 *            the categories
	 * @param buyPrice
	 *            the buy price
	 * @param firstBid
	 *            the first bid
	 * @param startTime
	 *            the start time
	 * @param endTime
	 *            the end time
	 * @param description
	 *            the description
	 * @param owner
	 *            the owner
	 */
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
		this.alive = true;
	}

	/**
	 * Instantiates a new auction.
	 */
	public Auction() {

	}

	/**
	 * Instantiates a new auction.
	 *
	 * @param auction
	 *            the auction
	 */
	public Auction(final AuctionRetObject auction) {
		if (auction != null) {
			this.id = auction.getId();
			this.productName = auction.getProductName();
			for (int i = 0; i < auction.getCategories().size(); i++) {
				this.categories += auction.getCategories().get(i);
				if (i < auction.getCategories().size() - 1) {
					this.categories += ", ";
				}
			}
			this.buyPrice = auction.getBuyPrice();
			this.firstBid = auction.getFirstBid();
			this.startTime = auction.getStartTime();
			this.endTime = auction.getEndTime();
			this.description = auction.getDescription();
			this.owner = User.getUser(auction.getOwnerId());
			this.alive = true;
		}
	}

	/**
	 * Gets the auction.
	 *
	 * @param id
	 *            the id
	 * @return the auction
	 */
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

	/**
	 * Transform to ret object.
	 *
	 * @param auction
	 *            the auction
	 * @return the auction ret object
	 */
	public static AuctionRetObject transformToRetObject(final Auction auction) {
		AuctionRetObject ret = new AuctionRetObject();
		if (auction != null) {
			ret.setBuyPrice(auction.getBuyPrice());
			ret.setCategories(Arrays.asList(auction.getCategories().split(",")));
			ret.setDescription(auction.getDescription());
			ret.setEndTime(auction.getEndTime());
			ret.setFirstBid(auction.getFirstBid());
			ret.setId(auction.getId());
			ret.setOwnerId(auction.getOwner().getId());
			ret.setOwnerName(auction.getOwner().getFirstName() + " "
					+ auction.getOwner().getLastName());
			ret.setProductName(auction.getProductName());
			ret.setStartTime(auction.getStartTime());
			ret.setLat(auction.getOwner().getLatitude());
			ret.setLon(auction.getOwner().getLongtitude());
			ret.setStatus(PropertyProvider.OK);
			if (auction.getCurrentBid() != null) {
				ret.setCurrentBid(auction.getCurrentBid().getAmmount());
			}
		}
		return ret;
	}

	/**
	 * Gets the all auctions.
	 *
	 * @return the all auctions
	 */
	public static List<AuctionRetObject> getAllAuctions() {
		List<AuctionRetObject> retList = new ArrayList<AuctionRetObject>();

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();

		String hql = "From Auction";
		Query query = session.createQuery(hql);

		if (query.list() != null) {
			for (Object obj : query.list()) {
				retList.add(transformToRetObject((Auction) obj));
			}
		}

		session.close();
		factory.close();

		return retList;
	}

	/**
	 * Gets the bids.
	 *
	 * @param id
	 *            the id
	 * @return the bids
	 */
	public static List<BidRetObject> getBids(final int id) {
		Auction auction = Auction.getAuction(id);
		List<BidRetObject> retList = new ArrayList<BidRetObject>();
		if (auction.getBids() != null) {
			for (Bid bid : auction.getBids()) {
				retList.add(Bid.transformToRetObject(bid));
			}
		}

		return retList;
	}

	/**
	 * Search auctions.
	 *
	 * @param search
	 *            the search
	 * @return the list
	 */
	public static List<AuctionRetObject> searchAuctions(
			final AuctionSearchObject search) {

		List<AuctionRetObject> retList = new ArrayList<AuctionRetObject>();
		if (search == null) {
			return retList;
		}

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Auction ";

		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Auction> retAuctions = query.list();
		tx.commit();
		session.close();
		factory.close();

		if (retAuctions == null) {
			return retList;
		}

		for (Auction auction : retAuctions) {
			boolean metCriteria = true;
			if (search.getBuyPriceHigh() != -1.0f
					&& search.getBuyPriceLow() != -1.0f) {
				metCriteria = auction.getBuyPrice() < search.getBuyPriceHigh()
						&& auction.getBuyPrice() > search.getBuyPriceLow();
			}
			if (metCriteria && search.getCategories() != null) {
				String[] auctionCategories = auction.getCategories().split(",");
				String[] searchCategories = search.getCategories().split(",");
				for (String searchCategory : searchCategories) {
					boolean contain = false;
					for (String auctionCategory : auctionCategories) {
						if (auctionCategory.equalsIgnoreCase(searchCategory)) {
							contain = true;
							break;
						}
					}
					if (!contain) {
						metCriteria = false;
						break;
					}
				}
			}
			if (metCriteria && search.getCountry() != null) {
				metCriteria = auction.getOwner().getCountry()
						.contains(search.getCountry());
			}
			if (metCriteria && search.getDescription() != null) {
				metCriteria = auction.getDescription().contains(
						search.getDescription());
			}
			if (metCriteria && search.getFirstBidHigh() != -1.0f
					&& search.getFirstBidLow() != -1.0f) {
				metCriteria = auction.getFirstBid() < search.getFirstBidHigh()
						&& auction.getFirstBid() > search.getFirstBidLow();
			}
			if (metCriteria && search.getProductName() != null) {
				metCriteria = auction.getProductName().contains(
						search.getProductName());
			}
			if (metCriteria && search.getTown() != null) {
				metCriteria = auction.getOwner().getTown()
						.contains(search.getTown());
			}
			if (metCriteria) {
				retList.add(Auction.transformToRetObject(auction));
			}
		}

		return retList;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the product name.
	 *
	 * @param productName
	 *            the new product name
	 */
	public void setProductName(final String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the categories.
	 *
	 * @return the categories
	 */
	public String getCategories() {
		return categories;
	}

	/**
	 * Sets the categories.
	 *
	 * @param categories
	 *            the new categories
	 */
	public void setCategories(final String categories) {
		this.categories = categories;
	}

	/**
	 * Gets the buy price.
	 *
	 * @return the buy price
	 */
	public float getBuyPrice() {
		return buyPrice;
	}

	/**
	 * Sets the buy price.
	 *
	 * @param buyPrice
	 *            the new buy price
	 */
	public void setBuyPrice(final float buyPrice) {
		this.buyPrice = buyPrice;
	}

	/**
	 * Gets the first bid.
	 *
	 * @return the first bid
	 */
	public float getFirstBid() {
		return firstBid;
	}

	/**
	 * Sets the first bid.
	 *
	 * @param firstBid
	 *            the new first bid
	 */
	public void setFirstBid(final float firstBid) {
		this.firstBid = firstBid;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime
	 *            the new start time
	 */
	public void setStartTime(final String startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime
	 *            the new end time
	 */
	public void setEndTime(final String endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * Sets the owner.
	 *
	 * @param owner
	 *            the new owner
	 */
	public void setOwner(final User owner) {
		this.owner = owner;
	}

	/**
	 * Gets the bids.
	 *
	 * @return the bids
	 */
	public Set<Bid> getBids() {
		return bids;
	}

	/**
	 * Sets the bids.
	 *
	 * @param bids
	 *            the new bids
	 */
	public void setBids(final Set<Bid> bids) {
		this.bids = bids;
	}

	/**
	 * Checks if is alive.
	 *
	 * @return true, if is alive
	 */
	public boolean isAlive() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(PropertyProvider.DATEFORMAT);
		boolean isPassed = true;
		try {
			isPassed = sdf.parse(getEndTime()).after(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return alive && isPassed;
	}

	/**
	 * Sets the alive.
	 *
	 * @param alive
	 *            the new alive
	 */
	public void setAlive(final boolean alive) {
		this.alive = alive;
	}

	/**
	 * Gets the current bid.
	 *
	 * @return the current bid
	 */
	public Bid getCurrentBid() {
		if (getBids() != null && getBids().toArray().length > 0) {
			Arrays.sort(getBids().toArray());
			return (Bid) getBids().toArray()[getBids().toArray().length - 1];
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
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
		builder.append("]");
		return builder.toString();
	}

}
