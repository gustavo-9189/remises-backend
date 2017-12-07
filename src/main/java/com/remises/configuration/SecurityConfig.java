package com.remises.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("administrador@gmail.com").password("123456").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("usuario@gmail.com").password("123456").roles("USER");
		auth.inMemoryAuthentication().withUser("dba@gmail.com").password("123456").roles("ADMIN, DBA");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/home").access("hasRole('USER')")
			.antMatchers("/admin/**").access("hasRole('ADMIN')")
			.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
			.and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
			.usernameParameter("ssoId").passwordParameter("password")
			.and().csrf()
			.and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
	
}
