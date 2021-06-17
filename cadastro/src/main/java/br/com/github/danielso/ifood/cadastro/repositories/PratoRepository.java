package br.com.github.danielso.ifood.cadastro.repositories;

import javax.enterprise.context.ApplicationScoped;

import br.com.github.danielso.ifood.cadastro.entities.Prato;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class PratoRepository implements PanacheRepositoryBase<Prato, Long> {
}
