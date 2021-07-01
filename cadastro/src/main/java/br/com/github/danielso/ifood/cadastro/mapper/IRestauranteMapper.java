package br.com.github.danielso.ifood.cadastro.mapper;

import org.eclipse.microprofile.opentracing.Traced;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import br.com.github.danielso.ifood.cadastro.dto.AdicionarRestauranteDTO;
import br.com.github.danielso.ifood.cadastro.dto.RestauranteDTO;
import br.com.github.danielso.ifood.cadastro.entities.Restaurante;

@Traced
@Mapper(componentModel = "cdi")
public interface IRestauranteMapper {

	IRestauranteMapper INSTANCE = Mappers.getMapper(IRestauranteMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dataCriacao", ignore = true)
	@Mapping(target = "dataAtualizacao", ignore = true)
	@Mapping(target = "pratos", ignore = true)
	@Mapping(target = "localizacao.id", ignore = true)
	@Mapping(target = "localizacao.dataCriacao", ignore = true)
	@Mapping(target = "localizacao.dataAtualizacao", ignore = true)
	@Mapping(target = "localizacao.restaurante", ignore = true)
	public Restaurante toRestaurante(AdicionarRestauranteDTO dto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dataCriacao", ignore = true)
	@Mapping(target = "dataAtualizacao", ignore = true)
	@Mapping(target = "pratos", ignore = true)
	@Mapping(target = "localizacao.id", ignore = true)
	@Mapping(target = "localizacao.dataCriacao", ignore = true)
	@Mapping(target = "localizacao.dataAtualizacao", ignore = true)
	@Mapping(target = "localizacao.restaurante", ignore = true)
	public void toRestaurante(AdicionarRestauranteDTO dto, @MappingTarget Restaurante entity);

	public RestauranteDTO toRestauranteDTO(Restaurante entity);

}
