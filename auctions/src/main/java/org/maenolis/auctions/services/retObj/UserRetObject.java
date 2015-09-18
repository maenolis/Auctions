package org.maenolis.auctions.services.retObj;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class UserRetObject.
 */
@XmlRootElement
public class UserRetObject {

	/** The id. */
	private int id;

	/** The username. */
	private String username;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The email. */
	private String email;

	/** The country. */
	private String country;

	/** The town. */
	private String town;

	/** The address. */
	private String address;

	/** The telephone. */
	private String telephone;

	/** The postal code. */
	private String postalCode;

	/** The tax registration number. */
	private String taxRegistrationNumber;

	/** The latitude. */
	private float latitude;

	/** The longtitude. */
	private float longtitude;

	/** The confirmed. */
	private boolean confirmed;

	/** The status. */
	private String status;

	/**
	 * Instantiates a new user ret object.
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
	 */
	public UserRetObject(final int id, final String username,
			final String firstName, final String lastName, final String email,
			final String country, final String town, final String address,
			final String telephone, final String postalCode,
			final String taxRegistrationNumber, final float latitude,
			final float longtitude, final boolean confirmed) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.country = country;
		this.town = town;
		this.address = address;
		this.telephone = telephone;
		this.postalCode = postalCode;
		this.taxRegistrationNumber = taxRegistrationNumber;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.confirmed = confirmed;
	}

	/**
	 * Instantiates a new user ret object.
	 */
	public UserRetObject() {
		super();
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
		builder.append("UserRetObject [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
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

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

}
