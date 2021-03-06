
package br.com.github.danielso.ifood.cadastro.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;

import br.com.github.danielso.ifood.cadastro.commons.Constants;
import br.com.github.danielso.ifood.cadastro.commons.response.ConstraintViolationResponse;
import br.com.github.danielso.ifood.cadastro.commons.response.ErrorResponse;
import br.com.github.danielso.ifood.cadastro.dto.AdicionarPratoDTO;
import br.com.github.danielso.ifood.cadastro.dto.PratoDTO;
import br.com.github.danielso.ifood.cadastro.entities.Prato;
import br.com.github.danielso.ifood.cadastro.entities.Restaurante;
import br.com.github.danielso.ifood.cadastro.mapper.IPratoMapper;
import br.com.github.danielso.ifood.cadastro.repositories.PratoRepository;
import br.com.github.danielso.ifood.cadastro.repositories.RestauranteRepository;
import io.quarkus.panache.common.Sort;

@Traced
@Path(Constants.API_VERSION + Constants.REST_RESTAURANTE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratosResource {

	private static final String TAG = "Pratos";
	private static final String TAG_DESCRIPTION = "Representa os pratos de um restaurante";
	private static final String DEFAULT_ORDER = "asc";

	@Inject
	PratoRepository repository;
	@Inject
	RestauranteRepository restauranteRepository;
	@Inject
	IPratoMapper mapper;

	public PratosResource() {
		// Construtor padr??o.
	}

	@GET
	@Path("{idRestaurante}" + Constants.REST_PRATO)
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Registros listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = PratoDTO.class))),
			@APIResponse(responseCode = "400", description = "Erro na obten????o dos dados ou filtro", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ErrorResponse.class))) })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	@Counted(displayName = "Quantidade buscas de pratos", name = "cadastro_pratos_qtd_busca", description = "Quantidades de buscas de pratos", absolute = true)
	@SimplyTimed(displayName = "Tempo buscas de pratos", name = "cadastro_pratos_tempo_simples_busca", absolute = true)
	@Timed(displayName = "Tempo completo buscas de pratos", name = "cadastro_pratos_tempo_completo_de_busca", absolute = true)
	public List<PratoDTO> getAll(@PathParam("idRestaurante") Long idRestaurante,
			@QueryParam("sort") @DefaultValue("id") String sortField,
			@QueryParam("order") @DefaultValue(DEFAULT_ORDER) String order) {
		String[] fields = sortField.contains(",") ? sortField.split(",") : sortField.split(";");
		var sort = Sort.ascending(fields);
		if (!order.equals(DEFAULT_ORDER)) {
			sort = Sort.descending(fields);
		}
		return repository.findByRestaurante(idRestaurante, sort).stream().map(mapper::toPratoDTO).collect(Collectors.toList());
	}

	@POST
	@Path("{idRestaurante}" + Constants.REST_PRATO)
	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Registro criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.OBJECT, implementation = PratoDTO.class))),
			@APIResponse(responseCode = "400", description = "Erro na obten????o dos dados ou filtro", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ConstraintViolationResponse.class))),
			@APIResponse(responseCode = "404", description = "N??o foi poss??vel cadastrar o registro.", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ErrorResponse.class))) })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	@Transactional
	@Counted(displayName = "Quantidade de pratos cadastrados", name = "cadastro_pratos_qtd_salvos_restaurante", description = "Quantidades de pratos cadastrados", absolute = true)
	public Response save(@PathParam("idRestaurante") Long idRestaurante, @Valid AdicionarPratoDTO dto) {
		var entity = mapper.toPrato(dto);
		entity.setRestaurante(findRestaurante(idRestaurante));
		repository.persist(entity);
		return Response.status(Status.CREATED).entity(mapper.toPratoDTO(entity)).build();
	}

	@PUT
	@Path("{idRestaurante}" + Constants.REST_PRATO + "/{id}")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Registro atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.OBJECT, implementation = PratoDTO.class))),
			@APIResponse(responseCode = "400", description = "Erro na obten????o dos dados ou filtro", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ConstraintViolationResponse.class))),
			@APIResponse(responseCode = "404", description = "Registro n??o encontrado.", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ErrorResponse.class))) })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	@Transactional
	@Counted(displayName = "Quantidade de pratos atualizados", name = "cadastro_pratos_qtd_atualizacao", description = "Quantidades de pratos atualizados", absolute = true)
	public Response update(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id,
			@Valid AdicionarPratoDTO dto) {
		Prato entity = repository.findByIdAndRestauranteId(id, idRestaurante).orElseThrow(NotFoundException::new);
		mapper.toPrato(dto, entity);
		repository.persist(entity);
		return Response.ok(mapper.toPratoDTO(entity)).build();
	}

	@GET
	@Path("{idRestaurante}" + Constants.REST_PRATO + "/{id}")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Registro carregado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.OBJECT, implementation = PratoDTO.class))),
			@APIResponse(responseCode = "404", description = "Registro n??o encontrado.", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ErrorResponse.class))) })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	@Counted(displayName = "Quantidade de pratos pesquisados por ID", name = "cadastro_pratos_qtd_por_id", description = "Quantidades de pratos pesquisa por ID", absolute = true)
	public PratoDTO getById(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id) {
		return repository.findByIdAndRestauranteId(id, idRestaurante).map(mapper::toPratoDTO).orElseThrow(NotFoundException::new);
	}

	@DELETE
	@Path("{idRestaurante}" + Constants.REST_PRATO + "/{id}")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Registro deletado com sucesso"),
			@APIResponse(responseCode = "404", description = "Registro n??o encontrado.", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ErrorResponse.class))) })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	@Transactional
	@Counted(displayName = "Quantidade de pratos deletados", name = "cadastro_pratos_qtd_delete", description = "Quantidades de pratos deletados", absolute = true)
	public Response delete(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id) {
		repository.delete(repository.findByIdAndRestauranteId(id, idRestaurante).orElseThrow(NotFoundException::new));
		return Response.status(Status.OK).build();
	}

	private Restaurante findRestaurante(Long idRestaurante) {
		return restauranteRepository.findByIdOptional(idRestaurante)
				.orElseThrow(() -> new NotFoundException("Restaurante n??o existe"));
	}

}
