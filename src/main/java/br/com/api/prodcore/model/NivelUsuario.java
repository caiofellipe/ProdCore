package br.com.api.prodcore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "nivel_usuario")
@SequenceGenerator(name = "ID", sequenceName = "ID", allocationSize = 1, initialValue = 1)
public class NivelUsuario implements GrantedAuthority{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID")
	private Long id;
	
	@Column(nullable = true)
	private String nivel;
	
	@Column(nullable = true)
	private String sigla;
	
	public NivelUsuario() {
		super();
	}
	
	public NivelUsuario(String nivel, String sigla) {
		super();
		this.nivel = nivel;
		this.sigla = sigla;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@JsonIgnore
	@Override
	public String getAuthority() {
		return this.sigla;
	}
	
	
}
