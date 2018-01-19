package com.remises.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
 * Configuracion de Spring Data JPA e Hibernate
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
	private @Value("${db.driver}") String driver;
	private @Value("${db.url}") String url;
	private @Value("${db.username}") String user;
	private @Value("${db.password}") String password;

	// JPA Parameters
	private @Value("${entitymanager.packages.to.scan}") String packageModel;
	private @Value("${entitymanager.name.persistence}") String namePersistence;
	private @Value("${jpa.generate.ddl}") String generateDDL;
	private @Value("${jpa.show_sql}") String logsSQL;
	private @Value("${jpa.database}") String database;

	/***
	 * Retorna la configuracion necesaria para el datasource (la base de datos)
	 * Aqui se define el driver, la conexion url, el usuario y password
	 *
	 * @return DataSource
	 */
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
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
        factoryBean.setPackagesToScan(packageModel);
        factoryBean.setPersistenceUnitName(namePersistence);
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
        jpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(generateDDL));
        jpaVendorAdapter.setDatabase(Database.valueOf(database));
		jpaVendorAdapter.setShowSql(Boolean.parseBoolean(logsSQL));
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