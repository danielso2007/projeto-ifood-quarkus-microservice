package br.com.github.danielso.ifood.resources.restaurante;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import br.com.github.danielso.ifood.cadastro.commons.Constants;
import br.com.github.danielso.ifood.testlifecyclemanager.CadastroTestLifecycleManager;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
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
	
	@Test
	@DataSet("restaurantes-cenario-1.yml")
	void testBuscarRestaurante_com_sucesso() {
		String resultado = given()
				.contentType(ContentType.JSON)
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE)
				.then().statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(resultado);
	}

	@Test
	@DataSet("restaurantes-cenario-1.yml")
	void testBuscarRestaurante_com_sort_e_order() {
		String resultado = given()
				.contentType(ContentType.JSON)
				.queryParam("sort", "id")
				.queryParam("order", "asc")
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE)
				.then()
				.statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(resultado);
	}
	
	@Test
	@DataSet("restaurantes-cenario-1.yml")
	void testBuscarRestaurante_com_sort_e_order_desc() {
		String resultado = given()
				.contentType(ContentType.JSON)
				.queryParam("sort", "id")
				.queryParam("order", "desc")
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE)
				.then()
				.statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(resultado);
	}
	
	@Test
	@DataSet("restaurantes-cenario-1.yml")
	void testBuscarRestaurante_com_sort_por_nome() {
		String resultado = given()
				.contentType(ContentType.JSON)
				.queryParam("sort", "nome")
				.queryParam("order", "desc")
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE)
				.then()
				.statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(resultado);
	}

	@Test
	@DataSet()
	void testCadastroRestaurante() {
		Response response = given()
				.header("Content-type", "application/json")
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
	@DataSet()
	void testCadastroRestaurante_nome_nulo_erro_validacao() {
		Response response = given()
				.header("Content-type", "application/json")
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
	@DataSet()
	void testCadastroRestaurante_cnpj_invalido_erro_validacao() {
		Response response = given()
				.header("Content-type", "application/json")
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
	@DataSet()
	void testAtualizarRestaurante() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBodyPut)
				.when()
				.put(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/10")
				.then()
				.extract()
				.response();
		Assertions.assertEquals(200, response.statusCode());
	}
	
	@Test
	@DataSet()
	void testAtualizarRestaurante_entidade_nao_existe_error() {
		Response response = given()
				.header("Content-type", "application/json")
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
	@DataSet()
	void testDeletandoRestaurante() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when()
				.delete(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/10")
				.then()
				.extract()
				.response();
		Assertions.assertEquals(200, response.statusCode());
	}
}