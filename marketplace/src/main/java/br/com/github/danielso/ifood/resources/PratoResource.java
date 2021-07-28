package br.com.github.danielso.ifood.resources;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.github.danielso.ifood.commons.Constants;
import br.com.github.danielso.ifood.dto.PratoDTO;
import br.com.github.danielso.ifood.entities.Prato;
import io.quarkus.reactive.datasource.ReactiveDataSource;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

@Path(Constants.API_VERSION + Constants.REST_PRATOS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {

	@Inject
	@ReactiveDataSource("marketplace")
	@Named("marketplace")
	PgPool pgPool;
	
    @GET
    public Multi<PratoDTO> buscarPratos() {
        return Prato.findAll(pgPool);
    }
}