package br.com.api.prodcore.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "ID", sequenceName = "ID", allocationSize = 1, initialValue = 1)
public class Usuario {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String sobrenome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String login;
	
	@Column(nullable = true)
	private String senha;
	
	@Column(name = "id_usuario", nullable = false)
	private String idUsuario;
	
	@Column(nullable = false)
	private boolean ativo;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.S]")	
	@Column(name = "data_criado", nullable = false)
	private Date dataCriado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.S]")
	@Column(name = "data_alterado", nullable = false)
	private Date dataAlterado;
	
	@ManyToMany
	@JoinColumn(name = "fk_nivel_usuario_id")
	private List<NivelUsuario> nivelUsuario;
	
	public Usuario() {}
	
	public Usuario(Long id, String nome, String sobrenome, String email, String idUsuario, boolean ativo,
			Date dataCriado, Date dataAlterado, List<NivelUsuario> nivelUsuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.idUsuario = idUsuario;
		this.ativo = ativo;
		this.dataCriado = dataCriado;
		this.dataAlterado = dataAlterado;
		this.nivelUsuario = nivelUsuario;
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
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Date getDataCriado() {
		return dataCriado;
	}

	public void setDataCriado(Date dataCriado) {
		this.dataCriado = dataCriado;
	}

	public Date getDataAlterado() {
		return dataAlterado;
	}

	public void setDataAlterado(Date dataAlterado) {
		this.dataAlterado = dataAlterado;
	}

	public List<NivelUsuario> getNivelUsuario() {
		return nivelUsuario;
	}
	public void setNivelUsuario(List<NivelUsuario> nivelUsuario) {
		this.nivelUsuario = nivelUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email + ", login="
				+ login + ", senha=" + senha + ", idUsuario=" + idUsuario + ", ativo=" + ativo + ", dataCriado="
				+ dataCriado + ", dataAlterado=" + dataAlterado + ", nivelUsuario=" + nivelUsuario + "]";
	}

}
