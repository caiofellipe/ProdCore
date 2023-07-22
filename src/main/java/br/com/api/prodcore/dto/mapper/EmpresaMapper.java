package br.com.api.prodcore.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.EmpresaDTO;
import br.com.api.prodcore.model.Empresa;

@Component
public class EmpresaMapper {

	public EmpresaDTO toDTO(Empresa empresa) {
		if(empresa == null) {
			return null;
		}
		
		return new EmpresaDTO(
				empresa.getId(), empresa.getCnpj(), empresa.getEmail(), empresa.getEndereco(),
				empresa.getNome(), empresa.getRamo(), empresa.getTelefone(), empresa.getLogo(), 
				empresa.getPlanos(), empresa.getUsuario()
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
		
		empresa.setCnpj(empresaDTO.cnpj());
		empresa.setEmail(empresaDTO.email());
		empresa.setEndereco(empresaDTO.endereco());

		empresa.setLogo(empresaDTO.logo());
		empresa.setNome(empresaDTO.nome());
		empresa.setPlanos(empresaDTO.plano());
		empresa.setRamo(empresaDTO.ramo());
		empresa.setTelefone(empresaDTO.telefone());
		empresa.setUsuario(empresaDTO.usuario());
		
		return empresa;
	}
	
}
