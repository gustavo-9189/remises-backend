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
 * Spring Data JPA, Spring Security y los Filters en este caso el CORSFilter
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/***
	 * Crea los beans que son específicos de la aplicación y 
	 * que deben estar disponibles para los filtros (ya que los filtros no son parte del servlet).
	 * Esta configuracion se cargara primero y luego la del getServletConfigClasses.
	 * Sera el contexto principal y creara una instancia de ApplicationContext
	 */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { JPAConfig.class, SecurityConfig.class };
    }
  
    /***
	 * Se utiliza para crear beans que son especificos de DispatcherServlet, 
	 * como ViewResolvers, ArgumentResolvers, Interceptor, etc.
	 * Esta configuracion se cargara luego de haberse cargado la del getRootConfigClasses.
	 * Sera el contexto secundario y creara una instancia de WebApplicationContext
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