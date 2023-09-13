package br.com.api.prodcore.dto;

import java.time.Instant;

import br.com.api.prodcore.model.Usuario;

public record TokenUsuarioDTO(
		String token,
		Instant criacao,
		Instant expiracao,
		Usuario usuario
		) {

}
