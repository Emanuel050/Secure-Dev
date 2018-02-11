package models.lite;

import java.io.Serializable;

import constants.PackageStatus;
import models.Package;

public class PackageModelLite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String senderName;
	private String recipientName;
	private UserModelLite customer; 
	private UserModelLite postman; 
	private String description;
	private Double weight;
	private PackageStatus status = PackageStatus.NEW;
	private String fromAddress;
	private String fromStreetNumber;
	private String fromPostalCode;
	private String fromCity;
	private String fromCountry;
	private String toAddress;
	private String toStreetNumber;
	private String toPostalCode;
	private String toCity;
	private String toCountry;
	private double fromLat;
	private double fromLng;
	private double toLat;
	private double toLng;
	
	public PackageModelLite(Package pack) {
		this.id = pack.getId();
		this.description = pack.getDescription();
		this.weight = pack.getWeight();
		this.status = PackageStatus.NEW;
		this.fromCity = pack.getFromCity();
		this.fromAddress = pack.getFromAddress();
		this.fromStreetNumber = pack.getFromStreetNumber();
		this.fromPostalCode = pack.getFromPostalCode();
		this.fromCountry = pack.getFromCountry();
		this.fromLat = pack.getFromLat();
		this.fromLng = pack.getFromLng();
		this.senderName = pack.getSenderName();
		this.toCity = pack.getToCity();
		this.toAddress = pack.getToAddress();
		this.toStreetNumber = pack.getToStreetNumber();
		this.toPostalCode = pack.getToPostalCode();
		this.toCountry =  pack.getToCountry();
		this.toLat = pack.getToLat();
		this.toLng = pack.getToLng();
		this.recipientName = pack.getRecipientName();
		this.customer = pack.getCusomter() != null ? new UserModelLite(pack.getCusomter()) : null;
		this.postman = pack.getPostman() != null ? new UserModelLite(pack.getPostman()) : null;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public PackageStatus getStatus() {
		return status;
	}

	public void setStatus(PackageStatus status) {
		this.status = status;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getFromStreetNumber() {
		return fromStreetNumber;
	}

	public void setFromStreetNumber(String fromStreetNumber) {
		this.fromStreetNumber = fromStreetNumber;
	}

	public String getFromPostalCode() {
		return fromPostalCode;
	}

	public void setFromPostalCode(String fromPostalCode) {
		this.fromPostalCode = fromPostalCode;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getFromCountry() {
		return fromCountry;
	}

	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getToStreetNumber() {
		return toStreetNumber;
	}

	public void setToStreetNumber(String toStreetNumber) {
		this.toStreetNumber = toStreetNumber;
	}

	public String getToPostalCode() {
		return toPostalCode;
	}

	public void setToPostalCode(String toPostalCode) {
		this.toPostalCode = toPostalCode;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public String getToCountry() {
		return toCountry;
	}

	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}

	public UserModelLite getCustomer() {
		return customer;
	}

	public UserModelLite getPostman() {
		return postman;
	}

	public void setCustomer(UserModelLite customer) {
		this.customer = customer;
	}

	public void setPostman(UserModelLite postman) {
		this.postman = postman;
	}

	public double getFromLat() {
		return fromLat;
	}

	public void setFromLat(double fromLat) {
		this.fromLat = fromLat;
	}

	public double getFromLng() {
		return fromLng;
	}

	public void setFromLng(double fromLng) {
		this.fromLng = fromLng;
	}

	public double getToLat() {
		return toLat;
	}

	public void setToLat(double toLat) {
		this.toLat = toLat;
	}

	public double getToLng() {
		return toLng;
	}

	public void setToLng(double toLng) {
		this.toLng = toLng;
	}	
	
}
