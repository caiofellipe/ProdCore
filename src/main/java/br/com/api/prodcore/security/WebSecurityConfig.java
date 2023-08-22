package br.com.api.prodcore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.api.prodcore.service.CustomUserDetailsService;


@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		//http.csrf().disable().authorizeHttpRequests().antMatchers("/prodcore/api/usuario/cadastrar", "/prodcore/api/usuario/cadastrar/**").permitAll().and().httpBasic();
		//http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated().and().antMatcher("/prodcore/api/usuario/cadastrar").httpBasic();
		http.csrf().disable()
			.authorizeHttpRequests()
				.antMatchers(HttpMethod.POST, "/api/usuario/cadastrar")
					.permitAll()
			.anyRequest().authenticated()
		.and().httpBasic();
		
		return http.build();
	}
	
	public void authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
