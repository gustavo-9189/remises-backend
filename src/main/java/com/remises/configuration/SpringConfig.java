package com.remises.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
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
public class SpringConfig extends WebMvcConfigurerAdapter {}