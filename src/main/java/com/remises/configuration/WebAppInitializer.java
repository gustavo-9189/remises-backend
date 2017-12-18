package com.remises.configuration;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/***
 * 
 * @author Gustavo
 *
 * Configuracion programatica del web.xml 
 * Aca se declaran los servlet, tal como el 
 * DispatcherServlet de Spring, la configuracion de Hibernate
 * Spring Data JPA y los Filters en este caso el CORSFilter
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/***
	 * Este metodo contiene la configuracion de acceso a datos
	 * que se encuentra en la clase: JPAConfig.class
	 */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { JPAConfig.class };
    }
  
    /***
	 * Dentro de este metodo definimos el Contexto de Spring
	 * que se encuentra en la clase: SpringConfig.class
	 * Es el contextConfigLocation usado para indicar donde se encuentra la configuracion
	 */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { SpringConfig.class };
    }
  
    /***
     * Es el antiguo tag: <servlet-mapping> del web.xml
     * Sirve para definir las URL que seran admitidas por este Servlet, 
     * define el patron de las URL validas, en este caso "/" 
     * indica que se admiten todas las peticiones HTTP.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    /***
     * Agregamos el Filter CORSFilter
     */
    @Override
    protected Filter[] getServletFilters() {
    	Filter [] singleton = { new CORSFilter()};
    	return singleton;
    }
 
}