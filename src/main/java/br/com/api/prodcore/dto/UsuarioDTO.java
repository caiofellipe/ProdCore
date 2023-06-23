package br.com.api.prodcore.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.prodcore.model.NivelUsuario;

public record UsuarioDTO(
		Long id,	
		String nome,
		String sobrenome,
		String email,
		String login,
		String idUsuario,
		boolean ativo,
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
		Date dataCriado,
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
		Date dataAlterado,
		List<NivelUsuario> nivelUsuario) {
	
}
