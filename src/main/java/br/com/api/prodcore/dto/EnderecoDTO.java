package br.com.api.prodcore.dto;

import br.com.api.prodcore.model.Empresa;

public record EnderecoDTO(
		Long id,
		String rua,
	    Integer numero,
	    String cep,
	    String estado,
	    String cidade,
	    String uf,
	    String bairro,
	    Empresa empresa
		) {}
