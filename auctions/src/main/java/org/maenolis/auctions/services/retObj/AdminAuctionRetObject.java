package org.maenolis.auctions.services.retObj;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.maenolis.auctions.dao.Auction;

@XmlRootElement
public class AdminAuctionRetObject extends AuctionRetObject {

	private List<BidRetObject> bids;

	private int numBids;

	public List<BidRetObject> getBids() {
		return bids;
	}

	public void setBids(final List<BidRetObject> bids) {
		this.bids = bids;
	}

	public AdminAuctionRetObject() {

	}

	public AdminAuctionRetObject(final AuctionRetObject auction) {
		super(auction);
		setBids(Auction.getBids(auction.getId()));
		setNumBids(bids.size());
	}

	public int getNumBids() {
		return numBids;
	}

	public void setNumBids(final int numBids) {
		this.numBids = numBids;
	}
}
