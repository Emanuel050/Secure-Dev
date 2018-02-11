package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Singleton;

import exceptions.AeException;
import exceptions.AeMessage;
import play.http.HttpErrorHandler;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

@Singleton
public class ErrorHandler implements HttpErrorHandler {
    public CompletionStage<Result> onClientError(RequestHeader request, int statusCode, String message) {
        return CompletableFuture.completedFuture(
                Results.status(statusCode, "A client error occurred: " + message)
        );
    }

    public CompletionStage<Result> onServerError(RequestHeader request, Throwable exception) {
        return CompletableFuture.completedFuture(getResult(exception));
    }

	protected Result getResult(Throwable exception) {
		exception.printStackTrace();
		if (exception instanceof AeException) {
			AeException aeException = (AeException) exception;
			AeMessage aeMessage =  aeException.getAeMessage();
			return Results.status(aeMessage.getErrorCode(), aeMessage.getFormatedMessage());
		} else {
		    return Results.internalServerError("A server error occurred: " + exception.getMessage());
		}
	}
}