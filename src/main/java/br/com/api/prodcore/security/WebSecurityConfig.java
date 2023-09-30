package br.com.api.prodcore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.api.prodcore.service.CustomUserDetailsService;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	@Autowired 
	SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().authorizeHttpRequests()
				.antMatchers("/api/empresa/localizacao/{uf}/{cidade}").permitAll()
				.antMatchers("/api/empresa/{id}").permitAll()
				.antMatchers("/api/usuario/cadastrar").permitAll()
				.antMatchers("/api/auth/login").permitAll()
				.antMatchers("/api/auth/cadastrar").permitAll()
				.antMatchers("/api/auth/validaToken").permitAll()
				.antMatchers("/swagger-ui/**/**").permitAll()
			//	.antMatchers("/api/plano-acesso/**").permitAll() // TODO remover para subir para produção
				.antMatchers("/v3/**").permitAll()
			.anyRequest().authenticated()
		/*.and().logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")*/;
		
		
		return http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
	}
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
}
