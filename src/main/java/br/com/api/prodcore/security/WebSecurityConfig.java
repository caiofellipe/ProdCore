package br.com.api.prodcore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.api.prodcore.service.ImplDetalhesUsuarioService;
import jakarta.servlet.http.HttpSessionListener;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig implements HttpSessionListener{
	
	@Autowired
	private ImplDetalhesUsuarioService implDetalhesUsuarioService;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((autorize) -> autorize
				.requestMatchers("/").permitAll()
				.requestMatchers("/index").permitAll()
				.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				
				// Redireciona para index quando fizer logout
				.anyRequest().authenticated()
				)
				//Filtra as requisições
				.addFilterAfter(new JWTLoginFilter("/login", getAuthenticationManager(http) ), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
				
		return http.build();
		
	}
	
	public AuthenticationManager authenticationManager(@Autowired AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(implDetalhesUsuarioService).passwordEncoder(new BCryptPasswordEncoder());
		return auth.build();
	}

	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	public AuthenticationManager getAuthenticationManager(HttpSecurity http) throws Exception{
		return http.getSharedObject(AuthenticationManager.class);
	}

}
