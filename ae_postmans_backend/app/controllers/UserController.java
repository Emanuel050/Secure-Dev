package controllers;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

import com.google.common.net.MediaType;
import forms.AeForm;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Http.Cookie;
import db_repositories.RepositoryUtils;
import exceptions.AeException;
import exceptions.AeMessage;
import exceptions.AeMessages;
import exceptions.MessageType;
import forms.FormValidator;
import forms.LoginForm;
import forms.SignupForm;
import forms.UpdateUserForm;
import models.User;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import utils.AppUtils;
import utils.ControllerUtils;

public class UserController extends ControllerUtils {

	private static Class<User> clazz = User.class;

	@Transactional
	public Result getUserProfile() throws AeException {
		User user = sessionKeepAlive();

		SignupForm signupForm = new SignupForm(user);
		Cookie csrfCookie = genereateCsrfToken(user);
		return Controller.ok(Json.toJson(signupForm)).withCookies(csrfCookie);
	}

	@Transactional
	public Result getUserProfileById(Long id) throws AeException {
		User actionByUser = sessionKeepAlive();
		
		if (actionByUser.isAdmin()) {
			User user = RepositoryUtils.getEntityById(id, User.class);
			SignupForm signupForm = new SignupForm(user);
			return Controller.ok(Json.toJson(signupForm));
		} else {
			throw new AeException(new AeMessage(MessageType.ERROR, AeMessages.USER_NOT_ADMIN));
		}
	}

	@Transactional
	public Result login() throws AeException {
		Logger.info("{}", request().toString());
		LoginForm loginForm = Json.fromJson(request().body().asJson(), LoginForm.class);

		FormValidator validateForm = loginForm.validateForm(LoginForm.class, loginForm);
		if (validateForm.getMessages().isFailed()) {
			return validateForm.getMessages().getResult("");
		} else {
			User user = RepositoryUtils.getEntityByUniqueFieldValue(User.class, "mail", loginForm.getEmail());
			
			user.setAuthToken(BCrypt.hashpw(user.getMail(), BCrypt.gensalt()).toString());
			session("email", user.getMail());
			session("authToken", user.getAuthToken());
			return validateForm.getMessages().getResult(new SignupForm(user));
		}
	}

	@Transactional
	public Result signup() throws AeException {
		Logger.info("{}", request().toString());
		MultipartFormData<File> body = request().body().asMultipartFormData();
		FilePart<File> picture = body.getFile("file");
		byte[] bFile = validateAndReadFile(picture, MediaType.ANY_IMAGE_TYPE);

		SignupForm signupForm = Json.fromJson(
				Json.parse(request().body().asMultipartFormData().asFormUrlEncoded().get("form")[0]), SignupForm.class);
		FormValidator validateForm = signupForm.validateForm(SignupForm.class, signupForm);

		signupForm.setPassportPicture(bFile);
		signupForm.setType(User.getUser());

		if (!validateForm.getMessages().isFailed()) {
			User user = new User(signupForm);
			user.setPassword(BCrypt.hashpw(signupForm.getPassword(), BCrypt.gensalt()));
			RepositoryUtils.save(user);
		}

		return validateForm.getMessages().getResult("Signup");
	}

	public Result checkServerStatus() {
		Logger.info("{}", request().toString());
		return Controller.ok(Json.toJson("Server is up"));
	}

	@Transactional
	public Result logout() throws AeException {
		try {
			User user = getUserByToken();
			user.setAddress(null);
			RepositoryUtils.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		session().clear();
		return ok();
	}

	@Transactional
	public Result getAllUsers() throws AeException {
		User actionByUser = getUserByToken();
		if (actionByUser.isAdmin()) {
			List<User> users = RepositoryUtils.getAllEntities(User.class);
			return ok(Json.toJson(users));
		} else {
			throw new AeException(new AeMessage(MessageType.ERROR, AeMessages.USER_NOT_ADMIN));
		}
	}

	@Transactional
	public Result deleteUser(Long id) throws AeException {
		User actionByUser = getUserByToken();
		
		if (actionByUser.isAdmin()) {
			User userToRemove = RepositoryUtils.getEntityById(id, User.class);
			RepositoryUtils.remove(userToRemove);
			return ok("User Removed");
		} else {
			throw new AeException(new AeMessage(MessageType.ERROR, AeMessages.USER_NOT_ADMIN));
		}

	}

	@Transactional
	public Result updateUser() throws AeException {
		User user = getUserByToken();
		return updateUser(user);
	}

	@Transactional
	public Result updateUserById(Long id) throws AeException {
		User actionByUser = getUserByToken();
		
		if (actionByUser.isAdmin()) {
			User user = RepositoryUtils.getEntityById(id, User.class);
			return updateUser(user);
		} else {
			throw new AeException(new AeMessage(MessageType.ERROR, AeMessages.USER_NOT_ADMIN));
		}
	}
	
	@Transactional
	public Result changeUserToAdmin(Long id) throws AeException {
		User actionByUser = getUserByToken();
	
		if (actionByUser.isAdmin()) 
		{
			User user = RepositoryUtils.getEntityById(id, User.class);
			if(!user.isAdmin())
			{
				user.setType(User.getAdmin());
				RepositoryUtils.update(user);
			}
			else
			{
				throw new AeException(new AeMessage(MessageType.ERROR, AeMessages.USER_ALREADY_ADMIN));
			}
		}
		else
		{
			throw new AeException(new AeMessage(MessageType.ERROR, AeMessages.USER_NOT_ADMIN));
		}
		
		return ok("User permissions updated to Admin successfully ");
	
	}
	
	@Transactional
	private Result updateUser(User user) throws AeException {
	//	User userNew;
		Logger.info("{}", request().toString());

		MultipartFormData<File> body = request().body().asMultipartFormData();
		FilePart<File> picture = body.getFile("file");

		UpdateUserForm updateUserForm = Json.fromJson(
				Json.parse(request().body().asMultipartFormData().asFormUrlEncoded().get("form")[0]), UpdateUserForm.class);
		
		updateUserForm.setCurrentEmail(user.getMail());
		FormValidator formValidator = updateUserForm.validateForm(UpdateUserForm.class, updateUserForm);
		formValidator.validateCsrfAttack(updateUserForm);
		if (picture != null) {
			byte[] bFile = validateAndReadFile(picture, MediaType.ANY_IMAGE_TYPE);
			updateUserForm.setPassportPicture(bFile);
		}

		if (!formValidator.getMessages().isFailed()) {
			//userNew = new User(signupForm);
			user.setFirstName(AppUtils.updateIfChanged(user.getFirstName(), updateUserForm.getFirstName()));
			user.setLastName(AppUtils.updateIfChanged(user.getLastName(), updateUserForm.getLastName()));
			user.setBirthDate(AppUtils.updateIfChanged(user.getBirthDate(), updateUserForm.getBirthDate()));
			// IS UPDATE PASSWORD
			changePasswordProcess(user, updateUserForm);
			user.setMail(AppUtils.updateIfChanged(user.getMail(), updateUserForm.getNewEmail()));
			user.setPhoneNumber(AppUtils.updateIfChanged(user.getPhoneNumber(), updateUserForm.getPhoneNumber()));
			user.setGender(AppUtils.updateIfChanged(user.getGender(), updateUserForm.getGender()));
			RepositoryUtils.update(user);
		}

		return formValidator.getMessages().getResult("User updated successfully!");
	}

	private void changePasswordProcess(User user, UpdateUserForm updateUserForm) throws AeException {
		if (StringUtils.isNotBlank(updateUserForm.getOldPassword()) && StringUtils.isNotBlank(updateUserForm.getPassword())
				&& StringUtils.isNotBlank(updateUserForm.getPasswordConfirm())) {

			if (!AppUtils.checkIfValidUser(user.getMail(), updateUserForm.getOldPassword())) {
				session().clear();
				throw new AeException(new AeMessage(MessageType.ERROR, AeMessages.USER_OR_PASSWORD_INVALID));
			} else {
				user.setPassword(BCrypt.hashpw(updateUserForm.getPassword(), BCrypt.gensalt()));
			}
		}
	}

}
