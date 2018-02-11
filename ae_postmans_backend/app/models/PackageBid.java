package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import db_repositories.RepositoryUtils;
import forms.QuoteForm;

@Entity
@Table(name = "package_bids")
public class PackageBid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PackageBid() {
	}
	
	public PackageBid(QuoteForm quoteForm, User user) {
		Package pack = RepositoryUtils.getEntityById(quoteForm.getSelectedPackageId(), Package.class);
		this.pack = pack;
		this.chosen = false;
		this.postman = user;
		this.shippingTime = quoteForm.getShippingTime();
		this.price = quoteForm.getPrice();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@OneToOne()
	@JoinColumn(name = "fk_package_id")
	private Package pack;

	@OneToOne()
	@JoinColumn(name = "fk_postman_id")
	private User postman;

	@Column(name = "chosen")
	private boolean chosen;

	@Column(name = "shipping_time")
	private String shippingTime;
	
	@Column(name = "price")
	private double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Package getPack() {
		return pack;
	}

	public void setPack(Package pack) {
		this.pack = pack;
	}

	public User getPostman() {
		return postman;
	}

	public void setPostman(User postman) {
		this.postman = postman;
	}

	public boolean isChosen() {
		return chosen;
	}

	public void setChosen(boolean chosen) {
		this.chosen = chosen;
	}

	public String getShippingTime() {
		return shippingTime;
	}

	public double getPrice() {
		return price;
	}

	public void setShippingTime(String shippingTime) {
		this.shippingTime = shippingTime;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
