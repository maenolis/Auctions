package org.maenolis.auctions.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "country")
	private String country;

	@Column(name = "town")
	private String town;

	@Column(name = "address")
	private String address;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "postalCode")
	private String postalCode;

	@Column(name = "taxRegistrationNumber")
	private String taxRegistrationNumber;

	@Column(name = "latitude")
	private float latitude;

	@Column(name = "longtitude")
	private float longtitude;

	@OneToMany(mappedBy = "sender")
	private Set<Message> sentMessages;

	@OneToMany(mappedBy = "receiver")
	private Set<Message> receivedMessages;

	@OneToMany(mappedBy = "owner")
	private Set<Auction> ownedAuctions;

	@OneToMany(mappedBy = "bidder")
	private Set<Bid> bids;

	public User(final int id, final String username, final String firstName,
			final String lastName, final String email, final String password,
			final String country, final String town, final String address,
			final String telephone, final String postalCode,
			final String taxRegistrationNumber, final float latitude,
			final float longtitude, final Set<Message> sentMessages,
			final Set<Message> receivedMessages,
			final Set<Auction> ownedAuctions, final Set<Bid> bids) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.country = country;
		this.town = town;
		this.address = address;
		this.telephone = telephone;
		this.postalCode = postalCode;
		this.taxRegistrationNumber = taxRegistrationNumber;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.sentMessages = sentMessages;
		this.receivedMessages = receivedMessages;
		this.ownedAuctions = ownedAuctions;
		this.bids = bids;
	}

	public User() {

	}

	public static User getUser(final String email) {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From User Where email=:email";
		Query query = session.createQuery(hql).setParameter("email", email);
		User retUser = (User) query.uniqueResult();

		tx.commit();
		session.close();
		factory.close();

		return retUser;
	}

	public static String encryptSHA256(final String key)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(key.getBytes("UTF-8"));
		byte hash[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getTown() {
		return town;
	}

	public void setTown(final String town) {
		this.town = town;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(final String telephone) {
		this.telephone = telephone;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTaxRegistrationNumber() {
		return taxRegistrationNumber;
	}

	public void setTaxRegistrationNumber(final String taxRegistrationNumber) {
		this.taxRegistrationNumber = taxRegistrationNumber;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(final float latitude) {
		this.latitude = latitude;
	}

	public float getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(final float longtitude) {
		this.longtitude = longtitude;
	}

	public Set<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(final Set<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public Set<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(final Set<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public Set<Auction> getOwnedAuctions() {
		return ownedAuctions;
	}

	public void setOwnedAuctions(final Set<Auction> ownedAuctions) {
		this.ownedAuctions = ownedAuctions;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(final Set<Bid> bids) {
		this.bids = bids;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", country=");
		builder.append(country);
		builder.append(", town=");
		builder.append(town);
		builder.append(", address=");
		builder.append(address);
		builder.append(", telephone=");
		builder.append(telephone);
		builder.append(", postalCode=");
		builder.append(postalCode);
		builder.append(", taxRegistrationNumber=");
		builder.append(taxRegistrationNumber);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", longtitude=");
		builder.append(longtitude);
		builder.append(", sentMessages=");
		builder.append(sentMessages);
		builder.append(", receivedMessages=");
		builder.append(receivedMessages);
		builder.append(", ownedAuctions=");
		builder.append(ownedAuctions);
		builder.append(", bids=");
		builder.append(bids);
		builder.append("]");
		return builder.toString();
	}

}
