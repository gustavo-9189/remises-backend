package com.remises.global;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:remises.properties")
public class Constantes {
	
	// Server Parameters
	private static @Value("${server.protocol}") String PROTOCOL;
	private static @Value("${server.host}") String HOST;
	private static @Value("${server.port}") String PORT;
	private static @Value("${server.uri}") String URI;

	public static String getURI(String uri) {
		uri = uri.length() > 0 ? uri : "";
		return PROTOCOL.concat("://")
			.concat(HOST).concat(":")
			.concat(PORT).concat("/")
			.concat(URI).concat(uri);
	}

}
