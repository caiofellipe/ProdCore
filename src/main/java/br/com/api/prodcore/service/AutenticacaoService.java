package br.com.api.prodcore.service;

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
import br.com.api.prodcore.exception.UsuarioException;
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
	TokenService tokenService;
		
	public TokenUsuarioDTO autenticacao(UsuarioAuthDTO usuarioAuthDTO) throws Exception {
		autentica(usuarioAuthDTO.email(), usuarioAuthDTO.senha());
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(usuarioAuthDTO.email());
		
		String token = tokenService.gerarToken((Usuario) userDetails);
		
		return new TokenUsuarioDTO(token, (Usuario) userDetails);
	}


	public UsuarioDTO cadastrar(CadastroUsuarioDTO cadastroUsuarioDTO) {
		Usuario usuario = usuarioRepository.findByUsuarioEmail(cadastroUsuarioDTO.email());
		
		if(usuario != null) {
			throw new Error("Usuario já cadastrado com este email");
		}
		
		UsuarioDTO usuarioDTO = usuarioService.criarUsuario(new UsuarioDTO(
				null, cadastroUsuarioDTO.nome(), cadastroUsuarioDTO.idUsuarioConvite(), 
				cadastroUsuarioDTO.email(), cadastroUsuarioDTO.senha(), true, null, null, 
				null, cadastroUsuarioDTO.foto())
				);
		
		return usuarioDTO;
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
	
}
