package br.com.api.prodcore.dto;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.prodcore.model.NivelUsuario;

public record UsuarioDTO(
	Long id,	
	String nome,
	String sobrenome,
	String idUsuarioConvite,
	String email,
	String senha,
	String login,
	UUID idUsuario,
	boolean ativo,
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date dataCriado,
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date dataAlterado,
	NivelUsuario nivelUsuario,
	Long empresaId,
	byte[] foto
) {}
