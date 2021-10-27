package br.com.github.danielso.ifood.marketplace.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.danielso.ifood.marketplace.commons.Constants;
import br.com.github.danielso.ifood.marketplace.commons.response.ErrorResponse;
import br.com.github.danielso.ifood.marketplace.dto.PratoDTO;
import br.com.github.danielso.ifood.marketplace.entities.Prato;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

@Path(Constants.API_VERSION + Constants.REST_PRATOS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantesResource {

	private static final String TAG = "Pratos";
	private static final String TAG_DESCRIPTION = "Representa os pratos de um restaurante";

	@Inject
	PgPool pgPool;

	@GET
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Registros listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = PratoDTO.class))),
			@APIResponse(responseCode = "400", description = "Erro na obtenção dos dados", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ErrorResponse.class))) })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	@Counted(displayName = "Quantidade buscas de pratos", name = "marketplace_qtd_busca_pratos", description = "Quantidades de buscas de pratos", absolute = true)
	@SimplyTimed(displayName = "Tempo buscas de pratos", name = "marketplace_tempo_simples_busca_pratos", absolute = true)
	@Timed(displayName = "Tempo completo buscas de pratos", name = "marketplace_tempo_completo_de_busca_pratos", absolute = true, description = "O tempo ao buscar pratos")
	public Multi<PratoDTO> buscarPratos() {
		return Prato.findAll(pgPool);
	}
}