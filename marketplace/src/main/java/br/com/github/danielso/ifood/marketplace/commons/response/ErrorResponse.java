package br.com.github.danielso.ifood.marketplace.commons.response;

import java.io.Serializable;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = -9122860654336300559L;

	@Schema(description = "Retorna a mensagem de erro", example = "could not execute statement", required = true)
	private final String message;
	@Schema(description = "Retorna a causa raiz do erro", example = "PSQLException: ERROR: duplicate key value violates unique constraint Detalhe: Key (nome)=(Outback) already exists.", required = true)
	private final String cause;
	@Schema(description = "Retorna o stacktrace de erros", example = "\\tat org.postgresql.core.v3.QueryExecutorImpl.receiveErrorResponse(QueryExecutorImpl.java:2553)", required = true)
	private final String[] stacktrace;

	public ErrorResponse(String message, String cause, String[] stacktrace) {
		this.message = message;
		this.cause = cause;
		this.stacktrace = stacktrace;
	}

	public static ErrorResponse of(Exception exception) {

		return new ErrorResponse(exception.getCause() != null ? exception.getCause().getMessage() : null,
				ExceptionUtils.getRootCauseMessage(exception), ExceptionUtils.getRootCauseStackTrace(exception));
	}

	public String getMessage() {
		return message;
	}

	public String getCause() {
		return cause;
	}

	public String[] getStacktrace() {
		return stacktrace;
	}

}