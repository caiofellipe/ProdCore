package br.com.api.prodcore.dto;

import br.com.api.prodcore.model.BeneficioAcesso;

public record NivelAcessoDTO(
		Long id,
		String nome,
		BeneficioAcesso beneficioAcesso
		) {}
