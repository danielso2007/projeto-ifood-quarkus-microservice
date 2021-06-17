package br.com.github.danielso.ifood.cadastro.repositories;

import javax.enterprise.context.ApplicationScoped;

import br.com.github.danielso.ifood.cadastro.entities.Localizacao;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class LocalizacaoRepository implements PanacheRepositoryBase<Localizacao, Long> {
}
