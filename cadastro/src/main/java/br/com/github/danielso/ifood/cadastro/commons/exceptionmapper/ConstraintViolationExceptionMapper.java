package br.com.github.danielso.ifood.cadastro.commons.exceptionmapper;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.microprofile.opentracing.Traced;

import br.com.github.danielso.ifood.cadastro.commons.response.ConstraintViolationResponse;

@Traced
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		System.err.println(ExceptionUtils.getRootCauseMessage(exception));
		System.err.println(ExceptionUtils.getStackTrace(exception));
		return Response.status(Status.BAD_REQUEST).entity(ConstraintViolationResponse.of(exception)).build();
	}

}
