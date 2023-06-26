package br.com.api.prodcore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_nivel_usuario")
public class UsuarioNivelUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "id")
	private Usuario usuario;
	
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "id")
	private NivelUsuario nivelUsuario;

	public UsuarioNivelUsuario() {}
	
	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public NivelUsuario getNivelUsuario() {
		return nivelUsuario;
	}

	public void setNivelUsuario(NivelUsuario nivelUsuario) {
		this.nivelUsuario = nivelUsuario;
	}
	
	
}
