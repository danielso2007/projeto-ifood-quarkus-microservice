package br.com.github.danielso.ifood.marketplace.mp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.github.danielso.ifood.marketplace.entities.Restaurante;
import io.vertx.mutiny.pgclient.PgPool;

@ApplicationScoped
public class RestauranteCadastradoMP {

	private static final Logger LOG = Logger.getLogger(RestauranteCadastradoMP.class);

	@Inject
	PgPool pgPool;

	@Incoming("restaurantes")
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
