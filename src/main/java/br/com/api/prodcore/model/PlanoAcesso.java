package br.com.api.prodcore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "plano_acesso")
public class PlanoAcesso {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(columnDefinition = "TEXT")
	private String descricao;
	private BigDecimal valor;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.S]")	
	@Column(name = "data_editado")
	private LocalDateTime dataEditado;
	
	@OneToMany(mappedBy = "planoAcesso", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	private List<Usuario> usuario;
	
	@Column(name = "editado_pelo_usuario_id")
	private Long editadoPeloUsuarioId;
	
	@OneToOne
	@JoinColumn(name = "nivel_acesso_id")
	private NivelAcesso nivelAcesso;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public LocalDateTime getDataEditado() {
		return dataEditado;
	}
	public void setDataEditado(LocalDateTime dataEditado) {
		this.dataEditado = dataEditado;
	}
	public List<Usuario> getUsuario() {
		return usuario;
	}
	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}
	
	public Long getEditadoPeloUsuarioId() {
		return editadoPeloUsuarioId;
	}
	
	public void setEditadoPeloUsuarioId(Long editadoPeloUsuarioId) {
		this.editadoPeloUsuarioId = editadoPeloUsuarioId;
	}
	
	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}
	public void setNivelAcesso(NivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	
}
