package br.com.api.prodcore.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record UsuarioPlanoAcessoDTO(
		Long id,
		Long planoAcessoId,
		Long usuarioId,
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
		LocalDateTime dataContratado,
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
		LocalDateTime dataExpiracao
		) {}
