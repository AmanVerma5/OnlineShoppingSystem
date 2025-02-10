package com.ecom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private CustomJWTAuthenticationFilter customJWTAuthenticationFilter;

	@Autowired
	private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
		http.csrf(c -> c.disable()).cors(c -> c.and())
				.authorizeHttpRequests(req -> req
						.requestMatchers("/users/signin", "/users/signup", "/swagger-ui/index.html").permitAll()
						.requestMatchers(HttpMethod.OPTIONS).permitAll().requestMatchers("/users/**")
						.hasAnyAuthority("ADMIN", "CUSTOMER", "VENDOR")
						.requestMatchers("/categories/**").permitAll()
						.requestMatchers("/products/**").permitAll()
						.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(customJWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.authenticationProvider(daoAuthenticationProvider());
		return http.build();

	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setUserDetailsService(customUserDetailsServiceImpl);
		provider.setPasswordEncoder(passwordEncoder());

		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
