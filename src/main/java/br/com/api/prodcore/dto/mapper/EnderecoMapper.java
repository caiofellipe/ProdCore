package br.com.api.prodcore.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.EnderecoDTO;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.model.Endereco;

@Component
public class EnderecoMapper {
	
	public EnderecoDTO toDTO(Endereco endereco) {
		if(endereco == null) {
			return null;
		}
		
		return new EnderecoDTO(
				endereco.getId(), endereco.getRua(), endereco.getNumero(),
				endereco.getCep(), endereco.getEstado(), endereco.getCidade(),
				endereco.getUf(), endereco.getBairro(), endereco.getEmpresa()
				);
	}
	
	public Endereco toEntity(EnderecoDTO enderecoDTO) {
		if(enderecoDTO == null) {
			return null;
		}
		
		Endereco endereco = new Endereco();
		
		if(enderecoDTO.id() != null) {
			endereco.setId(enderecoDTO.id());
		}
		
		endereco.setRua(enderecoDTO.rua());
		endereco.setNumero(enderecoDTO.numero());
		endereco.setCep(enderecoDTO.cep());
		endereco.setEstado(enderecoDTO.estado());
		endereco.setCidade(enderecoDTO.cidade());
		endereco.setUf(enderecoDTO.uf());
		endereco.setBairro(enderecoDTO.bairro());
		
		Empresa empresa = enderecoDTO.empresa();
		
		if(empresa != null) {
			empresa.setEndereco(endereco);
			endereco.setEmpresa(empresa);
		}
		
		
		return endereco;
	}

}
