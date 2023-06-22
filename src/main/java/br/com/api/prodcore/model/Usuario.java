package br.com.api.prodcore.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
@SequenceGenerator(name = "ID", sequenceName = "ID", allocationSize = 1, initialValue = 1)
public class Usuario implements UserDetails{
	
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
	
	@Column(nullable = false)
	private String senha;
	
	@Column(name = "id_usuario", nullable = false)
	private String idUsuario;
	
	@Column(nullable = false)
	private boolean ativo;
	
	@Column(name = "data_criado", nullable = false)
	private Date dataCriado;
	
	@Column(name = "data_alterado", nullable = false)
	private Date dataAlterado;
	
	@ManyToOne(targetEntity = NivelUsuario.class)
	@JoinColumn(name = "fk_nivelUsuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_nivelUsuario_id"))
	private List<NivelUsuario> nivelUsuario;
	
	public Usuario() {}
	
	public Usuario(String nome, String sobrenome, String email, String idUsuario, boolean ativo,
			Date dataCriado, Date dataAlterado, List<NivelUsuario> nivelUsuario) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.idUsuario = idUsuario;
		this.ativo = ativo;
		this.dataCriado = dataCriado;
		this.dataAlterado = dataAlterado;
		this.nivelUsuario = nivelUsuario;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.nivelUsuario;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
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


}
