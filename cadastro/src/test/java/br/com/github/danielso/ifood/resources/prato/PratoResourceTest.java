package br.com.github.danielso.ifood.resources.prato;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.github.danielso.ifood.cadastro.commons.Constants;
import br.com.github.danielso.ifood.testlifecyclemanager.CadastroTestLifecycleManager;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
class PratoResourceTest {

	private static String requestBody = "{\n"
			+ "    \"descricao\": \"Feito com os melhores ingredientes\",\n"
			+ "    \"nome\": \"Macarrão ao molhor vermelho\",\n"
			+ "    \"preco\": 58.98\n"
			+ "  }";
	private static String requestBodyPrecoNulo = "{\n"
			+ "    \"descricao\": \"Feito com os melhores ingredientes\",\n"
			+ "    \"nome\": \"Macarrão ao molhor vermelho\" "
			+ "  }";
	private static String requestBodyNomeNulo = "{\n"
			+ "    \"descricao\": \"Feito com os melhores ingredientes\",\n"
			+ "    \"preco\": 58.98\n"
			+ "  }";
	
	@Test
	void testBuscarPratos_com_sucesso() throws JsonMappingException, JsonProcessingException {
		String resultado = given()
				.contentType(ContentType.JSON)
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/1/pratos")
				.then().statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(ajustarDataParaZeroHorasEhMinutos(resultado));
	}
	
	@Test
	void testBuscarPrato_com_sort_e_order() throws JsonMappingException, JsonProcessingException {
		String resultado = given()
				.contentType(ContentType.JSON)
				.queryParam("sort", "id")
				.queryParam("order", "asc")
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/1/pratos")
				.then()
				.statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(ajustarDataParaZeroHorasEhMinutos(resultado));
	}
	
	@Test
	void test_prato_find_by_id() throws JsonMappingException, JsonProcessingException {
		String resultado = given()
				.contentType(ContentType.JSON)
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/1/pratos/1")
				.then()
				.statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(ajustarDataParaZeroHorasEhMinutos(resultado));
	}
	
	@Test
	void testBuscarPrato_com_sort_e_order_desc() throws JsonMappingException, JsonProcessingException {
		String resultado = given()
				.contentType(ContentType.JSON)
				.queryParam("sort", "id")
				.queryParam("order", "desc")
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/1/pratos")
				.then()
				.statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(ajustarDataParaZeroHorasEhMinutos(resultado));
	}
	
	@Test
	void testBuscarPrato_com_sort_por_nome() throws JsonMappingException, JsonProcessingException {
		String resultado = given()
				.contentType(ContentType.JSON)
				.queryParam("sort", "nome")
				.queryParam("order", "desc")
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/1/pratos")
				.then()
				.statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(ajustarDataParaZeroHorasEhMinutos(resultado));
	}

	@Test
	void testCadastroPrato() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when()
				.post(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/1/pratos")
				.then()
				.statusCode(201)
				.extract()
				.response();
		Assertions.assertEquals(201, response.statusCode());
	}
	
	@Test
	void testCadastroPrato_nome_nulo_erro_validacao() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBodyNomeNulo)
				.when()
				.post(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/1/pratos")
				.then()
				.statusCode(400)
				.extract()
				.response();
        
        Map<String, String> parameterViolations = response.jsonPath().getMap("errors[0]");
		
		Assertions.assertEquals(400, response.statusCode());
		Assertions.assertEquals("nome", parameterViolations.get("atributo"));
		Assertions.assertTrue(parameterViolations.get("mensagem").contains("O nome não pode ser "));
	}

	@Test
	void testCadastroPrato_preco_nulo_erro_validacao() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBodyPrecoNulo)
				.when()
				.post(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/1/pratos")
				.then()
				.statusCode(400)
				.extract()
				.response();
		
		Map<String, String> parameterViolations = response.jsonPath().getMap("errors[0]");
		
		Assertions.assertEquals(400, response.statusCode());
		Assertions.assertEquals("preco", parameterViolations.get("atributo"));
		Assertions.assertEquals("O preço não pode ser nulo", parameterViolations.get("mensagem"));
	}
	
	@Test
	void testAtualizarPrato() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when()
				.put(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/1/pratos" + "/1")
				.then()
				.extract()
				.response();
		Assertions.assertEquals(500, response.statusCode());
	}
	
	@Test
	void testAtualizarPrato_entidade_nao_existe_error() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when()
				.put(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/1/pratos" + "/999999")
				.then()
				.statusCode(404)
				.extract()
				.response();
		Assertions.assertEquals(404, response.statusCode());
	}
	
	@Test
	void testDeletandoPrato() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when()
				.delete(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/4/pratos" + "/3")
				.then()
				.extract()
				.response();
		Assertions.assertEquals(200, response.statusCode());
	}
	
	private String ajustarDataParaZeroHorasEhMinutos(String resultado) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(resultado);
		if (rootNode.isArray()) {
			for (final JsonNode objNode : rootNode) {
		        ((ObjectNode)objNode).put("dataCriacao", "2021-01-01T03:00:00");
		        ((ObjectNode)objNode).put("dataAtualizacao", "2021-01-01T03:00:00");
		    }
		}
		return rootNode.toString();
	}
	
}
