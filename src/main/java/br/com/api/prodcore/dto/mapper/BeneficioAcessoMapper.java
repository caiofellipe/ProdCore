package br.com.api.prodcore.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.BeneficioAcessoDTO;
import br.com.api.prodcore.model.BeneficioAcesso;

@Component
public class BeneficioAcessoMapper {
	public BeneficioAcessoDTO toDTO(BeneficioAcesso beneficioAcesso) {
		if(beneficioAcesso == null) {
			return null;
		}

		return new BeneficioAcessoDTO(
				beneficioAcesso.getId(), beneficioAcesso.getNome(), 
				beneficioAcesso.getNivelAcesso(), beneficioAcesso.getCodigo(), beneficioAcesso.getNivelAcesso().getNome()
				);
	}
	
	public BeneficioAcesso toEntity(BeneficioAcessoDTO beneficioAcessoDTO) {
		if(beneficioAcessoDTO == null) {
			return null;
		}
		
		BeneficioAcesso beneficioAcesso = new BeneficioAcesso();
		
		if(beneficioAcessoDTO.id() != null) {
			beneficioAcesso.setId(beneficioAcessoDTO.id());
		}
		
		beneficioAcesso.setNome(beneficioAcessoDTO.nome());
		beneficioAcesso.setNivelAcesso(beneficioAcessoDTO.nivelAcesso());
		beneficioAcesso.setCodigo(beneficioAcessoDTO.codigo());
		
		return beneficioAcesso;
	}
}
