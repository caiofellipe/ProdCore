package br.com.api.prodcore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.CadastroUsuarioDTO;
import br.com.api.prodcore.dto.TokenUsuarioDTO;
import br.com.api.prodcore.dto.UsuarioAuthDTO;
import br.com.api.prodcore.dto.UsuarioDTO;
import br.com.api.prodcore.dto.mapper.UsuarioMapper;
import br.com.api.prodcore.exception.UsuarioException;
import br.com.api.prodcore.model.Role;
import br.com.api.prodcore.model.Usuario;
import br.com.api.prodcore.repository.UsuarioRepository;

@Service
public class AutenticacaoService {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	UsuarioMapper usuarioMapper;
	@Autowired
	TokenService tokenService;
	
	public TokenUsuarioDTO autenticacao(UsuarioAuthDTO usuarioAuthDTO) throws Exception {
		 
		autentica(usuarioAuthDTO.email(), usuarioAuthDTO.senha());
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(usuarioAuthDTO.email());

		return tokenService.gerarToken((Usuario) userDetails);
	}


	public UsuarioDTO cadastrar(CadastroUsuarioDTO cadastroUsuarioDTO) {
		Usuario usuario = usuarioRepository.findByUsuarioEmail(cadastroUsuarioDTO.email());
		Usuario usuarioConvite = new Usuario();
		
		if(usuario != null) {
			throw new UsuarioException("Usuario já cadastrado com este email", null);
		}
		
		usuarioConvite = usuarioRepository.findById(cadastroUsuarioDTO.idUsuarioConvite()).get();
		
		if(usuarioConvite == null) {
			throw new UsuarioException("Seu convite é inválido", null);
		}

		usuario = usuarioMapper.toEntityCadastro(cadastroUsuarioDTO);
		
		
		if(usuarioConvite.getIdUsuarioConvite() == null) {
			usuario.setIdUsuarioConviteNv2(usuarioConvite.getIdUsuarioConvite());
		}
		
		List<Role> roleList = new ArrayList<Role>();
		roleList.add(new Role(2L, "USER"));
		usuario.setRoles(roleList);
		
		usuario.setSenha(usuarioService.criptografaSenha().encode(usuario.getSenha()));
		
		return usuarioMapper.toDTO(usuarioRepository.save(usuario));
	}
	
	private void autentica(String email, String senha) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(email, senha)
					);
		} catch (DisabledException e) {
			throw new UsuarioException("Usuario desabilitado", e);
		}catch(BadCredentialsException e) {
			throw new UsuarioException("Credenciais Inválidas", e);
		}
	}


	public String validarToken(String token) throws Exception {
		return tokenService.validaToken(token);
	}
	
}
