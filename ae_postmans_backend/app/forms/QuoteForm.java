package forms;

import exceptions.AeException;
import java.io.Serializable;

public class QuoteForm extends AeForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double price;
	private String shippingTime;
	private Long selectedPackageId;

	public Long getSelectedPackageId() {
		return selectedPackageId;
	}

	public void setSelectedPackageId(Long selectedPackageId) {
		this.selectedPackageId = selectedPackageId;
	}

	@Override
	public <T> FormValidator validateForm(Class<T> clazz, T form) throws AeException {
		FormValidator formValidator = FormValidator.startFieldsBasicValidation(clazz, form);
		return formValidator;
	}

	public double getPrice() {
		return price;
	}

	public String getShippingTime() {
		return shippingTime;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setShippingTime(String shippingTime) {
		this.shippingTime = shippingTime;
	}

}
