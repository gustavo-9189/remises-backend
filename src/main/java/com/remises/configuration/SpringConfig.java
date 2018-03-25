package com.remises.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/***
 * 
 * @author Gustavo
 *
 * Reemplaza al antiguo ApplicationContext.xml
 * donde se describe toda la configuracion del contexto de Spring.
 * En este caso @EnableWebMvc reemplazaria al tag <mvc:annotation-driven>
 * y @ComponentScan a <context:component-scan base-package="" />
 * donde "com.remises" sera el paquete donde Spring escaneara todos los componentes
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.remises")
public class SpringConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * Es necesario para poder ejecutar la UI de Swagger, si este
	 * fuera un proyecto springboot esto no seria necesario.
	 * Le dice al manejador de recursos de spring donde ubicar swagger-ui
	 * asi poder levantarlo
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");
	 
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
}