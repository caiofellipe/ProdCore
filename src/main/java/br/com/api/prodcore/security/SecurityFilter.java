package br.com.api.prodcore.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.auth0.jwt.exceptions.TokenExpiredException;

import br.com.api.prodcore.exception.TokenException;
import br.com.api.prodcore.repository.UsuarioRepository;
import br.com.api.prodcore.service.TokenService;

@Component
public class SecurityFilter extends OncePerRequestFilter{

	@Autowired
	TokenService tokenService;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	@Qualifier("handlerExceptionResolver")
	HandlerExceptionResolver handlerExceptionResolver;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = "";
		try {
			token = this.recuperaToken(request, response);
		}catch (Exception e) {
			throw new TokenException("",e);
		}
		
		if(token != null && !token.isEmpty()) {
			
			UserDetails user = usuarioRepository.findByEmail(token);
			Authentication autorizacao = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
			SecurityContextHolder.getContext().setAuthentication(autorizacao);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String recuperaToken(HttpServletRequest request, HttpServletResponse response) throws Exception, TokenExpiredException{
		String header = request.getHeader("Authorization");
		
		if(header == null) return null;
		
		try {
			return tokenService.validaToken(header.replace("Bearer ", ""));
		} catch (TokenExpiredException e) {
			throw new TokenException("Token expirado", e);
		}catch(Exception ex) {
			throw new TokenException("Token inválido", ex);
		}
	}

}
