package br.com.api.prodcore.dto.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.PlanoAcessoDTO;
import br.com.api.prodcore.model.PlanoAcesso;

@Component
public class PlanoAcessoMapper {

	public PlanoAcessoDTO toDTO(PlanoAcesso planoAcesso) {
		if(planoAcesso == null) {
			return null;
		}
		
		return new PlanoAcessoDTO(
				planoAcesso.getId(), planoAcesso.getNome(), planoAcesso.getDescricao(),
				planoAcesso.getValor(), planoAcesso.getDataEditado(), planoAcesso.getNivelAcesso()
				);
		
	}
	
	public PlanoAcesso toEntity(PlanoAcessoDTO planoAcessoDTO) {
		if(planoAcessoDTO == null) {
			return null;
		}
		
		PlanoAcesso planoAcesso = new PlanoAcesso();
		
		if(planoAcessoDTO.id() != null) {
			planoAcesso.setId(planoAcessoDTO.id());
		}
		
		planoAcesso.setNome(planoAcessoDTO.nome());
		planoAcesso.setDescricao(planoAcessoDTO.descricao());
		planoAcesso.setValor(planoAcessoDTO.valor());
		planoAcesso.setDataEditado(LocalDateTime.now());
		return planoAcesso;
		
	}
}
