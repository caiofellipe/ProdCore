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

import br.com.api.prodcore.model.Usuario;

@Service
public class TokenService {
	@Value("${api.security.token.chave}")
	private String chave;
	
	public String gerarToken(Usuario usuario) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(chave);
			String token = JWT.create()
					.withIssuer("auth-api")
					.withSubject(usuario.getEmail())
					.withExpiresAt(tempoExpiracaoToken())
					.sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao gerar o token JWT", e);
		}
	}
	
	public String validaToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(chave);
			return JWT.require(algorithm)
					.withIssuer("auth-api")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException e) {
			return "";
		}
	}
	
	private Instant tempoExpiracaoToken() {
		// Time zone pt-BR
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
}
