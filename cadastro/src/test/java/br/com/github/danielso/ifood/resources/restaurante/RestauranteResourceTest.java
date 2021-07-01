package br.com.github.danielso.ifood.resources.restaurante;

import java.util.Map;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.github.danielso.ifood.cadastro.commons.Constants;
import br.com.github.danielso.ifood.testlifecyclemanager.CadastroTestLifecycleManager;
import br.com.github.danielso.ifood.utils.TokenUtils;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
class RestauranteResourceTest {

	private static String requestBody = "{\n"
			+ "  \"cnpj\": \"76115833000160\",\n"
			+ "  \"nome\": \"Outback\",\n"
			+ "  \"proprietario\": \"Daniel Oliveira\",\n"
			+ "  \"localizacao\": {\"latitude\": -12.93138, \"longitude\": -45.46544}\n"
			+ "}";
	
	private static String requestBodyPut = "{\n"
			+ "  \"cnpj\": \"76115833000160\",\n"
			+ "  \"nome\": \"Outback 2\",\n"
			+ "  \"proprietario\": \"Daniel da Silva\",\n"
			+ "  \"localizacao\": {\"latitude\": -12.93138, \"longitude\": -45.46544}\n"
			+ "}";
	
	private static String requestBodyNomeNulo = "{\n"
			+ "  \"cnpj\": \"76115833000160\",\n"
			+ "  \"nome\": null,\n"
			+ "  \"proprietario\": \"Daniel Oliveira\",\n"
			+ "  \"localizacao\": {\"latitude\": -12.93138, \"longitude\": -45.46544}\n"
			+ "}";
	
	private static String requestBodyCnpjInvalido = "{\n"
			+ "  \"cnpj\": \"125478569ed654\",\n"
			+ "  \"nome\": \"Flávio\",\n"
			+ "  \"proprietario\": \"Daniel Oliveira\",\n"
			+ "  \"localizacao\": {\"latitude\": -12.93138, \"longitude\": -45.46544}\n"
			+ "}";
	
	private String token;
	
	@BeforeEach
	void gerarToken() {
		try {
			token = TokenUtils.generateTokenString("/JWTProprietarioClaims.json", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private RequestSpecification given() {
		return  RestAssured.given()
				.headers("Authorization", "Bearer " + token, "Content-Type", ContentType.JSON, "Accept", ContentType.JSON);
	}
	
	@Test
	void testBuscarRestaurante_com_sucesso() throws JsonMappingException, JsonProcessingException {
		String resultado = given()
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE)
				.then().statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(ajustarDataParaZeroHorasEhMinutos(resultado));
	}

	@Test
	void testBuscarRestaurante_com_sort_e_order() throws JsonMappingException, JsonProcessingException {
		String resultado = given()
				.queryParam("sort", "id")
				.queryParam("order", "asc")
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE)
				.then()
				.statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(ajustarDataParaZeroHorasEhMinutos(resultado));
	}
	
	@Test
	void testBuscarRestaurante_com_sort_e_order_desc() throws JsonMappingException, JsonProcessingException {
		String resultado = given()
				.queryParam("sort", "id")
				.queryParam("order", "desc")
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE)
				.then()
				.statusCode(200)
				.extract()
				.asString();
		
		Approvals.verifyJson(ajustarDataParaZeroHorasEhMinutos(resultado));
	}
	
	@Test
	void testBuscarRestaurante_com_sort_por_nome() throws JsonMappingException, JsonProcessingException {
		String resultado = given()
				.queryParam("sort", "nome")
				.queryParam("order", "desc")
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE)
				.then()
				.statusCode(200)
				.extract()
				.asString();
		 Approvals.verifyJson(ajustarDataParaZeroHorasEhMinutos(resultado));
	}
	
	@Test
	void testCadastroRestaurante() {
		Response response = given()
				.and()
				.body(requestBody)
				.when()
				.post(Constants.API_VERSION + Constants.REST_RESTAURANTE)
				.then()
				.statusCode(201)
				.extract()
				.response();
		Assertions.assertEquals(201, response.statusCode());
	}
	
	@Test
	void testCadastroRestaurante_nome_nulo_erro_validacao() {
		Response response = given()
				.contentType(ContentType.JSON)
				.and()
				.body(requestBodyNomeNulo)
				.when()
				.post(Constants.API_VERSION + Constants.REST_RESTAURANTE)
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
	void testCadastroRestaurante_cnpj_invalido_erro_validacao() {
		Response response = given()
				.contentType(ContentType.JSON)
				.and()
				.body(requestBodyCnpjInvalido)
				.when()
				.post(Constants.API_VERSION + Constants.REST_RESTAURANTE)
				.then()
				.statusCode(400)
				.extract()
				.response();
		
		Map<String, String> parameterViolations = response.jsonPath().getMap("errors[0]");
		
		Assertions.assertEquals(400, response.statusCode());
		Assertions.assertEquals("cnpj", parameterViolations.get("atributo"));
		Assertions.assertEquals("número do registro de contribuinte corporativo brasileiro (CNPJ) inválido", parameterViolations.get("mensagem"));
	}
	
	@Test
	void testAtualizarRestaurante() {
		Response response = given()
				.and()
				.body(requestBodyPut)
				.when()
				.put(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/1")
				.then()
				.extract()
				.response();
		Assertions.assertEquals(200, response.statusCode());
	}
	
	@Test
	void testAtualizarRestaurante_entidade_nao_existe_error() {
		Response response = given()
				.headers("Authorization", "Bearer " + token, "Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
				.and()
				.body(requestBodyPut)
				.when()
				.put(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/999999")
				.then()
				.statusCode(404)
				.extract()
				.response();
		Assertions.assertEquals(404, response.statusCode());
	}
	
	@Test
	void testDeletandoRestaurante() {
		Response response = given()
				.and()
				.body(requestBody)
				.when()
				.delete(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/2")
				.then()
				.extract()
				.response();
		Assertions.assertEquals(200, response.statusCode());
	}
	
	@Test
	void testBuscarRestaurante_acesso_nao_autorizado() {
		Response response = RestAssured.given()
				.queryParam("sort", "id")
				.queryParam("order", "desc")
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE)
				.then()
				.statusCode(401)
				.extract()
				.response();
		Assertions.assertEquals(401, response.statusCode());
	}
	
	private String ajustarDataParaZeroHorasEhMinutos(String resultado) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(resultado);
		if (rootNode.isArray()) {
			for (final JsonNode objNode : rootNode) {
		        ((ObjectNode)objNode).put("dataCriacao", "2021-01-01T03:00:00");
		        ((ObjectNode)objNode).put("dataAtualizacao", "2021-01-01T03:00:00");
		        JsonNode objNodeLocalizacao = objNode.get("localizacao");
		        ((ObjectNode)objNodeLocalizacao).put("dataCriacao", "2021-01-01T03:00:00");
		        ((ObjectNode)objNodeLocalizacao).put("dataAtualizacao", "2021-01-01T03:00:00");
		    }
		}
		return rootNode.toString();
	}
}