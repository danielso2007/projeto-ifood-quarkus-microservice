package br.com.github.danielso.ifood.mp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.github.danielso.ifood.entities.Restaurante;
import io.quarkus.reactive.datasource.ReactiveDataSource;
import io.vertx.mutiny.pgclient.PgPool;

@ApplicationScoped
public class RestauranteCadastradoMP {

	private static final Logger LOG = Logger.getLogger(RestauranteCadastradoMP.class);

	@Inject
	@ReactiveDataSource("marketplace")
	@Named("marketplace")
	PgPool pgPool;

	@Incoming("restaurantes")
	@Transactional
	public void receberRestaurante(String json) throws JsonProcessingException {

		var restaurante = new ObjectMapper().readValue(json, Restaurante.class);

//		LOG.info("---------------------------------");
//		LOG.info(json);
//		LOG.info("---------------------------------");
//		LOG.info(restaurante.toString());

		LOG.info("--------------- @Incoming ------------------");

		restaurante.persist(pgPool);
	}

}
