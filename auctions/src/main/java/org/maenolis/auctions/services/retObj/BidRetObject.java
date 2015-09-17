package org.maenolis.auctions.services.retObj;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BidRetObject {

	private String status;

	private int id;

	private int bidder_id;

	private String bidderName;

	private int auction_id;

	private Date time;

	private float ammount;

	public BidRetObject(final String status, final int id, final int bidder_id,
			final String bidderName, final int auction_id, final Date time,
			final float ammount) {
		super();
		this.status = status;
		this.id = id;
		this.bidder_id = bidder_id;
		this.bidderName = bidderName;
		this.auction_id = auction_id;
		this.time = time;
		this.ammount = ammount;
	}

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
		return bidder_id;
	}

	public void setBidder_id(final int bidder_id) {
		this.bidder_id = bidder_id;
	}

	public int getAuction_id() {
		return auction_id;
	}

	public void setAuction_id(final int auction_id) {
		this.auction_id = auction_id;
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

	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(final String bidderName) {
		this.bidderName = bidderName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BidRetObject [status=");
		builder.append(status);
		builder.append(", id=");
		builder.append(id);
		builder.append(", bidder_id=");
		builder.append(bidder_id);
		builder.append(", bidderName=");
		builder.append(bidderName);
		builder.append(", auction_id=");
		builder.append(auction_id);
		builder.append(", time=");
		builder.append(time);
		builder.append(", ammount=");
		builder.append(ammount);
		builder.append("]");
		return builder.toString();
	}

}
