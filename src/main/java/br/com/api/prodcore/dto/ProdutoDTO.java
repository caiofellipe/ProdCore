package br.com.api.prodcore.dto;

import java.util.List;

import br.com.api.prodcore.model.Plano;

public record ProdutoDTO(
	Long id,
	String nome,
	Integer categoria,
	Integer subCategoria,
	String descricao,
	Plano plano,
	List<String> imagens
) {}
