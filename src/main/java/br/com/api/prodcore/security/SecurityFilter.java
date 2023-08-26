package br.com.api.prodcore.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.api.prodcore.repository.UsuarioRepository;
import br.com.api.prodcore.service.TokenService;

@Component
public class SecurityFilter extends OncePerRequestFilter{

	@Autowired
	TokenService tokenService;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = this.recuperaToken(request);

		if(token != null) {
			String emailDeLoginComTokenValidado = tokenService.validaToken(token);
			UserDetails user = usuarioRepository.findByEmail(emailDeLoginComTokenValidado);
			Authentication autorizacao = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
			SecurityContextHolder.getContext().setAuthentication(autorizacao);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String recuperaToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		
		if(header == null) return null;
		
		return header.replace("Bearer ", "");
	}

}
