package br.com.api.prodcore.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.UsuarioPlanoAcessoDTO;
import br.com.api.prodcore.model.UsuarioPlanoAcesso;

@Component
public class UsuarioPlanoAcessoMapper {
	public UsuarioPlanoAcessoDTO toDTO(UsuarioPlanoAcesso usuarioPlanoAcesso) {
		if(usuarioPlanoAcesso == null) {
			return null;
		}
		
		return new UsuarioPlanoAcessoDTO(usuarioPlanoAcesso.getId(), usuarioPlanoAcesso.getPlanoAcessoId(), 
				usuarioPlanoAcesso.getUsuarioId(), usuarioPlanoAcesso.getDataContratado(), usuarioPlanoAcesso.getDataExpiracao());
	}
	
	public UsuarioPlanoAcesso toEntity(UsuarioPlanoAcessoDTO usuarioPlanoAcessoDTO) {
		if(usuarioPlanoAcessoDTO == null) {
			return null;
		}
		
		UsuarioPlanoAcesso usuarioPlanoAcesso = new UsuarioPlanoAcesso();
		
		
		if(usuarioPlanoAcessoDTO.id() != null) {
			usuarioPlanoAcesso.setId(usuarioPlanoAcessoDTO.id());
		}
		
		usuarioPlanoAcesso.setPlanoAcessoId(usuarioPlanoAcessoDTO.planoAcessoId());
		usuarioPlanoAcesso.setUsuarioId(usuarioPlanoAcessoDTO.usuarioId());
		usuarioPlanoAcesso.setDataContratado(usuarioPlanoAcessoDTO.dataContratado());
		usuarioPlanoAcesso.setDataExpiracao(usuarioPlanoAcessoDTO.dataExpiracao());
		return usuarioPlanoAcesso;
	}
}
