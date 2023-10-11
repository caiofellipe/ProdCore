package br.com.api.prodcore.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.EmpresaDTO;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.model.Produto;

@Component
public class EmpresaMapper {

	public EmpresaDTO toDTO(Empresa empresa) {
		if(empresa == null) {
			return null;
		}

		return new EmpresaDTO(
				empresa.getId(), empresa.getNome(), empresa.getCnpj(), empresa.getEmail(), 
				empresa.getRamo(), empresa.getTelefone(), empresa.getEndereco(), empresa.getLogo(), 
				empresa.getProduto(), empresa.getUsuario()
				);
	}
	
	public Empresa toEntity(EmpresaDTO empresaDTO) {
		if(empresaDTO == null) {
			return null;
		}
		
		Empresa empresa = new Empresa();
		
		if(empresaDTO.id() != null) {
			empresa.setId(empresaDTO.id());
		}
		
		empresa.setNome(empresaDTO.nome());
		empresa.setCnpj(empresaDTO.cnpj());
		empresa.setEmail(empresaDTO.email());
		empresa.setRamo(empresaDTO.ramo());
		empresa.setTelefone(empresaDTO.telefone());
		empresa.setLogo(empresaDTO.logo());
		
		List<Produto> produtos = new ArrayList<Produto>();
		if(empresaDTO.produto() != null) {
			for(Produto produto: empresaDTO.produto()){
				if(produto.getId() == null) {
					produto.setEmpresa(empresa);
				}
				produtos.add(produto);
			}
		}
		
		empresa.setProduto(produtos);
		
		return empresa;
	}
	
}
