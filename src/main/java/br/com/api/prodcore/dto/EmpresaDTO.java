package br.com.api.prodcore.dto;

import java.util.List;

import br.com.api.prodcore.model.Endereco;
import br.com.api.prodcore.model.Produto;
import br.com.api.prodcore.model.Usuario;

public record EmpresaDTO (
	Long id,
	String nome,
	String cnpj,
	String email,
	String ramo,
	String telefone,
	Endereco endereco,
	String logo,
	List<Produto> produto,
	Usuario usuario
){}
