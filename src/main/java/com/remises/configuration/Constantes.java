package com.remises.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:remises.properties")
public final class Constantes {

	// DB Parameters
	@Value("${db.driver}")
	public static String DB_DRIVER;
	@Value("${db.url}")
	public static String DB_URL;
	@Value("${db.username}")
	public static String DB_USER;
	@Value("${db.password}")
	public static String DB_PASSWORD;

	// Hibernate Parameters
	@Value("${entitymanager.packages.to.scan}")
	public static String HB_PACKAGE_MODEL;
	@Value("${entitymanager.name.persistence}")
	public static String HB_NAME_PERSISTENCE;
	@Value("${hibernate.generate.ddl}")
	public static boolean HB_GENERATE_DDL;
	@Value("${hibernate.show_sql}")
	public static boolean HB_LOGS_SQL;
	
	// Server Parameters
	@Value("${server.protocol}")
	public static String SERVER_PROTOCOL;
	@Value("${server.host}")
	public static String SERVER_HOST;
	@Value("${server.port}")
	public static String SERVER_PORT;
	@Value("${server.uri}")
	public static String SERVER_URI;

	// URI para Tests de los servicios REST
	public static final String URI = 
			SERVER_PROTOCOL
			.concat("://")
			.concat(SERVER_HOST)
			.concat(":")
			.concat(SERVER_PORT)
			.concat("/")
			.concat(SERVER_URI)
			.concat("/") ;

}
