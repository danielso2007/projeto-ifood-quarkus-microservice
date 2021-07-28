package br.com.github.danielso.ifood;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.github.danielso.ifood.commons.Constants;
import br.com.github.danielso.ifood.testlifecyclemanager.MarketplaceTestLifecycleManager;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

@QuarkusTest
@QuarkusTestResource(MarketplaceTestLifecycleManager.class)
class PratosResourceTest {

    @Test
    void testBuscarPratosEndpoint() {
        Response response = given()
				.header("Content-type", "application/json")
				.and()
				.when()
				.get(Constants.API_VERSION + Constants.REST_PRATOS)
				.then()
				.statusCode(200)
				.extract()
				.response();
		Assertions.assertEquals(200, response.statusCode());
    }

}