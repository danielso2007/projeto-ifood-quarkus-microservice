package br.com.github.danielso.ifood.commons.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;

import br.com.github.danielso.ifood.commons.response.ErrorResponse;

@Provider
public class AppExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		System.err.println(ExceptionUtils.getRootCauseMessage(exception));
		System.err.println(ExceptionUtils.getStackTrace(exception));
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ErrorResponse.of(exception)).build();
	}

}

