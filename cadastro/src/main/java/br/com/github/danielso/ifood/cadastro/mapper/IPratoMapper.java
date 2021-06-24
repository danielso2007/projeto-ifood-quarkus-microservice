package br.com.github.danielso.ifood.cadastro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import br.com.github.danielso.ifood.cadastro.dto.AdicionarPratoDTO;
import br.com.github.danielso.ifood.cadastro.dto.PratoDTO;
import br.com.github.danielso.ifood.cadastro.entities.Prato;

@Mapper(componentModel = "cdi")
public interface IPratoMapper {

	IPratoMapper INSTANCE = Mappers.getMapper(IPratoMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dataCriacao", ignore = true)
	@Mapping(target = "dataAtualizacao", ignore = true)
	@Mapping(target = "restaurante", ignore = true)
	public Prato toPrato(AdicionarPratoDTO dto);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dataCriacao", ignore = true)
	@Mapping(target = "dataAtualizacao", ignore = true)
	@Mapping(target = "restaurante", ignore = true)
	public void toPrato(AdicionarPratoDTO dto, @MappingTarget Prato entity);

	public PratoDTO toPratoDTO(Prato entity);

}
