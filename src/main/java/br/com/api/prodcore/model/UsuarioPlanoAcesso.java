package br.com.api.prodcore.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "usuario_plano_acesso")
public class UsuarioPlanoAcesso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "usuario_id")
	private Long usuarioId;
	
	@Column(name = "plano_acesso_id")
	private Long planoAcessoId;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.S]")	
	@Column(name = "data_contratado")
	private LocalDateTime dataContratado;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.S]")	
	@Column(name = "data_expiracao")
	private LocalDateTime dataExpiracao;

	public UsuarioPlanoAcesso() {
		super();
	}
	
	public UsuarioPlanoAcesso(Long usuarioId, Long planoAcessoId, LocalDateTime dataContratado,
			LocalDateTime dataExpiracao) {
		super();
		this.usuarioId = usuarioId;
		this.planoAcessoId = planoAcessoId;
		this.dataContratado = dataContratado;
		this.dataExpiracao = dataExpiracao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getPlanoAcessoId() {
		return planoAcessoId;
	}

	public void setPlanoAcessoId(Long planoAcessoId) {
		this.planoAcessoId = planoAcessoId;
	}

	public LocalDateTime getDataContratado() {
		return dataContratado;
	}

	public void setDataContratado(LocalDateTime dataContratado) {
		this.dataContratado = dataContratado;
	}

	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDateTime dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	
	

}
