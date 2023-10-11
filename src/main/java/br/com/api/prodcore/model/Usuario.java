package br.com.api.prodcore.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.api.prodcore.config.GrantedAuthorityDeserializer;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "id_usuario_convite")
	private Long idUsuarioConvite;
	
	@Column(name = "id_usuario_convite_nv2")
	private Long idUsuarioConviteNv2;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "senha")
	@JsonIgnore
	private String senha;
	
	@Column(name = "ativo")
	private boolean ativo;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.S]")	
	@Column(name = "data_criado")
	private LocalDateTime dataCriado;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.S]")
	@Column(name = "data_alterado")
	private LocalDateTime dataAlterado;
	
	@ManyToMany
	@JoinTable(name = "user_roles",
		joinColumns = @JoinColumn(name = "usuario_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

	@OneToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	@Column(name = "foto", columnDefinition = "text")
	private String foto;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plano_acesso_id")
	private PlanoAcesso planoAcesso;
	
	public Usuario() {} 
	
	public Usuario(Role role) {
		this.roles.add(role);
	}
	
	public Usuario(String email, String senha, Role role) {
		this.email = email;
		this.senha = senha;
		this.roles.add(role);
	} 
	
	public Usuario(Long id, String nome, Long idUsuarioConvite, Long idUsuarioConviteNv2, String email, String senha, boolean ativo,
			LocalDateTime dataCriado, LocalDateTime dataAlterado, List<Role> roles, Empresa empresa, String foto, PlanoAcesso planoAcesso) {
		super();
		this.id = id;
		this.nome = nome;
		this.idUsuarioConvite = idUsuarioConvite;
		this.idUsuarioConviteNv2 = idUsuarioConviteNv2;
		this.email = email;
		this.senha = senha;
		this.ativo = ativo;
		this.dataCriado = dataCriado;
		this.dataAlterado = dataAlterado;
		this.roles = roles;
		this.empresa = empresa;
		this.foto = foto;
		this.planoAcesso = planoAcesso;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public LocalDateTime getDataCriado() {
		return dataCriado;
	}

	public void setDataCriado(LocalDateTime dataCriado) {
		this.dataCriado = dataCriado;
	}

	public LocalDateTime getDataAlterado() {
		return dataAlterado;
	}

	public void setDataAlterado(LocalDateTime dataAlterado) {
		this.dataAlterado = dataAlterado;
	}

	public Long getIdUsuarioConvite() {
		return idUsuarioConvite;
	}
	
	public void setIdUsuarioConvite(Long idUsuarioConvite) {
		this.idUsuarioConvite = idUsuarioConvite;
	}
	
	public Long getIdUsuarioConviteNv2() {
		return idUsuarioConviteNv2;
	}
	
	public void setIdUsuarioConviteNv2(Long idUsuarioConviteNv2) {
		this.idUsuarioConviteNv2 = idUsuarioConviteNv2;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}

	public PlanoAcesso getPlanoAcesso() {
		return planoAcesso;
	}
	
	public void setPlanoAcesso(PlanoAcesso planoAcesso) {
		this.planoAcesso = planoAcesso;
	}
	
	@JsonDeserialize(contentUsing = GrantedAuthorityDeserializer.class)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(Role role: roles) {
			if(role.getNome().contains("ADMIN")) {
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}else {
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			}
		}
		return authorities;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", idUsuarioConvite=" + idUsuarioConvite + ", idUsuarioConviteNv2="+ idUsuarioConviteNv2 +", email=" + email + ", ativo=" + ativo + ", dataCriado="
				+ dataCriado + ", dataAlterado=" + dataAlterado + ", roles=" + roles.toArray() +", foto=" + foto +"]";
	}

}
