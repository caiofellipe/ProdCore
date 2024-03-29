package br.com.api.prodcore.dto;

import br.com.api.prodcore.model.Categoria;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.model.SubCategoria;

public record ProdutoDTO(
	Long id,
	String nome,
	Categoria categoria,
	SubCategoria subCategoria,
	String descricao,
	Empresa empresa,
	String imagem
) {}
