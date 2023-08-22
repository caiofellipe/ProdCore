package br.com.api.prodcore.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.UsuarioDTO;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.model.Usuario;

@Component
public class UsuarioMapper {
	
	public UsuarioDTO toDTO(Usuario usuario) {
		if(usuario == null) {
			return null;
		}

		return new UsuarioDTO(
				usuario.getId(), usuario.getNome(), usuario.getIdUsuarioConvite(), 
				usuario.getEmail(), usuario.getSenha(), usuario.isAtivo(), 
				usuario.getDataCriado(),usuario.getDataAlterado(), usuario.getRoles(), usuario.getFoto()
				);
	} 
	
	public Usuario toEntity(UsuarioDTO usuarioDTO) {
		if(usuarioDTO == null) { 
			return null;
		}

		Usuario usuario = new Usuario();
		Empresa empresa = new Empresa();
		
		if(usuarioDTO.id() != null){
			usuario.setId(usuarioDTO.id());
		}
		 
		usuario.setNome(usuarioDTO.nome());
		usuario.setEmail(usuarioDTO.email());
		usuario.setSenha(usuarioDTO.senha());
		usuario.setAtivo(usuarioDTO.ativo());
		usuario.setDataCriado(usuarioDTO.dataCriado());
		usuario.setDataAlterado(usuarioDTO.dataAlterado());
		usuario.setFoto(usuarioDTO.foto());
		
		return usuario;
	}

}
