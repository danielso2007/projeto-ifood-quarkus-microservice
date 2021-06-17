package br.com.github.danielso.ifood.cadastro.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.github.danielso.ifood.cadastro.converter.RestauranteConverter;
import br.com.github.danielso.ifood.cadastro.dto.RestauranteDTO;
import br.com.github.danielso.ifood.cadastro.entities.Restaurante;
import br.com.github.danielso.ifood.cadastro.repositories.RestauranteRepository;
import br.com.github.danielso.ifood.cadastro.resources.impl.BaseResource;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource extends BaseResource<Restaurante, RestauranteDTO, Long, RestauranteRepository> {

	public RestauranteResource() {
	}
	
	@Inject
	public RestauranteResource(RestauranteRepository repository, RestauranteConverter converter) {
		super(repository, converter);
	}

}