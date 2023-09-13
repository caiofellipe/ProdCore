package br.com.api.prodcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.api.prodcore.exception.UsuarioException;
import br.com.api.prodcore.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails usuario = usuarioRepository.findByEmail(username);
		
		if(usuario == null) {
			throw new UsuarioException("O usuario "+ username +" não está cadastrado!", new UsernameNotFoundException("Usuario não encontrado"));
		}
		
		return usuario;
	}

}
