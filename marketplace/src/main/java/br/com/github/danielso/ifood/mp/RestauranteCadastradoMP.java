package br.com.github.danielso.ifood.mp;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.github.danielso.ifood.entities.Restaurante;

@ApplicationScoped
public class RestauranteCadastradoMP {

	@Incoming("restaurantes")
	public void receberRestaurante(String json) throws JsonMappingException, JsonProcessingException {
		
		Restaurante obj = new ObjectMapper().readValue(json, Restaurante.class);
		
		System.out.println("---------------------------------");
		System.out.println(json);
		System.out.println("---------------------------------");
		System.out.println(obj.toString());
	}
	
}
