package br.com.api.prodcore.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sobrenome")
	private String sobrenome;
	
	@Column(name = "id_usuario_convite")
	private String idUsuarioConvite;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "id_usuario")
	private UUID idUsuario;
	
	@Column(name = "ativo")
	private boolean ativo;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.S]")	
	@Column(name = "data_criado")
	private Date dataCriado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.S]")
	@Column(name = "data_alterado")
	private Date dataAlterado;
	
	@ManyToOne
	@JoinColumn(name = "nivel_usuario_id")
	private NivelUsuario nivelUsuario;
	
	@OneToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	private byte[] foto;
	
	public Usuario() {} 
	
	public Usuario(Long id, String nome, String sobrenome, String idUsuarioConvite, String email, String login, String senha, UUID idUsuario, boolean ativo,
			Date dataCriado, Date dataAlterado, NivelUsuario nivelUsuario, Empresa empresa, byte[] foto) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.idUsuarioConvite = idUsuarioConvite;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.idUsuario = idUsuario;
		this.ativo = ativo;
		this.dataCriado = dataCriado;
		this.dataAlterado = dataAlterado;
		this.nivelUsuario = nivelUsuario;
		this.empresa = empresa;
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

	public UUID getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(UUID idUsuario) {
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

	public NivelUsuario getNivelUsuario() {
		return nivelUsuario;
	}
	public void setNivelUsuario(NivelUsuario nivelUsuario) {
		this.nivelUsuario = nivelUsuario;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public String getIdUsuarioConvite() {
		return idUsuarioConvite;
	}
	
	public void setIdUsuarioConvite(String idUsuarioConvite) {
		this.idUsuarioConvite = idUsuarioConvite;
	}
	
	public byte[] getFoto() {
		return foto;
	}
	
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email + ", login="
				+ login + ", senha=" + senha + ", idUsuario=" + idUsuario + ", ativo=" + ativo + ", dataCriado="
				+ dataCriado + ", dataAlterado=" + dataAlterado + ", nivelUsuario=" + nivelUsuario + ", empresId=" + empresa + ", foto=" + foto +", idUsuarioConvite=" + idUsuarioConvite +"]";
	}

}
