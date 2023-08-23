package br.com.api.prodcore.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{

	private static final long serialVersionUID = 1L;
	private String nome;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private Usuario usuario;
	
	public UserPrincipal(Usuario usuario) {
		this.nome = usuario.getRoles().get(usuario.getRoles().size() - 1).getNome();
		this.password = usuario.getSenha();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		// ROLE_ADMIN, ROLE_USER
		usuario.getRoles().stream().map(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_".concat(this.nome)));
			return authorities;
		}).collect(Collectors.toList());
		
		this.authorities = authorities;
		setUsuario(usuario);
				
	}
	
	public static UserPrincipal criar(Usuario usuario) {
		return new UserPrincipal(usuario);
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
	
}
