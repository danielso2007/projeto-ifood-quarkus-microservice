package br.com.github.danielso.ifood.cadastro.resources.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import br.com.github.danielso.ifood.cadastro.converter.ConverterDTO;
import br.com.github.danielso.ifood.cadastro.dto.BaseDTO;
import br.com.github.danielso.ifood.cadastro.entities.BaseAudit;
import br.com.github.danielso.ifood.cadastro.entities.BaseEntity;
import br.com.github.danielso.ifood.cadastro.resources.IBaseResource;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;

public abstract class BaseResource<E extends BaseEntity, D extends BaseDTO, K extends Serializable, R extends PanacheRepositoryBase<E, K>>  implements IBaseResource<E, D, K, R> {

    private static final String DEFAULT_ORDER = "asc";
	private static final String DEFAULT_SORT_COLUMN = "id";

	private R repository;
	private ConverterDTO<E, D> converter;

    protected BaseResource() {
	}
    
    protected BaseResource(R repository, ConverterDTO<E, D> converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<D> getAll(@QueryParam("sort") @DefaultValue(DEFAULT_SORT_COLUMN) String sortField, @QueryParam("order") @DefaultValue(DEFAULT_ORDER) String order) {
        String[] fields = sortField.contains(",") ? sortField.split(",") : sortField.split(";");
        var sort = Sort.ascending(fields);
        if (!order.equals(DEFAULT_ORDER)) {
            sort = Sort.descending(fields);
        }
        return getRepository().listAll(sort).stream().map(entity -> getConverter().entityToDto(entity)).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
	public void save(@Valid D dto) {
    	var entity = getConverter().dtoToEntity(dto);
    	entity.setId(null);
    	if (entity instanceof BaseAudit) {
			((BaseAudit) entity).setDataCriacao(null);
			((BaseAudit) entity).setDataAtualizacao(null);
		}
    	getRepository().persist(entity);
	}

	@Override
	@Transactional
	public void update(@PathParam(DEFAULT_SORT_COLUMN) K id, @Valid D dto) {
        getRepository().findByIdOptional(id).ifPresentOrElse(entity -> {
			var p = getConverter().dtoToEntity(dto, entity);
			p.setId(entity.getId());
			if (entity instanceof BaseAudit) {
				((BaseAudit) p).setDataCriacao(((BaseAudit) entity).getDataCriacao());
				((BaseAudit) p).setDataAtualizacao(((BaseAudit) entity).getDataAtualizacao());
			}
			getRepository().persist(p);
		}, NotFoundException::new);
	}

    @Override
    public D getById(@PathParam(DEFAULT_SORT_COLUMN) K id) {
        return getConverter().entityToDto(getRepository().findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    @Override
    @Transactional
    public void delete(@PathParam(DEFAULT_SORT_COLUMN) K id) {
        getRepository().delete(getRepository().findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    @Override
	public R getRepository() {
        return repository;
    }
    
    @Override
	public ConverterDTO<E, D> getConverter() {
        return converter;
    }
}
