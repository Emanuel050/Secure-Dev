package forms;

import java.io.Serializable;

import exceptions.AeException;

public class RequestDeliveryForm extends AeForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String senderName;
	private String fromCountry;
	private String fromCity;
	private String fromAddress;
	private String fromStreetNumber;
	private String fromPostalCode;
	private String recipientName;
	private String toCountry;
	private String toCity;
	private String toAddress;
	private String toStreetNumber;
	private String toPostalCode;
	private String description;
	private double weight;
	private String status;
	private double fromLat;
	private double fromLng;
	private double toLat;
	private double toLng;
	
	
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


	public String getFromCountry() {
		return fromCountry;
	}


	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}


	public String getFromCity() {
		return fromCity;
	}


	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
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


	public String getToCountry() {
		return toCountry;
	}


	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}


	public String getToCity() {
		return toCity;
	}


	public void setToCity(String toCity) {
		this.toCity = toCity;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public double getFromLat() {
		return fromLat;
	}


	public double getFromLng() {
		return fromLng;
	}


	public double getToLat() {
		return toLat;
	}


	public double getToLng() {
		return toLng;
	}


	public void setFromLat(double fromLat) {
		this.fromLat = fromLat;
	}


	public void setFromLng(double fromLng) {
		this.fromLng = fromLng;
	}


	public void setToLat(double toLat) {
		this.toLat = toLat;
	}


	public void setToLng(double toLng) {
		this.toLng = toLng;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public <T> FormValidator validateForm(Class<T> clazz, T form) throws AeException {
		FormValidator formValidator = FormValidator.startFieldsBasicValidation(clazz, form);
		return formValidator;
	}


	



}