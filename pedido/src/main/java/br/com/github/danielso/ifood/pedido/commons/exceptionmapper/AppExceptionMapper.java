package br.com.github.danielso.ifood.pedido.commons.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jboss.logging.Logger;

import br.com.github.danielso.ifood.pedido.commons.response.ErrorResponse;

@Provider
public class AppExceptionMapper implements ExceptionMapper<Exception> {

	private static final Logger LOG = Logger.getLogger(AppExceptionMapper.class);
	
	@Override
	public Response toResponse(Exception exception) {
		LOG.error(ExceptionUtils.getRootCauseMessage(exception));
		LOG.error(ExceptionUtils.getStackTrace(exception));
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ErrorResponse.of(exception)).build();
	}

}

