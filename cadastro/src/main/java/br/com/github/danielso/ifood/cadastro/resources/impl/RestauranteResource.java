package br.com.github.danielso.ifood.cadastro.resources.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.github.danielso.ifood.cadastro.converter.RestauranteConverter;
import br.com.github.danielso.ifood.cadastro.dto.RestauranteDTO;
import br.com.github.danielso.ifood.cadastro.entities.BaseAudit;
import br.com.github.danielso.ifood.cadastro.entities.Restaurante;
import br.com.github.danielso.ifood.cadastro.repositories.RestauranteRepository;
import br.com.github.danielso.ifood.cadastro.resources.IRestauranteResource;
import io.quarkus.panache.common.Sort;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource implements
		IRestauranteResource<Restaurante, RestauranteDTO, Long, RestauranteRepository, RestauranteConverter> {

	private static final String DEFAULT_ORDER = "asc";

	private RestauranteRepository repository;
	private RestauranteConverter converter;

    public RestauranteResource() {
	}
    
    @Inject
    public RestauranteResource(RestauranteRepository repository, RestauranteConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<RestauranteDTO> getAll(String sortField, String order) {
        String[] fields = sortField.contains(",") ? sortField.split(",") : sortField.split(";");
        var sort = Sort.ascending(fields);
        if (!order.equals(DEFAULT_ORDER)) {
            sort = Sort.descending(fields);
        }
        return getRepository().listAll(sort).stream().map(entity -> getConverter().entityToDto(entity)).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
	public Response save(RestauranteDTO dto) {
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
	@Transactional
	public Response update(Long id, RestauranteDTO dto) {
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
    public RestauranteDTO getById(Long id) {
        return getConverter().entityToDto(getRepository().findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        getRepository().delete(getRepository().findByIdOptional(id).orElseThrow(NotFoundException::new));
        return Response.status(Status.OK).build();
    }

    @Override
	public RestauranteRepository getRepository() {
        return repository;
    }
    
    @Override
	public RestauranteConverter getConverter() {
        return converter;
    }

}