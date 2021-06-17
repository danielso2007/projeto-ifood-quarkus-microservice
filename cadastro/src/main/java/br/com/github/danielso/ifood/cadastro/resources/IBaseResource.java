package br.com.github.danielso.ifood.cadastro.resources;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.Response;

import br.com.github.danielso.ifood.cadastro.converter.ConverterDTO;
import br.com.github.danielso.ifood.cadastro.dto.BaseDTO;
import br.com.github.danielso.ifood.cadastro.entities.BaseEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface IBaseResource<E extends BaseEntity, D extends BaseDTO, K extends Serializable, R extends PanacheRepositoryBase<E, K>, C extends ConverterDTO<E, D>> {

	List<D> getAll(String sortField, String order);

	Response save(D dto);

	Response update(K id, D dto);

	D getById(K id);

	Response delete(K id);

	R getRepository();

	C getConverter();

}
