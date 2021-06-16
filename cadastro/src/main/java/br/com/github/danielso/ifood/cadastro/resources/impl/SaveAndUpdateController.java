package br.com.github.danielso.ifood.cadastro.resources;

import br.com.github.danielso.ifood.cadastro.dto.BaseDTO;
import br.com.github.danielso.ifood.cadastro.entities.BaseEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.io.Serializable;

public abstract class SaveAndUpdateController<E extends BaseEntity, D extends BaseDTO, K extends Serializable, R extends PanacheRepositoryBase<E, K>> extends DeleteController<E, D, K, R> implements ISaveAndUpdateController<E, D, K, R> {
    public SaveAndUpdateController(R repository) {
        super(repository);
    }
}
