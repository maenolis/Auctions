package org.maenolis.auctions.dao;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Auction")
public class Auction {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "productName")
	private String productName;
	
	@Column(name = "Categories")
	private ArrayList<String> categories;
	
	@Column(name = "buyPrice")
	private float buyPrice;
	
	@Column(name = "firstBid")
	private float firstBid;
	
	@Column(name = "startTimeDate")
	private Date startTimeDate;
	
	@Column(name = "endTimeDate")
	private Date endTimeDate;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "longitude")
	private String longitude;
	
	//TODO
	//private Location location;
}
