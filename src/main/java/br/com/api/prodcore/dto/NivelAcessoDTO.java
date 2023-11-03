package br.com.api.prodcore.dto;

import java.util.List;

import br.com.api.prodcore.model.BeneficioAcesso;

public record NivelAcessoDTO(
		Long id,
		String nome,
		List<BeneficioAcesso> beneficioAcesso
		) {}
