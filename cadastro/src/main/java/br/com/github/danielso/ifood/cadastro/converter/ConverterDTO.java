package br.com.github.danielso.ifood.cadastro.converter;

import br.com.github.danielso.ifood.cadastro.dto.BaseDTO;
import br.com.github.danielso.ifood.cadastro.entities.BaseEntity;

public interface ConverterDTO<E extends BaseEntity, D extends BaseDTO> {

	D entityToDto(E entity);
	E dtoToEntity(D dto, E persistenceEntity);
	E dtoToEntity(D dto);
	
}
