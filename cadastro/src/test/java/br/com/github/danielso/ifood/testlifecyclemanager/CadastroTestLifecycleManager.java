package br.com.github.danielso.ifood.testlifecyclemanager;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class CadastroTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

	public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:13.3-alpine");

	@Override
	public Map<String, String> start() {
		POSTGRES.start();
		Map<String, String> properties = new HashMap<String, String>();

		properties.put("quarkus.datasource.url", POSTGRES.getJdbcUrl());
		properties.put("quarkus.datasource.username", "postgres");
		properties.put("quarkus.datasource.password", "postgres");

		return properties;
	}

	@Override
	public void stop() {
		if (POSTGRES != null && POSTGRES.isRunning()) {
			POSTGRES.stop();
		}
	}

}
