package br.com.github.danielso.ifood.pedido.commons.exceptionmapper;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jboss.logging.Logger;

import br.com.github.danielso.ifood.pedido.commons.response.ConstraintViolationResponse;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	private static final Logger LOG = Logger.getLogger(ConstraintViolationExceptionMapper.class);
	
	@Override
	public Response toResponse(ConstraintViolationException exception) {
		LOG.error(ExceptionUtils.getRootCauseMessage(exception));
		LOG.error(ExceptionUtils.getStackTrace(exception));
		return Response.status(Status.BAD_REQUEST).entity(ConstraintViolationResponse.of(exception)).build();
	}

}
