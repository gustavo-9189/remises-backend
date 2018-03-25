package com.remises.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/***
 * 
 * @author Gustavo
 * 
 * Filtro para devolver desde el lado del servidor, 
 * un Header de control de acceso CORS adicional con respuesta, 
 * lo que permitira una mayor comunicacion entre dominios (Front/Back).
 *
 */
public class CORSFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(CORSFilter.class);

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		LOGGER.info("Filtering on...........................................................");
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type ");
		try {
			// Ejecuta el resto de los filtros pendientes
			chain.doFilter(req, res);	
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	public void init(FilterConfig filterConfig) {}

	public void destroy() {}

}