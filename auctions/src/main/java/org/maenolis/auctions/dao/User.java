package org.maenolis.auctions.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.maenolis.auctions.services.literals.PropertyProvider;
import org.maenolis.auctions.services.retObj.MessageRetObject;
import org.maenolis.auctions.services.retObj.UserRetObject;

/**
 * The Class User.
 */
@Entity
@Table(name = "User")
public class User {

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	/** The username. */
	@Column(name = "username", unique = true)
	private String username;

	/** The first name. */
	@Column(name = "firstName")
	private String firstName;

	/** The last name. */
	@Column(name = "lastName")
	private String lastName;

	/** The email. */
	@Column(name = "email")
	private String email;

	/** The password. */
	@Column(name = "password")
	private String password;

	/** The country. */
	@Column(name = "country")
	private String country;

	/** The town. */
	@Column(name = "town")
	private String town;

	/** The address. */
	@Column(name = "address")
	private String address;

	/** The telephone. */
	@Column(name = "telephone")
	private String telephone;

	/** The postal code. */
	@Column(name = "postalCode")
	private String postalCode;

	/** The tax registration number. */
	@Column(name = "taxRegistrationNumber")
	private String taxRegistrationNumber;

	/** The latitude. */
	@Column(name = "latitude")
	private float latitude;

	/** The longtitude. */
	@Column(name = "longtitude")
	private float longtitude;

	/** The confirmed. */
	@Column(name = "confirmed")
	private boolean confirmed = false;

	/** The sent messages. */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sender")
	private Set<Message> sentMessages;

	/** The received messages. */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "receiver")
	private Set<Message> receivedMessages;

	/** The owned auctions. */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
	private Set<Auction> ownedAuctions;

	/** The bids. */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "bidder")
	private Set<Bid> bids;

	/**
	 * Instantiates a new user.
	 *
	 * @param id
	 *            the id
	 * @param username
	 *            the username
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @param country
	 *            the country
	 * @param town
	 *            the town
	 * @param address
	 *            the address
	 * @param telephone
	 *            the telephone
	 * @param postalCode
	 *            the postal code
	 * @param taxRegistrationNumber
	 *            the tax registration number
	 * @param latitude
	 *            the latitude
	 * @param longtitude
	 *            the longtitude
	 * @param confirmed
	 *            the confirmed
	 * @param sentMessages
	 *            the sent messages
	 * @param receivedMessages
	 *            the received messages
	 * @param ownedAuctions
	 *            the owned auctions
	 * @param bids
	 *            the bids
	 */
	public User(final int id, final String username, final String firstName,
			final String lastName, final String email, final String password,
			final String country, final String town, final String address,
			final String telephone, final String postalCode,
			final String taxRegistrationNumber, final float latitude,
			final float longtitude, final boolean confirmed,
			final Set<Message> sentMessages,
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
		this.confirmed = confirmed;
		this.sentMessages = sentMessages;
		this.receivedMessages = receivedMessages;
		this.ownedAuctions = ownedAuctions;
		this.bids = bids;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param user
	 *            the user
	 */
	public User(final UserRetObject user) {

		if (user != null) {
			this.id = user.getId();
			this.username = user.getUsername();
			this.firstName = user.getFirstName();
			this.lastName = user.getLastName();
			this.email = user.getEmail();
			this.country = user.getCountry();
			this.town = user.getTown();
			this.address = user.getAddress();
			this.telephone = user.getTelephone();
			this.postalCode = user.getPostalCode();
			this.taxRegistrationNumber = user.getTaxRegistrationNumber();
			this.latitude = user.getLatitude();
			this.longtitude = user.getLongtitude();
		}

	}

	/**
	 * Instantiates a new user.
	 */
	public User() {

	}

	/**
	 * Gets the user.
	 *
	 * @param email
	 *            the email
	 * @return the user
	 */
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

	/**
	 * Gets the user.
	 *
	 * @param id
	 *            the id
	 * @return the user
	 */
	public static User getUser(final int id) {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From User Where id=:id";
		Query query = session.createQuery(hql).setParameter("id", id);
		User retUser = (User) query.uniqueResult();

		tx.commit();
		session.close();
		factory.close();

		return retUser;
	}

	/**
	 * Gets the user sent messages.
	 *
	 * @param userid
	 *            the userid
	 * @return the user sent messages
	 */
	public static List<Message> getUserSentMessages(final int userid) {
		User user = getUser(userid);
		List<Message> retList = new ArrayList<Message>();
		if (user.getSentMessages() == null) {
			return retList;
		}
		for (Object messageObj : user.getSentMessages().toArray()) {
			retList.add((Message) messageObj);
		}
		return retList;

	}

	/**
	 * Gets the user received messages.
	 *
	 * @param userid
	 *            the userid
	 * @return the user received messages
	 */
	public static List<MessageRetObject> getUserReceivedMessages(
			final int userid) {
		User user = getUser(userid);
		List<MessageRetObject> retList = new ArrayList<MessageRetObject>();
		if (user.getReceivedMessages() == null) {
			return retList;
		}
		for (Object messageObj : user.getReceivedMessages().toArray()) {
			retList.add(new MessageRetObject((Message) messageObj));
		}
		return retList;
	}

	/**
	 * Gets the user received messages from.
	 *
	 * @param senderId
	 *            the sender id
	 * @param receiverId
	 *            the receiver id
	 * @return the user received messages from
	 */
	public static List<MessageRetObject> getUserReceivedMessagesFrom(
			final int senderId, final int receiverId) {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Message Where senderId=:senderId and receiverId=:receiverId";
		Query query = session.createQuery(hql)
				.setParameter("senderId", senderId)
				.setParameter("receiverId", receiverId);

		@SuppressWarnings("unchecked")
		List<Message> retMessages = query.list();
		tx.commit();
		session.close();
		factory.close();

		List<MessageRetObject> retList = new ArrayList<MessageRetObject>();
		if (retMessages == null) {
			return retList;
		}
		for (Message message : retMessages) {
			retList.add(new MessageRetObject(message));
		}
		return retList;

	}

	/**
	 * Gets the user auctions.
	 *
	 * @param userid
	 *            the userid
	 * @return the user auctions
	 */
	public static List<Auction> getUserAuctions(final int userid) {
		User user = getUser(userid);
		List<Auction> retList = new ArrayList<Auction>();
		if (user.getOwnedAuctions() == null) {
			return retList;
		}
		for (Object auctionObj : user.getOwnedAuctions().toArray()) {
			retList.add((Auction) auctionObj);
		}
		return retList;
	}

	public static List<UserRetObject> getAllUsers() {
		List<UserRetObject> retList = new ArrayList<UserRetObject>();

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();

		String hql = "From User";
		Query query = session.createQuery(hql);

		if (query.list() == null) {
			return retList;
		}

		for (Object obj : query.list()) {
			retList.add(transformToRetObject((User) obj));
		}

		// System.out.println(retList.get(0));

		session.close();
		factory.close();

		return retList;
	}

	/**
	 * Confirm.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	public static String confirm(final int id) {

		String ret = PropertyProvider.OK;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			Session session = factory.openSession();
			Transaction tx;
			tx = session.beginTransaction();

			String hql = "From User Where id=:id";
			Query query = session.createQuery(hql).setParameter("id", id);
			User retUser = (User) query.uniqueResult();

			retUser.setConfirmed(true);
			session.save(retUser);
			tx.commit();
			session.close();
			factory.close();
		} catch (Exception e) {
			System.err.println("Error while retrieving User with id : " + id);
			ret = PropertyProvider.NOK;
		}
		return ret;
	}

	/**
	 * Transform to ret object.
	 *
	 * @param user
	 *            the user
	 * @return the user ret object
	 */
	public static UserRetObject transformToRetObject(final User user) {
		UserRetObject ret = new UserRetObject();
		if (user != null) {
			ret.setAddress(user.getAddress());
			ret.setConfirmed(user.isConfirmed());
			ret.setCountry(user.getCountry());
			ret.setEmail(user.getEmail());
			ret.setFirstName(user.getFirstName());
			ret.setId(user.getId());
			ret.setLastName(user.getLastName());
			ret.setLatitude(user.getLatitude());
			ret.setLongtitude(user.getLongtitude());
			ret.setPostalCode(user.getPostalCode());
			ret.setTaxRegistrationNumber(user.getTaxRegistrationNumber());
			ret.setTelephone(user.getTelephone());
			ret.setTown(user.getTown());
			ret.setUsername(user.getUsername());
			ret.setStatus(PropertyProvider.OK);
		}
		return ret;
	}

	/**
	 * Encrypt sh a256.
	 *
	 * @param key
	 *            the key
	 * @return the string
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
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

	public boolean isAdmin() {
		return getEmail() == PropertyProvider.ADMINEMAIL;
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

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(final boolean confirmed) {
		this.confirmed = confirmed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
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
		builder.append(", confirmed=");
		builder.append(confirmed);
		builder.append("]");
		return builder.toString();
	}

}
