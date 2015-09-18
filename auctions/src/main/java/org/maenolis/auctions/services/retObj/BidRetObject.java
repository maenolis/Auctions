package org.maenolis.auctions.services.retObj;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class BidRetObject.
 */
@XmlRootElement
public class BidRetObject {

	/** The status. */
	private String status;

	/** The id. */
	private int id;

	/** The bidder id. */
	private int bidderId;

	/** The bidder name. */
	private String bidderName;

	/** The auction id. */
	private int auctionId;

	/** The time. */
	private String time;

	/** The ammount. */
	private float ammount;

	/**
	 * Instantiates a new bid ret object.
	 *
	 * @param status
	 *            the status
	 * @param id
	 *            the id
	 * @param bidder_id
	 *            the bidder_id
	 * @param bidderName
	 *            the bidder name
	 * @param auction_id
	 *            the auction_id
	 * @param time
	 *            the time
	 * @param ammount
	 *            the ammount
	 */
	public BidRetObject(final String status, final int id, final int bidder_id,
			final String bidderName, final int auction_id, final String time,
			final float ammount) {
		super();
		this.status = status;
		this.id = id;
		this.bidderId = bidder_id;
		this.bidderName = bidderName;
		this.auctionId = auction_id;
		this.time = time;
		this.ammount = ammount;
	}

	/**
	 * Instantiates a new bid ret object.
	 */
	public BidRetObject() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getBidder_id() {
		return bidderId;
	}

	public void setBidder_id(final int bidder_id) {
		this.bidderId = bidder_id;
	}

	public int getAuction_id() {
		return auctionId;
	}

	public void setAuction_id(final int auction_id) {
		this.auctionId = auction_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(final String time) {
		this.time = time;
	}

	public float getAmmount() {
		return ammount;
	}

	public void setAmmount(final float ammount) {
		this.ammount = ammount;
	}

	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(final String bidderName) {
		this.bidderName = bidderName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BidRetObject [status=");
		builder.append(status);
		builder.append(", id=");
		builder.append(id);
		builder.append(", bidder_id=");
		builder.append(bidderId);
		builder.append(", bidderName=");
		builder.append(bidderName);
		builder.append(", auction_id=");
		builder.append(auctionId);
		builder.append(", time=");
		builder.append(time);
		builder.append(", ammount=");
		builder.append(ammount);
		builder.append("]");
		return builder.toString();
	}

}
