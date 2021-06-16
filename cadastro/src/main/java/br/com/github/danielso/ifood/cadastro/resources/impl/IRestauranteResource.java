package br.com.github.danielso.ifood.cadastro.resources.impl;

import br.com.github.danielso.ifood.cadastro.dto.RestauranteDTO;
import br.com.github.danielso.ifood.cadastro.entities.Restaurante;
import br.com.github.danielso.ifood.cadastro.repositories.RestauranteRepository;
import br.com.github.danielso.ifood.cadastro.resources.ISaveAndUpdateController;

public interface IRestauranteResource extends ISaveAndUpdateController<Restaurante, RestauranteDTO, Long, RestauranteRepository> {

}
