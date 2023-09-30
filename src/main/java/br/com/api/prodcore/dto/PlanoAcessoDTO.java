package br.com.api.prodcore.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.prodcore.model.NivelAcesso;

public record PlanoAcessoDTO(
	Long id,
	String nome,
	String descricao,
	BigDecimal valor,
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime dataEditado,
	NivelAcesso nivelAcesso
) {}
