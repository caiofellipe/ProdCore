package br.com.api.prodcore.dto;

public record CadastroUsuarioDTO (
		String nome,
		Long idUsuarioConvite,
		String email,
		String senha,
		String foto
		){}
