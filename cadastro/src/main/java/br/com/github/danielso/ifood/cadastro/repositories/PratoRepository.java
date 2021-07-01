package br.com.github.danielso.ifood.cadastro.repositories;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.opentracing.Traced;

import br.com.github.danielso.ifood.cadastro.entities.Prato;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;

@Traced
@ApplicationScoped
public class PratoRepository implements PanacheRepositoryBase<Prato, Long> {

	public List<Prato> findByRestaurante(Long idRestaurante, Sort sort) {
		return list("restaurante.id", sort, idRestaurante);
	}

	public Optional<Prato> findByIdAndRestauranteId(Long idRestaurante, Long idPrato) {
		return find("id = :idPrato AND restaurante.id = :idRestaurante",
				Parameters.with("idPrato", idPrato).and("idRestaurante", idRestaurante).map()).firstResultOptional();
	}

}
