package controllers;

import java.util.List;
import java.util.stream.Collectors;

import constants.ForumMessageType;
import db_repositories.RepositoryUtils;
import exceptions.AeException;
import exceptions.AeMessage;
import exceptions.AeMessages;
import exceptions.MessageType;
import forms.FormValidator;
import forms.ForumForm;
import forms.RequestDeliveryForm;
import models.Forum;
import models.Package;
import models.User;
import models.lite.ForumLiteMsg;
import models.lite.PackageModelLite;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;
import utils.ControllerUtils;

public class ForumController extends ControllerUtils {

	@Transactional
	public Result setNewQuestion() throws AeException {

		User user = sessionKeepAlive();
		Logger.info("{}", request().toString());
		ForumForm forumForm = Json.fromJson(request().body().asJson(), ForumForm.class);
		FormValidator validateForm = forumForm.validateForm(ForumForm.class, forumForm);
		Forum newQuestion = new Forum(forumForm);
		newQuestion.setUser(user);
		newQuestion.setType(ForumMessageType.QUESTION);

		if (!validateForm.getMessages().isFailed()) {
			RepositoryUtils.save(newQuestion);
		}

		//return validateForm.getResult(newQuestion);
		return validateForm.getMessages().getResult("");
	}

	@Transactional
	public Result setNewAnswer(Long questionId) throws AeException {

		User user = sessionKeepAlive();
		Logger.info("{}", request().toString());
		ForumForm forumForm = Json.fromJson(request().body().asJson(), ForumForm.class);
		FormValidator validateForm = forumForm.validateForm(ForumForm.class, forumForm);
		Forum newAnswer = new Forum(forumForm);
		Forum responeToQuestion = RepositoryUtils.getEntityById(questionId, Forum.class);
		newAnswer.setUser(user);
		newAnswer.setType(ForumMessageType.ANSWER);
		newAnswer.setForum(responeToQuestion);

		if (!validateForm.getMessages().isFailed()) {
			RepositoryUtils.save(newAnswer);
		}

		return validateForm.getResult(newAnswer);
	}

	@Transactional
	public Result getAllQuestions() throws AeException {
		// User user = getUserByToken();
		List<Forum> forumQuestions = RepositoryUtils.getEntityListByFieldValue(Forum.class, "type", ForumMessageType.QUESTION);
		List<ForumLiteMsg> forumLiteMessage = forumQuestions.stream().map(p -> new ForumLiteMsg(p)).collect(Collectors.toList());

		return ok(Json.toJson(forumLiteMessage));
	}
	

	@Transactional
	public Result getAllQuestionAnswers(Long questionId) throws AeException {
		// User user = getUserByToken();
		List<Forum> forumQuestionAnswers = RepositoryUtils.getEntityListByFieldValue(Forum.class, "forum.id", questionId);
		List<ForumLiteMsg> forumLiteMessage = forumQuestionAnswers.stream().map(p -> new ForumLiteMsg(p)).collect(Collectors.toList());

		return ok(Json.toJson(forumLiteMessage));
	}
}