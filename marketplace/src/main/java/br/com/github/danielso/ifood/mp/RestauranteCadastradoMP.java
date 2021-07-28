package br.com.github.danielso.ifood.mp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.github.danielso.ifood.entities.Restaurante;
import io.quarkus.reactive.datasource.ReactiveDataSource;
import io.vertx.mutiny.pgclient.PgPool;

@ApplicationScoped
public class RestauranteCadastradoMP {

	@Inject
	@ReactiveDataSource("marketplace")
	@Named("marketplace")
	PgPool pgPool;
	
	@Incoming("restaurantes")
	public void receberRestaurante(String json) throws JsonProcessingException {
		
		var restaurante = new ObjectMapper().readValue(json, Restaurante.class);
		
//		System.out.println("---------------------------------");
//		System.out.println(json);
//		System.out.println("---------------------------------");
//		System.out.println(restaurante.toString());
		
		System.out.println("--------------- @Incoming ------------------");
		
		restaurante.persist(pgPool);
	}
	
}
