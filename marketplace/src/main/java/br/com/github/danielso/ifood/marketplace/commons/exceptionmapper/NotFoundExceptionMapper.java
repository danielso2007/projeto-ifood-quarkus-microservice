package br.com.github.danielso.ifood.marketplace.commons.exceptionmapper;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jboss.logging.Logger;

import br.com.github.danielso.ifood.marketplace.commons.response.ErrorResponse;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	private static final Logger LOG = Logger.getLogger(ConstraintViolationExceptionMapper.class);
	
	@Override
	public Response toResponse(NotFoundException exception) {
		LOG.error(ExceptionUtils.getRootCauseMessage(exception));
		LOG.error(ExceptionUtils.getStackTrace(exception));
		return Response.status(Status.NOT_FOUND).entity(ErrorResponse.of(exception)).build();
	}

}
