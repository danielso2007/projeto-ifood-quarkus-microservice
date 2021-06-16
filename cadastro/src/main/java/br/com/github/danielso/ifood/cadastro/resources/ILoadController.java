package br.com.github.danielso.ifood.cadastro.resources;

import java.io.Serializable;

import br.com.github.danielso.ifood.cadastro.entities.BaseEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface ILoadController <E extends BaseEntity, K extends Serializable, R extends PanacheRepositoryBase<E, K>> {

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}", produces = { IConstantsAPI.APPLICATION_JSON_UTF_8, IConstantsAPI.APPLICATION_XML_UTF_8 })
	@Operation(summary = "Obter um registro pelo id.", description = "Retorna um registro.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registro carregado com sucesso."),
			@ApiResponse(responseCode = "404", description = "Registro não encontrado."),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	ResponseEntity<M> find(@Parameter(description="O id do registro a ser obtido. Não pode ser vazio.", required=true) K id);
	
}
