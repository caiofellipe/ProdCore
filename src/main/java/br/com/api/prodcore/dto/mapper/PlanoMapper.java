package br.com.api.prodcore.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.PlanoDTO;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.model.Plano;

@Component
public class PlanoMapper {

	public PlanoDTO toDTO(Plano plano) {
		if(plano == null) {
			return null;
		}

		return new PlanoDTO(
				plano.getId(), plano.getNome(), plano.getNivel(), 
				plano.getProduto(), plano.getEmpresa().getId()
				);
	} 
	
	public Plano toEntity(PlanoDTO planoDTO) {
		if(planoDTO == null) {
			return null;
		}

		Plano plano = new Plano();
		Empresa empresa = new Empresa();
		
		if(planoDTO.id() != null){
			plano.setId(planoDTO.id());
		}
		
		plano.setNome(planoDTO.nome());
		plano.setNivel(planoDTO.nivel());
		plano.setProduto(planoDTO.produto());
		
		empresa.setId(planoDTO.empresaId());
		plano.setEmpresa(empresa);
		
		return plano;
	}
	
}
