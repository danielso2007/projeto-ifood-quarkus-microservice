package br.com.github.danielso.ifood.resources.prato;

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
	@DataSet("restaurantes-cenario-1.yml, pratos-cenario-1.yml")
	void testBuscarPratos_com_sucesso() {
		String resultado = given()
				.contentType(ContentType.JSON)
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/10/pratos")
				.then().statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(resultado);
	}
	
	@Test
	@DataSet("restaurantes-cenario-1.yml, pratos-cenario-1.yml")
	void testBuscarPrato_com_sort_e_order() {
		String resultado = given()
				.contentType(ContentType.JSON)
				.queryParam("sort", "id")
				.queryParam("order", "asc")
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/10/pratos")
				.then()
				.statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(resultado);
	}
	
	@Test
	@DataSet("restaurantes-cenario-1.yml, pratos-cenario-1.yml")
	void testBuscarPrato_com_sort_e_order_desc() {
		String resultado = given()
				.contentType(ContentType.JSON)
				.queryParam("sort", "id")
				.queryParam("order", "desc")
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/10/pratos")
				.then()
				.statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(resultado);
	}
	
	@Test
	@DataSet("restaurantes-cenario-1.yml, pratos-cenario-1.yml")
	void testBuscarPrato_com_sort_por_nome() {
		String resultado = given()
				.contentType(ContentType.JSON)
				.queryParam("sort", "nome")
				.queryParam("order", "desc")
				.when()
				.get(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/10/pratos")
				.then()
				.statusCode(200)
				.extract()
				.asString();
		Approvals.verifyJson(resultado);
	}

	@Test
	@DataSet()
	void testCadastroPrato() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when()
				.post(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/20/pratos")
				.then()
				.statusCode(201)
				.extract()
				.response();
		Assertions.assertEquals(201, response.statusCode());
	}
	
	@Test
	@DataSet()
	void testCadastroPrato_nome_nulo_erro_validacao() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBodyNomeNulo)
				.when()
				.post(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/10/pratos")
				.then()
				.statusCode(400)
				.extract()
				.response();
		Assertions.assertEquals(400, response.statusCode());
		
		Map<String, String> parameterViolations = response.jsonPath().getMap("parameterViolations[0]");
		System.out.println(response.jsonPath().getMap("parameterViolations[0]"));
		
		Assertions.assertEquals("PARAMETER", parameterViolations.get("constraintType"));
		Assertions.assertTrue(parameterViolations.get("message").contains("O nome não pode ser "));
        Assertions.assertEquals("save.dto.nome", parameterViolations.get("path"));
	}

	@Test
	@DataSet()
	void testCadastroPrato_preco_nulo_erro_validacao() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBodyPrecoNulo)
				.when()
				.post(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/10/pratos")
				.then()
				.statusCode(400)
				.extract()
				.response();
		Assertions.assertEquals(400, response.statusCode());
		Map<String, String> parameterViolations = response.jsonPath().getMap("parameterViolations[0]");
		System.out.println(response.jsonPath().getMap("parameterViolations[0]"));
		
		Assertions.assertEquals("PARAMETER", parameterViolations.get("constraintType"));
		Assertions.assertEquals("O preço não pode ser nulo", parameterViolations.get("message"));
        Assertions.assertEquals("save.dto.preco", parameterViolations.get("path"));
	}
	
	@Test
	@DataSet()
	void testAtualizarPrato() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when()
				.put(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/10/pratos" + "/10")
				.then()
				.extract()
				.response();
		Assertions.assertEquals(200, response.statusCode());
	}
	
	@Test
	@DataSet()
	void testAtualizarPrato_entidade_nao_existe_error() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when()
				.put(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/10/pratos" + "/999999")
				.then()
				.statusCode(404)
				.extract()
				.response();
		Assertions.assertEquals(404, response.statusCode());
	}
	
	@Test
	@DataSet()
	void testDeletandoPrato() {
		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when()
				.delete(Constants.API_VERSION + Constants.REST_RESTAURANTE + "/10/pratos" + "/20")
				.then()
				.extract()
				.response();
		Assertions.assertEquals(200, response.statusCode());
	}
	
}
