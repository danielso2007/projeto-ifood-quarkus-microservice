
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

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.danielso.ifood.cadastro.Constants;
import br.com.github.danielso.ifood.cadastro.converter.PratoConverter;
import br.com.github.danielso.ifood.cadastro.dto.PratoDTO;
import br.com.github.danielso.ifood.cadastro.entities.BaseAudit;
import br.com.github.danielso.ifood.cadastro.entities.Prato;
import br.com.github.danielso.ifood.cadastro.entities.Restaurante;
import br.com.github.danielso.ifood.cadastro.repositories.PratoRepository;
import br.com.github.danielso.ifood.cadastro.repositories.RestauranteRepository;
import io.quarkus.panache.common.Sort;

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
	PratoConverter converter;
	@Inject
	RestauranteRepository restauranteRepository;

	public PratosResource() {
		// Construtor padrão.
	}

	@GET
	@Path("{idRestaurante}" + Constants.REST_PRATO)
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Registros listados com sucesso"),
			@APIResponse(responseCode = "400", description = "Erro na obtenção dos dados ou filtro"),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor") })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	public List<PratoDTO> getAll(@PathParam("idRestaurante") Long idRestaurante,
			@QueryParam("sort") @DefaultValue("id") String sortField,
			@QueryParam("order") @DefaultValue(DEFAULT_ORDER) String order) {
		String[] fields = sortField.contains(",") ? sortField.split(",") : sortField.split(";");
		var sort = Sort.ascending(fields);
		if (!order.equals(DEFAULT_ORDER)) {
			sort = Sort.descending(fields);
		}
		return getRepository().listAll(sort).stream().map(entity -> getConverter().entityToDto(entity))
				.collect(Collectors.toList());
	}

	@POST
	@Path("{idRestaurante}" + Constants.REST_PRATO)
	@APIResponses(value = { @APIResponse(responseCode = "201", description = "Registro criado com sucesso"),
			@APIResponse(responseCode = "404", description = "Não foi possível cadastrar o registro."),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor") })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	@Transactional
	public Response save(@PathParam("idRestaurante") Long idRestaurante, @Valid PratoDTO dto) {
		var entity = getConverter().dtoToEntity(dto);
		entity.setId(null);
		if (entity instanceof BaseAudit) {
			entity.setDataCriacao(null);
			entity.setDataAtualizacao(null);
		}
		entity.setRestaurante(findRestaurante(idRestaurante));
		getRepository().persist(entity);
		return Response.status(Status.CREATED).build();
	}

	@PUT
	@Path("{idRestaurante}" + Constants.REST_PRATO + "/{id}")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Registro atualizado com sucesso"),
			@APIResponse(responseCode = "404", description = "Registro não encontrado."),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor") })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	@Transactional
	public Response update(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id,
			@Valid PratoDTO dto) {
		Prato entity = getRepository().findByIdOptional(id).orElseThrow(NotFoundException::new);
		var p = getConverter().dtoToEntity(dto, entity);
		if (entity instanceof BaseAudit) {
			p.setDataAtualizacao(null);
		}
		entity.setRestaurante(findRestaurante(idRestaurante));
		getRepository().persist(p);
		return Response.status(Status.OK).build();
	}

	@GET
	@Path("{idRestaurante}" + Constants.REST_PRATO + "/{id}")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Registro carregado com sucesso."),
			@APIResponse(responseCode = "404", description = "Registro não encontrado."),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor") })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	public PratoDTO getById(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id) {
		Prato entity = getRepository().findByIdOptional(id).orElseThrow(NotFoundException::new);
		entity.setRestaurante(restauranteRepository.findByIdOptional(idRestaurante)
				.orElseThrow(() -> new NotFoundException("Restaurante não existe")));
		return getConverter().entityToDto(entity);
	}

	@DELETE
	@Path("{idRestaurante}" + Constants.REST_PRATO + "/{id}")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Registro deletado com sucesso"),
			@APIResponse(responseCode = "404", description = "Registro não encontrado."),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor") })
	@Tag(name = TAG, description = TAG_DESCRIPTION)
	@Transactional
	public Response delete(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id) {
		findRestaurante(idRestaurante);
		getRepository().delete(getRepository().findByIdOptional(id).orElseThrow(NotFoundException::new));
		return Response.status(Status.OK).build();
	}

	private Restaurante findRestaurante(Long idRestaurante) {
		return restauranteRepository.findByIdOptional(idRestaurante)
				.orElseThrow(() -> new NotFoundException("Restaurante não existe"));
	}

	public PratoRepository getRepository() {
		return repository;
	}

	public PratoConverter getConverter() {
		return converter;
	}

}
