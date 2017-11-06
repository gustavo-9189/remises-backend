package com.remises.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/***
 * 
 * @author Gustavo
 *
 * Configuracion de Hibernate y Spring Data JPA
 * Desde el archivo de properties
 * Ubicado en src/main/resources/remises.properties
 * 
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.remises.repository")
@PropertySource("classpath:remises.properties")
public class JPAConfig {

	// DB Parameters
	private static final String DRIVER = "db.driver";
	private static final String URL = "db.url";
	private static final String USER = "db.username";
	private static final String PASSWORD = "db.password";

	// Hibernate Parameters
	private static final String PACKAGE_MODEL = "entitymanager.packages.to.scan";
	private static final String NAME_PERSISTENCE = "entitymanager.name.persistence";
	private static final boolean GENERATE_DDL = false;
	private static final boolean LOGS_SQL = true;

	@Autowired
	private Environment entorno;

	/***
	 * Retorna la configuracion necesaria para el datasource (la base de datos)
	 * Aqui se define el driver, la conexion url, el usuario y password
	 *
	 * @return DataSource
	 */
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(entorno.getRequiredProperty(DRIVER));
        dataSource.setUrl(entorno.getRequiredProperty(URL));
        dataSource.setUsername(entorno.getRequiredProperty(USER));
        dataSource.setPassword(entorno.getRequiredProperty(PASSWORD));
        return dataSource;
	}

	/***
	 * Configuracion para el entity manager de Hibernate
	 * Aqui se setea el paquete donde actuaran 
	 * las anotaciones de hibernate, el datasource configurado anteriormente,
	 * se le da un nombre al PersistenceUnitName, etc...
	 * 
	 * @return LocalContainerEntityManagerFactoryBean
	 */
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setPackagesToScan(entorno.getRequiredProperty(PACKAGE_MODEL));
        factoryBean.setPersistenceUnitName(entorno.getRequiredProperty(NAME_PERSISTENCE));
        return factoryBean;
    }

	/***
	 * Se configura el adaptador del proveedor de persistencia,
	 * para este caso Hibernate con MySQL
	 * 
	 * @return JpaVendorAdapter
	 */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(GENERATE_DDL);
        jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setShowSql(LOGS_SQL);
        return jpaVendorAdapter;
    }

    /***
     * Se configura el gestor de transacciones para JPA
     * 
     * @return PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}