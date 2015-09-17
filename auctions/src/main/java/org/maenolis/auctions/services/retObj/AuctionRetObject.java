package org.maenolis.auctions.services.retObj;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuctionRetObject {

	private String status;

	private int id;

	private int ownerId;

	private String ownerName;

	private String productName;

	private List<String> categories;

	private float buyPrice;

	private float firstBid;

	private float currentBid;

	private String startTime;

	private String endTime;

	private String description;

	public AuctionRetObject(final String status, final int id,
			final int ownerId, final String ownerName,
			final String productName, final List<String> categories,
			final float buyPrice, final float firstBid, final float currentBid,
			final String startTime, final String endTime,
			final String description) {
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
	}

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
		builder.append("]");
		return builder.toString();
	}

}
