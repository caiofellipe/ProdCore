package br.com.api.prodcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.UsuarioAuthDTO;
import br.com.api.prodcore.dto.UsuarioDTO;

@Service
public class AutenticacaoService {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
		
	public UserDetails autenticacao(UsuarioAuthDTO usuarioAuthDTO) throws Exception {
		autentica(usuarioAuthDTO.email(), usuarioAuthDTO.senha());
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(usuarioAuthDTO.email());
		
		return userDetails;
	}


	public UsuarioDTO cadastrar(UsuarioDTO usuarioDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String logout() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void autentica(String email, String senha) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(email, senha)
					);
		} catch (DisabledException e) {
			throw new Exception("Usuario desabilitado", e);
		}catch(BadCredentialsException e) {
			throw new Exception("Credenciais Inválidas", e);
		}
	}
}
