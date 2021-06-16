package br.com.github.danielso.ifood.cadastro.resources.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import br.com.github.danielso.ifood.cadastro.dto.BaseDTO;
import br.com.github.danielso.ifood.cadastro.entities.BaseEntity;
import br.com.github.danielso.ifood.cadastro.resources.IBaseResource;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;

public abstract class BaseResource<E extends BaseEntity, D extends BaseDTO, K extends Serializable, R extends PanacheRepositoryBase<E, K>>  implements IBaseResource<E, D, K, R> {

    private R repository;

    protected BaseResource() {
	}
    
    protected BaseResource(R repository) {
        this.repository = repository;
    }

    @Override
    public List<E> getAll(@QueryParam("sort") @DefaultValue("id") String sortField, @QueryParam("order") @DefaultValue("asc") String order) {
        String[] fields = sortField.contains(",") ? sortField.split(",") : sortField.split(";");
        var sort = Sort.ascending(fields);
        if (!order.equals("asc")) {
            sort = Sort.descending(fields);
        }
        return getRepository().listAll(sort);
    }
    
    @Override
	public void save(@Valid D dto) {
	}

	@Override
	public void update(@PathParam("id") K id, @Valid D dto) {
		Optional<E> op = getRepository().findByIdOptional(id);
		if (op.isPresent()) {
			var p = op.get();
			getRepository().persist(p);
		} else {
			throw new NotFoundException(String.format("Restaurante com ID %d não encontrado.", id));
		}
	}

    @Override
    public E getById(@PathParam("id") K id) {
        Optional<E> op = getRepository().findByIdOptional(id);
        if (op.isPresent()) {
            return op.get();
        }
        throw new NotFoundException();
    }

    @Override
    public void delete(@PathParam("id") K id) {
        Optional<E> op = getRepository().findByIdOptional(id);
        op.ifPresentOrElse(getRepository()::delete, () -> {throw new NotFoundException();});
    }

    @Override
	public R getRepository() {
        return repository;
    }
}
