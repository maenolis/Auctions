package org.maenolis.auctions.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "AuctionUser", uniqueConstraints = @UniqueConstraint(columnNames = {
		"user_id", "auction_id" }))
public class AuctionUser {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int user_id;

	@Column(name = "auction_id")
	private int auction_id;

	public AuctionUser() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(final int user_id) {
		this.user_id = user_id;
	}

	public int getAuction_id() {
		return auction_id;
	}

	public void setAuction_id(final int auction_id) {
		this.auction_id = auction_id;
	}
}
