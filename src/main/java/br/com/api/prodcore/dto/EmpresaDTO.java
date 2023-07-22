package br.com.api.prodcore.dto;

import java.util.List;

import br.com.api.prodcore.model.Plano;
import br.com.api.prodcore.model.Usuario;

public record EmpresaDTO (
	Long id,
	String nome,
	String cnpj,
	String email,
	String ramo,
	String telefone,
	String endereco,
	byte[] logo,
	List<Plano> plano,
	Usuario usuario
){}
