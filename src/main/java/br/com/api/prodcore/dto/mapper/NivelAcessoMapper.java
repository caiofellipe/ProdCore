package br.com.api.prodcore.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.NivelAcessoDTO;
import br.com.api.prodcore.model.BeneficioAcesso;
import br.com.api.prodcore.model.NivelAcesso;

@Component
public class NivelAcessoMapper {
	public NivelAcessoDTO toDTO(NivelAcesso nivelAcesso) {
		if(nivelAcesso == null) {
			return null;
		}

		return new NivelAcessoDTO(
				nivelAcesso.getId(), nivelAcesso.getNome(), 
				nivelAcesso.getBeneficioAcesso()
				);
	}
	
	public NivelAcesso toEntity(NivelAcessoDTO nivelAcessoDTO) {
		if(nivelAcessoDTO == null) {
			return null;
		}
		
		NivelAcesso nivelAcesso = new NivelAcesso();
		
		if(nivelAcessoDTO.id() != null) {
			nivelAcesso.setId(nivelAcessoDTO.id());
		}
		
		nivelAcesso.setNome(nivelAcessoDTO.nome());
		
		List<BeneficioAcesso> listBeneficioAcesso = new ArrayList<BeneficioAcesso>();
		for(BeneficioAcesso beneficio: nivelAcessoDTO.beneficioAcesso()) {
			listBeneficioAcesso.add(beneficio);
			nivelAcesso.setBeneficioAcesso(listBeneficioAcesso);
		}
		
		return nivelAcesso;
	}
}
