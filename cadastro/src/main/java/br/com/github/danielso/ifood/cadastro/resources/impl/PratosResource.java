
package br.com.github.danielso.ifood.cadastro.resources.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.github.danielso.ifood.cadastro.converter.PratoConverter;
import br.com.github.danielso.ifood.cadastro.dto.PratoDTO;
import br.com.github.danielso.ifood.cadastro.entities.BaseAudit;
import br.com.github.danielso.ifood.cadastro.entities.Prato;
import br.com.github.danielso.ifood.cadastro.repositories.PratoRepository;
import br.com.github.danielso.ifood.cadastro.resources.IPratoResource;
import io.quarkus.panache.common.Sort;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratosResource implements IPratoResource<Prato, PratoDTO, Long, PratoRepository, PratoConverter> {

	private static final String DEFAULT_ORDER = "asc";

	private PratoRepository repository;
	private PratoConverter converter;

	public PratosResource() {
	}

	@Inject
	public PratosResource(PratoRepository repository, PratoConverter converter) {
		this.repository = repository;
		this.converter = converter;
	}

	@Override
	public List<PratoDTO> getAll(Long idRestaurante, String sortField, String order) {
		String[] fields = sortField.contains(",") ? sortField.split(",") : sortField.split(";");
        var sort = Sort.ascending(fields);
        if (!order.equals(DEFAULT_ORDER)) {
            sort = Sort.descending(fields);
        }
        return getRepository().listAll(sort).stream().map(entity -> getConverter().entityToDto(entity)).collect(Collectors.toList());
	}

	@Override
	public Response save(Long idRestaurante, @Valid PratoDTO dto) {
		var entity = getConverter().dtoToEntity(dto);
    	entity.setId(null);
    	if (entity instanceof BaseAudit) {
			entity.setDataCriacao(null);
			entity.setDataAtualizacao(null);
		}
    	getRepository().persist(entity);
    	return Response.status(Status.CREATED).build();
	}

	@Override
	public Response update(Long idRestaurante, Long id, @Valid PratoDTO dto) {
		getRepository().findByIdOptional(id).ifPresentOrElse(entity -> {
			var p = getConverter().dtoToEntity(dto, entity);
			if (entity instanceof BaseAudit) {
				p.setDataCriacao(entity.getDataCriacao());
				p.setDataAtualizacao(entity.getDataAtualizacao());
			}
			getRepository().persist(p);
		}, NotFoundException::new);
        return Response.status(Status.OK).build();
	}

	@Override
	public PratoDTO getById(Long idRestaurante, Long id) {
		return getConverter().entityToDto(getRepository().findByIdOptional(id).orElseThrow(NotFoundException::new));
	}

	@Override
	public Response delete(Long idRestaurante, Long id) {
		getRepository().delete(getRepository().findByIdOptional(id).orElseThrow(NotFoundException::new));
        return Response.status(Status.OK).build();
	}

	@Override
	public PratoRepository getRepository() {
		return repository;
	}

	@Override
	public PratoConverter getConverter() {
		return converter;
	}

}
