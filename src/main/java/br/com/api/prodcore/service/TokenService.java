package br.com.api.prodcore.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import br.com.api.prodcore.dto.TokenUsuarioDTO;
import br.com.api.prodcore.exception.TokenException;
import br.com.api.prodcore.model.Usuario;

@Service
public class TokenService {
	@Value("${api.security.token.chave}")
	private String chave;
	
	public TokenUsuarioDTO gerarToken(Usuario usuario) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(chave);
			
			
			String token = JWT.create()
					.withIssuer("auth-api")
					.withSubject(usuario.getEmail())
					.withExpiresAt(tempoExpiracaoToken())
					.sign(algorithm);

			return new TokenUsuarioDTO(token, tempoAtualCriacaoToken(), tempoExpiracaoToken(), usuario);
			
		} catch (JWTCreationException e) {
			throw new TokenException("Erro ao gerar o token de autenticação", e);
		}
	}
	
	public String validaToken(String token) throws JWTVerificationException{
		try {
			Algorithm algorithm = Algorithm.HMAC256(chave);
			return JWT.require(algorithm)
					.withIssuer("auth-api")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException e) {
			throw new TokenException("Token expirado", e);
		}
	}
	
	private Instant tempoExpiracaoToken() {
		// Tempo de expiração do token é de 5h com Time zone pt-BR
		return LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.of("-03:00"));
	}
	
	private Instant tempoAtualCriacaoToken() {
		return LocalDateTime.now().toInstant(ZoneOffset.of("-03:00"));
	}
	
}
