package br.com.api.prodcore.model;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "id_usuario_convite")
	private Long idUsuarioConvite;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "ativo")
	private boolean ativo;

	//@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.S]")	
	@Column(name = "data_criado")
	private LocalDateTime dataCriado;
	
	//@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.S]")
	@Column(name = "data_alterado")
	private LocalDateTime dataAlterado;
	
	@ManyToMany
	@JoinTable(name = "user_roles",
		joinColumns = @JoinColumn(name = "usuario_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	/*
	@OneToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	*/
	@Column(name = "foto", columnDefinition = "text")
	private String foto;
	
	public Usuario() {} 
	
	public Usuario(Long id, String nome, Long idUsuarioConvite, String email, String senha, boolean ativo,
			LocalDateTime dataCriado, LocalDateTime dataAlterado, List<Role> roles, /*Empresa empresa,*/ String foto) {
		super();
		this.id = id;
		this.nome = nome;
		this.idUsuarioConvite = idUsuarioConvite;
		this.email = email;
		this.senha = senha;
		this.ativo = ativo;
		this.dataCriado = dataCriado;
		this.dataAlterado = dataAlterado;
		this.roles = roles;
		//this.empresa = empresa;
		this.foto = foto;
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
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", idUsuarioConvite=" + idUsuarioConvite +", email=" + email + ", senha=" + senha + ", ativo=" + ativo + ", dataCriado="
				+ dataCriado + ", dataAlterado=" + dataAlterado + ", roles=" + roles.toArray() +", foto=" + foto +"]";
	}

}
