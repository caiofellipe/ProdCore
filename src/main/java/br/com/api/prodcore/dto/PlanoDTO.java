package br.com.api.prodcore.dto;

public record PlanoDTO(
	Long id,
	String nome,
	String nivel,
	Long empresaId
) {}
