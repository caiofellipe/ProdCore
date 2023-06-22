package br.com.api.prodcore.security;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.api.prodcore.ApplicationContextLoad;
import br.com.api.prodcore.model.Usuario;
import br.com.api.prodcore.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTTokenAutenticacaoService {

	/* 10 dias - validade token em milisegundos */
	private static final long TEMPO_EXPIRACAO = 864000000;
	private static final String CHAVE_SECRETA = "";
	private static final String TOKEN_PREFIXO = "Bearer";
	private static final String HEADER = "Authorization";
	private static final String ACCESS_CONTROL_ORIGIN = "Access-Control-Allow-Origin";
	private static final String ACCESS_CONTROL_HEADERS = "Access-Control-Allow-Headers";
	private static final String ACCESS_CONTROL_REQUEST= "Access-Control-Request-Headers";
	private static final String ACCESS_CONTROL_METHODS= "Access-Control-Allow-Methods";
	
	public void addAuth(HttpServletResponse response, String userName) throws Exception {
		long dataAtualEmMilisegundos = System.currentTimeMillis() + TEMPO_EXPIRACAO;
		
		String JWT = Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(dataAtualEmMilisegundos))
				.signWith(SignatureAlgorithm.HS512, CHAVE_SECRETA).compact();
		
		String token = TOKEN_PREFIXO + " " + JWT;
	
		response.addHeader(HEADER, token);
		liberaCors(response);
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	
	}
	
	public Authentication getAutenticacao(HttpServletRequest request, HttpServletResponse response){
		
		String token = request.getHeader(HEADER);
		if(token != null){
			String tokemSemPrefixo = token.replace(TOKEN_PREFIXO, "").trim();
			String usuarioObtidoDoToken = Jwts.parser()
					.setSigningKey(CHAVE_SECRETA)
					.parseClaimsJws(tokemSemPrefixo)
					.getBody().getSubject();
			 if(usuarioObtidoDoToken != null) {
				 // Obtem o contexto da aplicação e acessa a classe UsuarioRepository com toda sua injeção de dependencia
				 Usuario usuario = ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class).findUserByLogin(usuarioObtidoDoToken);
				 if(usuario != null) {
					 return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha(), usuario.getAuthorities());
				 }
				 
			 }
		}
		
		liberaCors(response);
		return null;
		
	}
	
	
	private void liberaCors(HttpServletResponse response) {
		if(response.getHeader(ACCESS_CONTROL_ORIGIN) == null) {
			response.addHeader(ACCESS_CONTROL_ORIGIN, "*");
		}
		if(response.getHeader(ACCESS_CONTROL_HEADERS) == null) {
			response.addHeader(ACCESS_CONTROL_HEADERS, "*");
		}
		if(response.getHeader(ACCESS_CONTROL_REQUEST) == null) {
			response.addHeader(ACCESS_CONTROL_REQUEST, "*");
		}
		if(response.getHeader(ACCESS_CONTROL_METHODS) == null) {
			response.addHeader(ACCESS_CONTROL_METHODS, "*");
		}
	}
	

}
