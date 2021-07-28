package br.com.github.danielso.ifood.resources;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.danielso.ifood.commons.Constants;
import br.com.github.danielso.ifood.commons.response.ErrorResponse;
import br.com.github.danielso.ifood.dto.PratoDTO;
import br.com.github.danielso.ifood.entities.Prato;
import io.quarkus.reactive.datasource.ReactiveDataSource;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

@Path(Constants.API_VERSION)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {

	private static final String TAG = "Pratos";
	private static final String TAG_DESCRIPTION = "Representa os pratos de um restaurante";

	@Inject
	@ReactiveDataSource("marketplace")
	@Named("marketplace")
	PgPool pgPool;

	@GET
	@Path("{idRestaurante}/pratos")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Registros listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = PratoDTO.class))),
			@APIResponse(responseCode = "400", description = "Erro na obtenção dos dados", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ErrorResponse.class))) })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	public Multi<PratoDTO> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
		return Prato.findAll(pgPool, idRestaurante);
	}
}