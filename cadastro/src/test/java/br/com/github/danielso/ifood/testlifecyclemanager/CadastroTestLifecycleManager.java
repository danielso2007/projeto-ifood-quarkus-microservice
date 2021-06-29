package br.com.github.danielso.ifood.testlifecyclemanager;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class CadastroTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

	private static final String USER = "usuario";
	private static final String PASSWORD = "senha";
	private static final String DATABASE = "ifood";
	
	private static final DockerImageName POSTGRES_IMAGE = DockerImageName.parse("postgres:13.3-alpine");
    private GenericContainer<?> postgresContainer = new GenericContainer<>(POSTGRES_IMAGE);


	@Override
	public Map<String, String> start() {
		postgresContainer
		        .withEnv("POSTGRES_USER", USER)
				.withEnv("POSTGRES_PASSWORD", PASSWORD)
				.withExposedPorts(5432)
				.withEnv("POSTGRES_DB", DATABASE);
		postgresContainer.start();

		Map<String, String> properties = new HashMap<>();
		properties.put("quarkus.datasource.username", USER);
		properties.put("quarkus.datasource.password", PASSWORD);
		properties.put("quarkus.datasource.jdbc.url", "jdbc:postgresql://" 
			+ postgresContainer.getHost() 
			+ ":" 
			+ postgresContainer.getFirstMappedPort() 
			+ "/" + DATABASE);

		return properties;
	}

	@Override
	public void stop() {
		if (postgresContainer != null && postgresContainer.isRunning()) {
			postgresContainer.stop();
		}
	}

}
