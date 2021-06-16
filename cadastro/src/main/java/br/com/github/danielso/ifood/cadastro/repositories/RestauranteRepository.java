package br.com.github.danielso.ifood.cadastro.repositories;

import br.com.github.danielso.ifood.cadastro.entities.Restaurante;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RestauranteRepository implements PanacheRepositoryBase<Restaurante, Long> {
}
