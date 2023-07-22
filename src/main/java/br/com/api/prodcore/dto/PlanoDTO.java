package br.com.api.prodcore.dto;

import java.util.List;

import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.model.Produto;

public record PlanoDTO(
	Long id,
	String nome,
	String nivel,
	List<Produto> produto,
	Empresa empresa
) {}
