package br.com.github.danielso.ifood.cadastro.converter;

import javax.enterprise.context.ApplicationScoped;

import br.com.github.danielso.ifood.cadastro.dto.RestauranteDTO;
import br.com.github.danielso.ifood.cadastro.entities.Restaurante;

@ApplicationScoped
public class RestauranteConverter implements ConverterDTO<Restaurante, RestauranteDTO> {

	@Override
	public RestauranteDTO entityToDto(Restaurante entity) {
		var dto = new RestauranteDTO();
		dto.id(entity.getId());
		dto.cnpj(entity.getCnpj());
		dto.proprietario(entity.getProprietario());
		dto.nome(entity.getNome());
		dto.dataCriacao(entity.getDataCriacao());
		dto.dataAtualizacao(entity.getDataAtualizacao());		
		return dto;
	}

	@Override
	public Restaurante dtoToEntity(RestauranteDTO dto, Restaurante persistenceEntity) {
		var entity = new Restaurante();
		if (persistenceEntity != null) {
			entity = persistenceEntity;
		}
		entity.id(dto.getId());
		entity.cnpj(dto.getCnpj());
		entity.proprietario(dto.getProprietario());
		entity.nome(dto.getNome());
		dto.dataCriacao(dto.getDataCriacao());
		entity.dataAtualizacao(dto.getDataAtualizacao());		
		return entity;
	}

	@Override
	public Restaurante dtoToEntity(RestauranteDTO dto) {
		return dtoToEntity(dto, null);
	}

}
