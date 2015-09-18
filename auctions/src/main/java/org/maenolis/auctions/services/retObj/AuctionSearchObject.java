package org.maenolis.auctions.services.retObj;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuctionSearchObject {

	private String productName;

	private String categories;

	private float buyPriceLow;

	private float buyPriceHigh;

	private float firstBidLow;

	private float firstBidHigh;

	private String description;

	private String town;

	private String country;

	public AuctionSearchObject(final String productName,
			final String categories, final float buyPriceLow,
			final float buyPriceHigh, final float firstBidLow,
			final float firstBidHigh, final String description,
			final String town, final String country) {
		super();
		this.productName = productName;
		this.categories = categories;
		this.buyPriceLow = buyPriceLow;
		this.buyPriceHigh = buyPriceHigh;
		this.firstBidLow = firstBidLow;
		this.firstBidHigh = firstBidHigh;
		this.description = description;
		this.town = town;
		this.country = country;
	}

	public AuctionSearchObject() {
		super();
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

	public float getBuyPriceLow() {
		return buyPriceLow;
	}

	public void setBuyPriceLow(final float buyPriceLow) {
		this.buyPriceLow = buyPriceLow;
	}

	public float getBuyPriceHigh() {
		return buyPriceHigh;
	}

	public void setBuyPriceHigh(final float buyPriceHigh) {
		this.buyPriceHigh = buyPriceHigh;
	}

	public float getFirstBidLow() {
		return firstBidLow;
	}

	public void setFirstBidLow(final float firstBidLow) {
		this.firstBidLow = firstBidLow;
	}

	public float getFirstBidHigh() {
		return firstBidHigh;
	}

	public void setFirstBidHigh(final float firstBidHigh) {
		this.firstBidHigh = firstBidHigh;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getTown() {
		return town;
	}

	public void setTown(final String town) {
		this.town = town;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuctionSearchObject [productName=");
		builder.append(productName);
		builder.append(", categories=");
		builder.append(categories);
		builder.append(", buyPriceLow=");
		builder.append(buyPriceLow);
		builder.append(", buyPriceHigh=");
		builder.append(buyPriceHigh);
		builder.append(", firstBidLow=");
		builder.append(firstBidLow);
		builder.append(", firstBidHigh=");
		builder.append(firstBidHigh);
		builder.append(", description=");
		builder.append(description);
		builder.append(", town=");
		builder.append(town);
		builder.append(", country=");
		builder.append(country);
		builder.append("]");
		return builder.toString();
	}

}
