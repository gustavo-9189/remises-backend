package com.remises.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@PropertySource("classpath:remises.properties")
public class Constantes {

	@Autowired
	private static Environment entorno;

	// DB Parameter
	public static final String DB_DRIVER = entorno.getRequiredProperty("db.driver");
	public static final String DB_URL = entorno.getRequiredProperty("db.url");
	public static final String DB_USER = entorno.getRequiredProperty("db.username");
	public static final String DB_PASSWORD = entorno.getRequiredProperty("db.password");

	// Hibernate Parameters
	public static final String HB_PACKAGE_MODEL = entorno.getRequiredProperty("entitymanager.packages.to.scan");
	public static final String HB_NAME_PERSISTENCE = entorno.getRequiredProperty("entitymanager.name.persistence");
	public static final boolean HB_GENERATE_DDL = Boolean.parseBoolean(entorno.getRequiredProperty("hibernate.generate.ddl"));
	public static final boolean HB_LOGS_SQL = Boolean.parseBoolean(entorno.getRequiredProperty("hibernate.show_sql"));
	
	// Server Parameters
	public static final String SERVER_PROTOCOL = entorno.getRequiredProperty("server.protocol");
	public static final String SERVER_HOST = entorno.getRequiredProperty("server.host");
	public static final String SERVER_PORT = entorno.getRequiredProperty("server.port");
	public static final String SERVER_URI = entorno.getRequiredProperty("server.uri");

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
