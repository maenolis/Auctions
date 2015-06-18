package org.maenolis.auctions.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@XmlRootElement
@Entity(name="User")
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTaxRegistrationNumber() {
		return taxRegistrationNumber;
	}

	public void setTaxRegistrationNumber(String taxRegistrationNumber) {
		this.taxRegistrationNumber = taxRegistrationNumber;
	}
	
	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(float longtitude) {
		this.longtitude = longtitude;
	}

	public User(String username, String firstName, String lastName,
			String email, String password, String country, String town,
			String address, String telephone, String postalCode,
			String taxRegistrationNumber, float latitude, float longtitude) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = encryptSHA256(password);
		this.country = country;
		this.town = town;
		this.address = address;
		this.telephone = telephone;
		this.postalCode = postalCode;
		this.taxRegistrationNumber = taxRegistrationNumber;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}
	
	public User(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		this.username = user.username;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.email = user.email;
		this.password = encryptSHA256(user.password);
		this.country = user.country;
		this.town = user.town;
		this.address = user.address;
		this.telephone = user.telephone;
		this.postalCode = user.postalCode;
		this.taxRegistrationNumber = user.taxRegistrationNumber;
		this.latitude = user.latitude;
		this.longtitude = user.longtitude;
	}
	
	public User() {
		
	}
	
	public static User getUser(String email) {

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
	
	public static String encryptSHA256(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
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
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String s1 = "rr";
		String s2 = "rr";
		
		System.out.println(s1.equals(s2));
		System.out.println("rr".equals("r"));
		System.out.println("rr".equals("rrR"));
		
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email
				+ ", country=" + country + ", town=" + town + ", address="
				+ address + ", telephone=" + telephone + ", postalCode="
				+ postalCode + ", taxRegistrationNumber="
				+ taxRegistrationNumber + ", latitude=" + latitude
				+ ", longtitude=" + longtitude + "]";
	}
}
