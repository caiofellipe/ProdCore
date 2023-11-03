package br.com.api.prodcore.dto;

import br.com.api.prodcore.model.NivelAcesso;

public record BeneficioAcessoDTO(
		Long id,
		String nome,
		NivelAcesso nivelAcesso,
		String codigo,
		String nomeNivelAcesso
		) {}
