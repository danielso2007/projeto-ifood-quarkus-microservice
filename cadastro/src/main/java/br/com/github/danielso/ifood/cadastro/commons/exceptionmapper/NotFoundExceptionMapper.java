package br.com.github.danielso.ifood.cadastro.commons.exceptionmapper;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.microprofile.opentracing.Traced;

import br.com.github.danielso.ifood.cadastro.commons.response.ErrorResponse;

@Traced
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException exception) {
		System.err.println(ExceptionUtils.getRootCauseMessage(exception));
		System.err.println(ExceptionUtils.getStackTrace(exception));
		return Response.status(Status.NOT_FOUND).entity(ErrorResponse.of(exception)).build();
	}

}
