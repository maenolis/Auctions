package org.maenolis.auctions.services.retObj;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class AuctionRetObject.
 */
@XmlRootElement
public class AuctionRetObject {

	/** The status. */
	private String status;

	/** The id. */
	private int id;

	/** The owner id. */
	private int ownerId;

	/** The owner name. */
	private String ownerName;

	/** The product name. */
	private String productName;

	/** The categories. */
	private List<String> categories;

	/** The buy price. */
	private float buyPrice;

	/** The first bid. */
	private float firstBid;

	/** The current bid. */
	private float currentBid;

	/** The start time. */
	private String startTime;

	/** The end time. */
	private String endTime;

	/** The description. */
	private String description;

	/** The lat. */
	private float lat;

	/** The lon. */
	private float lon;

	/**
	 * Instantiates a new auction ret object.
	 *
	 * @param status
	 *            the status
	 * @param id
	 *            the id
	 * @param ownerId
	 *            the owner id
	 * @param ownerName
	 *            the owner name
	 * @param productName
	 *            the product name
	 * @param categories
	 *            the categories
	 * @param buyPrice
	 *            the buy price
	 * @param firstBid
	 *            the first bid
	 * @param currentBid
	 *            the current bid
	 * @param startTime
	 *            the start time
	 * @param endTime
	 *            the end time
	 * @param description
	 *            the description
	 * @param lat
	 *            the lat
	 * @param lon
	 *            the lon
	 */
	public AuctionRetObject(final String status, final int id,
			final int ownerId, final String ownerName,
			final String productName, final List<String> categories,
			final float buyPrice, final float firstBid, final float currentBid,
			final String startTime, final String endTime,
			final String description, final float lat, final float lon) {
		super();
		this.status = status;
		this.id = id;
		this.ownerId = ownerId;
		this.ownerName = ownerName;
		this.productName = productName;
		this.categories = categories;
		this.buyPrice = buyPrice;
		this.firstBid = firstBid;
		this.currentBid = currentBid;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.lat = lat;
		this.lon = lon;
	}

	/**
	 * Instantiates a new auction ret object.
	 */
	public AuctionRetObject() {
		super();
		this.categories = new ArrayList<String>();
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(final List<String> categories) {
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

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(final int ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(final String ownerName) {
		this.ownerName = ownerName;
	}

	public float getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(final float currentBid) {
		this.currentBid = currentBid;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(final float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(final float lon) {
		this.lon = lon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuctionRetObject [status=");
		builder.append(status);
		builder.append(", id=");
		builder.append(id);
		builder.append(", ownerId=");
		builder.append(ownerId);
		builder.append(", ownerName=");
		builder.append(ownerName);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", categories=");
		builder.append(categories);
		builder.append(", buyPrice=");
		builder.append(buyPrice);
		builder.append(", firstBid=");
		builder.append(firstBid);
		builder.append(", currentBid=");
		builder.append(currentBid);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", description=");
		builder.append(description);
		builder.append(", lat=");
		builder.append(lat);
		builder.append(", lon=");
		builder.append(lon);
		builder.append("]");
		return builder.toString();
	}

}
