package com.wlailton.curriculumMatrixapi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
			.headers().frameOptions().disable()
			.and().csrf().disable()
			.authorizeRequests().antMatchers("/h2/*").permitAll()
			.antMatchers(HttpMethod.POST, "/api/login").permitAll()
			.antMatchers("/api/user").hasAuthority("ROLE_ADMIN")
			.anyRequest().authenticated()
			.and()
			// filter login requests
			.addFilterBefore(new JWTLoginFilter("/api/login", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
			// filters other requests to check the presence of JWT in the header
			.addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
		// create default accounts
		.withUser("admin").password("password").roles("ADMIN")
		.and()
		.withUser("coordinator").password("password").roles("COORDINATOR")
		.and()
		.withUser("professor").password("password").roles("PROFESSOR")
		.and()
		.withUser("student").password("password").roles("STUDENT");
	}
}
