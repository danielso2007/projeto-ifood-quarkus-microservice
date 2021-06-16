package br.com.github.danielso.ifood.cadastro.resources;

import br.com.github.danielso.ifood.cadastro.dto.BaseDTO;
import br.com.github.danielso.ifood.cadastro.entities.BaseEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.Serializable;
import java.util.Optional;

public abstract class LoadController<E extends BaseEntity, D extends BaseDTO, K extends Serializable, R extends PanacheRepositoryBase<E, K>> extends SearchController<E, D, K, R> implements ILoadController<E, D, K, R> {

    public LoadController(R repository) {
        super(repository);
    }

    @GET
    @Path("{id}")
    public E getById(@PathParam("id") K id) {
        Optional<E> op = getRepository().findByIdOptional(id);
        if (op.isPresent()) {
            return op.get();
        }
        throw new NotFoundException();
    }

}
