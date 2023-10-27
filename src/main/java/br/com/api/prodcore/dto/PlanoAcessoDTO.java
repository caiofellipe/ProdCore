package br.com.api.prodcore.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.api.prodcore.model.NivelAcesso;
import br.com.api.prodcore.model.Usuario;

public record PlanoAcessoDTO(
	Long id,
	String nome,
	String descricao,
	BigDecimal valor,
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime dataEditado,
	@JsonIgnore
	List<Usuario> usuario,
	NivelAcesso nivelAcesso
) {}
