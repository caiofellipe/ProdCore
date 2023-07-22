package br.com.api.prodcore.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.UsuarioDTO;
import br.com.api.prodcore.model.Usuario;

@Component
public class UsuarioMapper {
	
	public UsuarioDTO toDTO(Usuario usuario) {
		if(usuario == null) {
			return null;
		}

		return new UsuarioDTO(
				usuario.getId(), usuario.getNome(), usuario.getSobrenome(), 
				usuario.getIdUsuarioConvite(), usuario.getEmail(), usuario.getLogin(), usuario.getSenha(),
				usuario.getIdUsuario(), usuario.isAtivo(), usuario.getDataCriado(),usuario.getDataAlterado(), 
				usuario.getNivelUsuario(), usuario.getEmpresa(), usuario.getFoto()
				);
	} 
	
	public Usuario toEntity(UsuarioDTO usuarioDTO) {
		if(usuarioDTO == null) {
			return null;
		}

		Usuario usuario = new Usuario();
		
		if(usuarioDTO.id() != null){
			usuario.setId(usuarioDTO.id());
		}
		
		usuario.setNome(usuarioDTO.nome());
		usuario.setSobrenome(usuarioDTO.sobrenome());
		usuario.setEmail(usuarioDTO.email());
		usuario.setLogin(usuarioDTO.login());
		usuario.setSenha(usuarioDTO.senha());
		usuario.setIdUsuario(usuarioDTO.idUsuario());
		usuario.setAtivo(usuarioDTO.ativo());
		usuario.setDataCriado(usuarioDTO.dataCriado());
		usuario.setNivelUsuario(usuarioDTO.nivelUsuario());
		usuario.setEmpresa(usuarioDTO.empresa());
		
		return usuario;
	}

}
