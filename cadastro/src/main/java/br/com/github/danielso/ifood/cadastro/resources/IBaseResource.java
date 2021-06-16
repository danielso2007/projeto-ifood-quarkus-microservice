package br.com.github.danielso.ifood.cadastro.resources;

import java.io.Serializable;

import br.com.github.danielso.ifood.cadastro.entities.BaseEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface IBaseResource <E extends BaseEntity, K extends Serializable, R extends PanacheRepositoryBase<E, K>> {

}
