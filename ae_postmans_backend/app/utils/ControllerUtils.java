package utils;

import java.io.File;
import java.io.FileInputStream;
import play.mvc.Http.Cookie;
import javax.activation.MimetypesFileTypeMap;
import javax.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;

import com.google.common.net.MediaType;

import db_repositories.RepositoryUtils;
import exceptions.AeException;
import exceptions.AeMessage;
import exceptions.AeMessages;
import exceptions.MessageType;
import models.User;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData.FilePart;


public class ControllerUtils extends Controller {
    @Inject FormFactory formFactory;

	public FormFactory getFormFactory() {
		return formFactory;
	}
	
	protected byte[] validateAndReadFile(FilePart<File> fileFromUser, MediaType allowedMediaType) throws AeException {
		File file = new File(fileFromUser.getFile().getAbsolutePath());
		String contentType = fileFromUser.getContentType();
		if (!MediaType.parse(contentType).is(allowedMediaType)) {
			throw new AeException(new AeMessage(MessageType.ERROR, AeMessages.FILE_TYPE_IS_NOT_ALLOWED, contentType, allowedMediaType.type()));
		}
		
        byte[] bFile = new byte[(int) file.length()];
        try {
   	     FileInputStream fileInputStream = new FileInputStream(file);
   	     //convert file into array of bytes
   	     fileInputStream.read(bFile);
   	     fileInputStream.close();
           } catch (Exception e) {
   	     e.printStackTrace();
          }
		return bFile;
	}
	public User sessionKeepAlive() throws AeException {
		User user = getUserByToken();
		
		if (user != null) {
			user.setAuthToken(BCrypt.hashpw(user.getMail(), BCrypt.gensalt()).toString());
			session("authToken", user.getAuthToken());
		} else {
			throw new AeException(new AeMessage(MessageType.ERROR, AeMessages.SESSION_TIMEOUT));
		}
		
		return user;
	}


	protected User getUserByToken() {
		String authToken = session("authToken");
		User user = RepositoryUtils.getEntityByUniqueFieldValue(User.class, "authToken", authToken);
		return user;
	}
	
	protected Cookie genereateCsrfToken(User user) {
		String csrfToken = BCrypt.hashpw(user.getMail(), BCrypt.gensalt()).toString();
		session("csrfToken", csrfToken);
		Cookie csrfCookie = new Cookie("CSRF_TOKEN", csrfToken, 1800, null, null, false, false);
		return csrfCookie;
	}
}
