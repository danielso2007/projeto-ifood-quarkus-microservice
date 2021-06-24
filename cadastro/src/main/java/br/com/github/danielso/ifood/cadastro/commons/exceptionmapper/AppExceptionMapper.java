package br.com.github.danielso.ifood.cadastro.commons.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.github.danielso.ifood.cadastro.commons.response.ErrorResponse;

@Provider
public class AppExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ErrorResponse.of(exception)).build();
	}

}

