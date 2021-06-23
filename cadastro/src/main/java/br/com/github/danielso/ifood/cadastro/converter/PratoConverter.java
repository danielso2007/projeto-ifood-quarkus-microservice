package br.com.github.danielso.ifood.cadastro.converter;

import javax.enterprise.context.ApplicationScoped;

import br.com.github.danielso.ifood.cadastro.dto.PratoDTO;
import br.com.github.danielso.ifood.cadastro.entities.Prato;

@ApplicationScoped
public class PratoConverter implements ConverterDTO<Prato, PratoDTO> {

	@Override
	public PratoDTO entityToDto(Prato entity) {
		var dto = new PratoDTO();
		dto.id(entity.getId());
		dto.nome(entity.getNome());
		dto.descricao(entity.getDescricao());
		dto.setPreco(entity.getPreco());
		dto.dataCriacao(entity.getDataCriacao());
		dto.dataAtualizacao(entity.getDataAtualizacao());
		return dto;
	}

	@Override
	public Prato dtoToEntity(PratoDTO dto, Prato persistenceEntity) {
		var entity = new Prato();
		if (persistenceEntity != null) {
			entity = persistenceEntity;
		} else {
			entity.dataCriacao(dto.getDataCriacao());
			entity.dataAtualizacao(dto.getDataAtualizacao());		
		}
		entity.nome(dto.getNome());
		entity.descricao(dto.getDescricao());
		entity.setPreco(dto.getPreco());
		return entity;
	}

	@Override
	public Prato dtoToEntity(PratoDTO dto) {
		return dtoToEntity(dto, null);
	}

}
