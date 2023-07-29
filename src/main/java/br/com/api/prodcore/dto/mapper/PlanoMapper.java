package br.com.api.prodcore.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.PlanoDTO;
import br.com.api.prodcore.dto.ProdutoDTO;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.model.Plano;
import br.com.api.prodcore.model.Produto;

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
		
		
		if(planoDTO.id() != null){
			plano.setId(planoDTO.id());
		}
		
		plano.setNome(planoDTO.nome());
		plano.setNivel(planoDTO.nivel());
		plano.setProduto(planoDTO.produto());
		
		for(Produto produto: planoDTO.produto()) {
			produto.setPlano(plano);
		}

		return plano;
	}
	
}
