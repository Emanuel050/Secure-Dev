package controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import constants.PackageStatus;
import db_repositories.RepositoryUtils;
import exceptions.AeException;
import exceptions.AeMessage;
import exceptions.AeMessages;
import exceptions.MessageType;
import forms.FormValidator;
import forms.QuoteForm;
import forms.RequestDeliveryForm;
import models.Package;
import models.PackageBid;
import models.User;
import models.lite.PackageModelLite;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;
import utils.AppUtils;
import utils.ControllerUtils;

public class RequestDeliveryController extends ControllerUtils {

	@Transactional
	public Result requestDelivery() throws AeException {
		User user = sessionKeepAlive();
		Logger.info("{}", request().toString());
		RequestDeliveryForm requestrDeliveryForm = Json.fromJson(request().body().asJson(), RequestDeliveryForm.class);
		FormValidator validateForm = requestrDeliveryForm.validateForm(RequestDeliveryForm.class, requestrDeliveryForm);
		validateForm.validateCsrfAttack(requestrDeliveryForm);
		
		Package newPackage = null;
		if (!validateForm.getMessages().isFailed()) {
			newPackage = new Package(requestrDeliveryForm);
			newPackage.setCusomter(user);
			RepositoryUtils.save(newPackage);
		}
		request();

		return validateForm.getResult("");
		//return validateForm.getMessages().getResult("Your order has been successfully sent");
		
	}

	@Transactional
	public Result getNewPackages() throws AeException {
		User user = sessionKeepAlive();
		Logger.info("{}", request().toString());
		List<Package> packageList = RepositoryUtils.getEntityListByNamedQuery(Package.class, Package.GET_NEW_PACKAGES,
				PackageStatus.NEW, user);
		List<PackageModelLite> packageListLite = packageList.stream().map(p -> new PackageModelLite(p))
				.collect(Collectors.toList());
		return ControllerUtils.ok(Json.toJson(packageListLite));
	}

	@Transactional
	public Result submitDeliveryQuote() throws AeException {
		User user = sessionKeepAlive();
		QuoteForm quoteForm = Json.fromJson(request().body().asJson(), QuoteForm.class);
		FormValidator validateForm = quoteForm.validateForm(QuoteForm.class, quoteForm);
		validateForm.validateCsrfAttack(quoteForm);
		
		if (!validateForm.getMessages().isFailed()) {
			PackageBid packageBid = new PackageBid(quoteForm, user);
			packageBid.setPostman(user);
			try {
				RepositoryUtils.save(packageBid);
			} catch (Exception e) {
				if (AppUtils.hasCause(e, MySQLIntegrityConstraintViolationException.class)) {
					throw new AeException(new AeMessage(MessageType.ERROR, AeMessages.DUPLICATE_ENTRY_EXISTS));
				}
			}
		}
		
		request();
		return validateForm.getResult("");
	}

	@Transactional
	public Result getAllDeliveries() throws AeException {
		User user = getUserByToken();
		List<Package> packages = RepositoryUtils.getEntityListByFieldValue(Package.class, "customer.id", user.getId());
		List<PackageModelLite> packageListLite = packages.stream().map(p -> new PackageModelLite(p))
				.collect(Collectors.toList());
		return ok(Json.toJson(packageListLite));
	}

	@Transactional
	public Result deletePackage(Long id) throws AeException {
		User user = getUserByToken();

		Package packageToRemove = RepositoryUtils.getEntityById(id, Package.class);
		// Only the customer can delete his own package Or the Admin
		if (user.getId() == packageToRemove.getCusomter().getId() || user.getType().equals("Admin")) {
			RepositoryUtils.remove(packageToRemove);
		}
		return ok("Package Removed");
	}
	
}
