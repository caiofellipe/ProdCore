package br.com.api.prodcore.dto;

import br.com.api.prodcore.model.Usuario;

public record TokenUsuarioDTO(
		String token,
		Usuario usuario
		) {

}
