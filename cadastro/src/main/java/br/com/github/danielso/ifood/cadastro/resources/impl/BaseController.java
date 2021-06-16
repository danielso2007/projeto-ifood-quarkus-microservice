package br.com.github.danielso.ifood.cadastro.resources;

import br.com.github.danielso.ifood.cadastro.dto.BaseDTO;
import br.com.github.danielso.ifood.cadastro.entities.BaseEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.io.Serializable;

public abstract class BaseController<E extends BaseEntity, D extends BaseDTO, K extends Serializable, R extends PanacheRepositoryBase<E, K>>  implements IBaseResource<E, D, K, R> {

    private final R repository;

    public BaseController(R repository) {
        this.repository = repository;
    }

    public R getRepository() {
        return repository;
    }
}
