package models;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

import constants.PackageStatus;
import forms.RequestDeliveryForm;


@Entity
@Table(name = "package")
@NamedQuery(name = Package.GET_NEW_PACKAGES, query = "SELECT p From Package p WHERE status = ?1 AND customer != ?2)")
public class Package implements Serializable {
	/**
	 * 
	 */
	public static final String GET_NEW_PACKAGES = "Package.getNewPackages";
	private static final long serialVersionUID = 1L;
	
	
	public Package() {
		super();
	}

	public Package(RequestDeliveryForm requestDeliveryForm) {
		this.description = requestDeliveryForm.getDescription();
		this.weight = requestDeliveryForm.getWeight();
		this.status = PackageStatus.NEW;

		this.fromCity = requestDeliveryForm.getFromCity();
		this.fromAddress = requestDeliveryForm.getFromAddress();
		this.fromStreetNumber = requestDeliveryForm.getFromStreetNumber();
		this.fromPostalCode = requestDeliveryForm.getFromPostalCode();
		this.fromCountry = requestDeliveryForm.getFromCountry();
		this.fromLat = requestDeliveryForm.getFromLat();
		this.fromLng = requestDeliveryForm.getFromLng();
		this.senderName = requestDeliveryForm.getSenderName();
		
		this.toCity = requestDeliveryForm.getToCity();
		this.toAddress = requestDeliveryForm.getToAddress();
		this.toStreetNumber = requestDeliveryForm.getToStreetNumber();
		this.toPostalCode = requestDeliveryForm.getToPostalCode();
		this.toCountry =  requestDeliveryForm.getToCountry();
		this.toLat = requestDeliveryForm.getToLat();
		this.toLng = requestDeliveryForm.getToLng();
		this.recipientName = requestDeliveryForm.getRecipientName();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sender_name")
	private String senderName;
	
	@Column(name = "recipient_name")
	private String recipientName;

	@OneToOne
	@JoinColumn(name="fk_customer_id")
	private User customer; 
	
	@OneToOne
	@JoinColumn(name="fk_postman_id")
	private User postman; 
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "weight")
	private Double weight;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private PackageStatus status = PackageStatus.NEW;
	 
	@Column(name = "from_address")
	private String fromAddress;
	
	@Column(name = "from_street_number")
	private String fromStreetNumber;
	
	@Column(name = "from_postal_code")
	private String fromPostalCode;
	
	@Column(name = "from_city")
	private String fromCity;
	
	@Column(name = "from_country")
	private String fromCountry;
	
	@Column(name = "to_address")
	private String toAddress;
	
	@Column(name = "to_street_number")
	private String toStreetNumber;
	
	@Column(name = "to_postal_code")
	private String toPostalCode;
	
	@Column(name = "to_city")
	private String toCity;
	
	@Column(name = "to_country")
	private String toCountry;

	@Column(name = "from_lat")
	private double fromLat;
	
	@Column(name = "from_lng")
	private double fromLng;
	
	@Column(name = "to_lat")
	private double toLat;
	
	@Column(name = "to_lng")
	private double toLng;
	
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

	public User getCusomter() {
		return customer;
	}

	public void setCusomter(User cusomter) {
		this.customer = cusomter;
	}

	public User getPostman() {
		return postman;
	}

	public void setPostman(User postman) {
		this.postman = postman;
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

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fromAddress == null) ? 0 : fromAddress.hashCode());
		result = prime * result + ((fromCity == null) ? 0 : fromCity.hashCode());
		result = prime * result + ((fromCountry == null) ? 0 : fromCountry.hashCode());
		long temp;
		temp = Double.doubleToLongBits(fromLat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(fromLng);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fromPostalCode == null) ? 0 : fromPostalCode.hashCode());
		result = prime * result + ((fromStreetNumber == null) ? 0 : fromStreetNumber.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((postman == null) ? 0 : postman.hashCode());
		result = prime * result + ((recipientName == null) ? 0 : recipientName.hashCode());
		result = prime * result + ((senderName == null) ? 0 : senderName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((toAddress == null) ? 0 : toAddress.hashCode());
		result = prime * result + ((toCity == null) ? 0 : toCity.hashCode());
		result = prime * result + ((toCountry == null) ? 0 : toCountry.hashCode());
		temp = Double.doubleToLongBits(toLat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(toLng);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((toPostalCode == null) ? 0 : toPostalCode.hashCode());
		result = prime * result + ((toStreetNumber == null) ? 0 : toStreetNumber.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Package other = (Package) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fromAddress == null) {
			if (other.fromAddress != null)
				return false;
		} else if (!fromAddress.equals(other.fromAddress))
			return false;
		if (fromCity == null) {
			if (other.fromCity != null)
				return false;
		} else if (!fromCity.equals(other.fromCity))
			return false;
		if (fromCountry == null) {
			if (other.fromCountry != null)
				return false;
		} else if (!fromCountry.equals(other.fromCountry))
			return false;
		if (Double.doubleToLongBits(fromLat) != Double.doubleToLongBits(other.fromLat))
			return false;
		if (Double.doubleToLongBits(fromLng) != Double.doubleToLongBits(other.fromLng))
			return false;
		if (fromPostalCode == null) {
			if (other.fromPostalCode != null)
				return false;
		} else if (!fromPostalCode.equals(other.fromPostalCode))
			return false;
		if (fromStreetNumber == null) {
			if (other.fromStreetNumber != null)
				return false;
		} else if (!fromStreetNumber.equals(other.fromStreetNumber))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (postman == null) {
			if (other.postman != null)
				return false;
		} else if (!postman.equals(other.postman))
			return false;
		if (recipientName == null) {
			if (other.recipientName != null)
				return false;
		} else if (!recipientName.equals(other.recipientName))
			return false;
		if (senderName == null) {
			if (other.senderName != null)
				return false;
		} else if (!senderName.equals(other.senderName))
			return false;
		if (status != other.status)
			return false;
		if (toAddress == null) {
			if (other.toAddress != null)
				return false;
		} else if (!toAddress.equals(other.toAddress))
			return false;
		if (toCity == null) {
			if (other.toCity != null)
				return false;
		} else if (!toCity.equals(other.toCity))
			return false;
		if (toCountry == null) {
			if (other.toCountry != null)
				return false;
		} else if (!toCountry.equals(other.toCountry))
			return false;
		if (Double.doubleToLongBits(toLat) != Double.doubleToLongBits(other.toLat))
			return false;
		if (Double.doubleToLongBits(toLng) != Double.doubleToLongBits(other.toLng))
			return false;
		if (toPostalCode == null) {
			if (other.toPostalCode != null)
				return false;
		} else if (!toPostalCode.equals(other.toPostalCode))
			return false;
		if (toStreetNumber == null) {
			if (other.toStreetNumber != null)
				return false;
		} else if (!toStreetNumber.equals(other.toStreetNumber))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}	
	
	
	



}
