package br.com.api.prodcore.dto.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.CadastroUsuarioDTO;
import br.com.api.prodcore.dto.UsuarioDTO;
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
				usuario.getDataCriado(),usuario.getDataAlterado(), usuario.getRoles(), usuario.getPlanoAcesso(), usuario.getEmpresa(), usuario.getFoto()
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
		usuario.setEmail(usuarioDTO.email());
		usuario.setSenha(usuarioDTO.senha());
		usuario.setAtivo(usuarioDTO.ativo());
		usuario.setDataCriado(usuarioDTO.dataCriado());
		usuario.setDataAlterado(usuarioDTO.dataAlterado());
		usuario.setFoto(usuarioDTO.foto());
		
		return usuario;
	}
	
	public Usuario toEntityCadastro(CadastroUsuarioDTO cadastroUsuarioDTO) {
		if(cadastroUsuarioDTO == null) { 
			return null;
		}

		Usuario usuario = new Usuario();
		
		usuario.setNome(cadastroUsuarioDTO.nome());
		usuario.setEmail(cadastroUsuarioDTO.email());
		usuario.setSenha(cadastroUsuarioDTO.senha());
		usuario.setFoto(cadastroUsuarioDTO.foto());
		usuario.setIdUsuarioConvite(cadastroUsuarioDTO.idUsuarioConvite());
		usuario.setAtivo(true);
		usuario.setDataCriado(LocalDateTime.now());
		usuario.setDataAlterado(LocalDateTime.now());
		
		return usuario;
	}

}
