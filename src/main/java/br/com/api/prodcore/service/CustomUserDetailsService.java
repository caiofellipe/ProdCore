package br.com.api.prodcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.api.prodcore.model.UserPrincipal;
import br.com.api.prodcore.model.Usuario;
import br.com.api.prodcore.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUseremailFetchRoles(username);
		
		if(usuario == null) {
			throw new Error("Este usuario não está cadastrado!");
		}
		
		return UserPrincipal.criar(usuario);
	}

}
