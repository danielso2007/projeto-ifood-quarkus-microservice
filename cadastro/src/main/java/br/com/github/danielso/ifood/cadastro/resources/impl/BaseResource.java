package br.com.github.danielso.ifood.cadastro.resources.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.github.danielso.ifood.cadastro.converter.ConverterDTO;
import br.com.github.danielso.ifood.cadastro.dto.BaseDTO;
import br.com.github.danielso.ifood.cadastro.entities.BaseAudit;
import br.com.github.danielso.ifood.cadastro.entities.BaseEntity;
import br.com.github.danielso.ifood.cadastro.resources.IBaseResource;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;

public abstract class BaseResource<E extends BaseEntity, D extends BaseDTO, K extends Serializable, R extends PanacheRepositoryBase<E, K>, C extends ConverterDTO<E, D>>  implements IBaseResource<E, D, K, R, C> {

    private static final String DEFAULT_ORDER = "asc";

	private R repository;
	private C converter;

    protected BaseResource() {
	}
    
    protected BaseResource(R repository, C converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<D> getAll(String sortField, String order) {
        String[] fields = sortField.contains(",") ? sortField.split(",") : sortField.split(";");
        var sort = Sort.ascending(fields);
        if (!order.equals(DEFAULT_ORDER)) {
            sort = Sort.descending(fields);
        }
        return getRepository().listAll(sort).stream().map(entity -> getConverter().entityToDto(entity)).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
	public Response save(D dto) {
    	var entity = getConverter().dtoToEntity(dto);
    	entity.setId(null);
    	if (entity instanceof BaseAudit) {
			((BaseAudit) entity).setDataCriacao(null);
			((BaseAudit) entity).setDataAtualizacao(null);
		}
    	getRepository().persist(entity);
    	return Response.status(Status.CREATED).build();
	}

	@Override
	@Transactional
	public Response update(K id, D dto) {
        getRepository().findByIdOptional(id).ifPresentOrElse(entity -> {
			var p = getConverter().dtoToEntity(dto, entity);
			if (entity instanceof BaseAudit) {
				((BaseAudit) p).setDataCriacao(((BaseAudit) entity).getDataCriacao());
				((BaseAudit) p).setDataAtualizacao(((BaseAudit) entity).getDataAtualizacao());
			}
			getRepository().persist(p);
		}, NotFoundException::new);
        return Response.status(Status.OK).build();
	}

    @Override
    public D getById(K id) {
        return getConverter().entityToDto(getRepository().findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    @Override
    @Transactional
    public Response delete(K id) {
        getRepository().delete(getRepository().findByIdOptional(id).orElseThrow(NotFoundException::new));
        return Response.status(Status.OK).build();
    }

    @Override
	public R getRepository() {
        return repository;
    }
    
    @Override
	public C getConverter() {
        return converter;
    }
}
