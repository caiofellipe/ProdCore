package br.com.api.prodcore.dto;

public record CadastroUsuarioDTO (
		String nome,
		Long idUsuarioConvite,
		Long idUsuarioConviteNv2,
		String email,
		String senha,
		String foto
		){}
