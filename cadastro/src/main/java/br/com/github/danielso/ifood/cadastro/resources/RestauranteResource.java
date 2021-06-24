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

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.danielso.ifood.cadastro.commons.Constants;
import br.com.github.danielso.ifood.cadastro.commons.response.ConstraintViolationResponse;
import br.com.github.danielso.ifood.cadastro.commons.response.ErrorResponse;
import br.com.github.danielso.ifood.cadastro.dto.AdicionarRestauranteDTO;
import br.com.github.danielso.ifood.cadastro.dto.RestauranteDTO;
import br.com.github.danielso.ifood.cadastro.entities.Restaurante;
import br.com.github.danielso.ifood.cadastro.mapper.IRestauranteMapper;
import br.com.github.danielso.ifood.cadastro.repositories.LocalizacaoRepository;
import br.com.github.danielso.ifood.cadastro.repositories.RestauranteRepository;
import io.quarkus.panache.common.Sort;

@Path(Constants.API_VERSION + Constants.REST_RESTAURANTE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

	private static final String TAG = "Restaurantes";
	private static final String TAG_DESCRIPTION = "Representa os restaurantes";
	private static final String DEFAULT_ORDER = "asc";

	@Inject
	RestauranteRepository repository;
	@Inject
	IRestauranteMapper mapper;
	@Inject
	LocalizacaoRepository localizacaoRepository;

	public RestauranteResource() {
		// Construtor padrão.
	}

	@GET
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Registros listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = RestauranteDTO.class))),
			@APIResponse(responseCode = "400", description = "Erro na obtenção dos dados ou filtro", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ConstraintViolationResponse.class))),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ErrorResponse.class))) })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	public List<RestauranteDTO> getAll(@QueryParam("sort") @DefaultValue("id") String sortField,
			@QueryParam("order") @DefaultValue(DEFAULT_ORDER) String order) {
		String[] fields = sortField.contains(",") ? sortField.split(",") : sortField.split(";");
		var sort = Sort.ascending(fields);
		if (!order.equals(DEFAULT_ORDER)) {
			sort = Sort.descending(fields);
		}
		return repository.listAll(sort).stream().map(mapper::toRestauranteDTO).collect(Collectors.toList());
	}

	@POST
	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Registro criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.OBJECT, implementation = RestauranteDTO.class))),
			@APIResponse(responseCode = "400", description = "Erro na obtenção dos dados ou filtro", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ConstraintViolationResponse.class))),
			@APIResponse(responseCode = "404", description = "Não foi possível cadastrar o registro.", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ErrorResponse.class))) })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	@Transactional
	public Response save(@Valid AdicionarRestauranteDTO dto) {
		var entity = mapper.toRestaurante(dto);
		repository.persist(entity);
		return Response.status(Status.CREATED).entity(mapper.toRestauranteDTO(entity)).build();
	}

	@PUT
	@Path("{id}")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Registro atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.OBJECT, implementation = RestauranteDTO.class))),
			@APIResponse(responseCode = "400", description = "Erro na obtenção dos dados ou filtro", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ConstraintViolationResponse.class))),
			@APIResponse(responseCode = "404", description = "Registro não encontrado.", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ErrorResponse.class))) })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	@Transactional
	public Response update(@PathParam("id") Long id, @Valid AdicionarRestauranteDTO dto) {
		Restaurante entity = repository.findByIdOptional(id).orElseThrow(NotFoundException::new);
		mapper.toRestaurante(dto, entity);
		repository.persist(entity);
		return Response.ok(mapper.toRestauranteDTO(entity)).build();
	}

	@GET
	@Path("{id}")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Registro carregado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.OBJECT, implementation = RestauranteDTO.class))),
			@APIResponse(responseCode = "404", description = "Registro não encontrado.", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ErrorResponse.class))) })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	public RestauranteDTO getById(@PathParam("id") Long id) {
		return repository.findByIdOptional(id).map(mapper::toRestauranteDTO).orElseThrow(NotFoundException::new);
	}

	@DELETE
	@Path("{id}")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Registro deletado com sucesso"),
			@APIResponse(responseCode = "404", description = "Registro não encontrado.", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(allOf = ErrorResponse.class))) })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	@Transactional
	public Response delete(@PathParam("id") Long id) {
		repository.delete(repository.findByIdOptional(id).orElseThrow(NotFoundException::new));
		return Response.status(Status.OK).build();
	}

}