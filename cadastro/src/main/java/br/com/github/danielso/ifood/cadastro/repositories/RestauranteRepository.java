package br.com.github.danielso.ifood.cadastro.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.opentracing.Traced;

import br.com.github.danielso.ifood.cadastro.entities.Restaurante;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@Traced
@ApplicationScoped
public class RestauranteRepository implements PanacheRepositoryBase<Restaurante, Long> {
}
