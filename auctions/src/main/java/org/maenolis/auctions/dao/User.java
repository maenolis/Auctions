package org.maenolis.auctions.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="newUser")
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
	
	public User(String username, String firstName, String lastName,
			String email, String password, String country, String town,
			String address, String telephone, String postalCode,
			String taxRegistrationNumber) {
		
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
	}
	
	public User() {
		
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
		String s1 = "66554444";
		String s2 = "99887766";
		String s3 = "66554444";
		
		if (encryptSHA256(s1).compareTo(encryptSHA256(s2)) == 0) {
			System.out.println("s1 = s2");
		} else {
			System.out.println("s1 != s2");
		}
		
		if (encryptSHA256(s1).compareTo(encryptSHA256(s3)) == 0) {
			System.out.println("s1 = s3");
		} else {
			System.out.println("s1 != s3");
		}
		
		System.out.println(encryptSHA256(s1));
		System.out.println(encryptSHA256(s2));
		System.out.println(encryptSHA256(s3));
	}
}
