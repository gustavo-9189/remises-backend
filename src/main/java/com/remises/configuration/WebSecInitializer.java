package com.remises.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 
 * @author GUMARTINEZ
 *
 * Registra DelegatingFilterProxy para usar 
 * springSecurityFilterChain antes que cualquier otro filtro registrado
 */
public class WebSecInitializer extends AbstractSecurityWebApplicationInitializer {}
