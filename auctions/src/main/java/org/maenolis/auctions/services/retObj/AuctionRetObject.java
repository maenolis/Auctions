package org.maenolis.auctions.services.retObj;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuctionRetObject {

	private String status;

	private int id;

	private String productName;

	private ArrayList<String> categories;

	private float buyPrice;

	private float firstBid;

	private String startTime;

	private String endTime;

	private String description;

	public AuctionRetObject(final String status, final int id,
			final String productName, final ArrayList<String> categories,
			final float buyPrice, final float firstBid, final String startTime,
			final String endTime, final String description) {
		super();
		this.status = status;
		this.id = id;
		this.productName = productName;
		this.categories = categories;
		this.buyPrice = buyPrice;
		this.firstBid = firstBid;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	}

	public AuctionRetObject() {
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(final ArrayList<String> categories) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuctionRetObject [status=");
		builder.append(status);
		builder.append(", id=");
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
