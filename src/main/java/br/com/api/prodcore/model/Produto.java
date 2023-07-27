package br.com.api.prodcore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nome;
	private Integer categoria;
	
	@Column(name = "sub_categoria")
	private Integer subCategoria;
	
	@Column(columnDefinition = "TEXT")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "plano_id")
	@JsonIgnore
	private Plano plano;
	
	@ElementCollection
	private List<String> imagens;

	public Produto() {
		super();
	}
	
	public Produto(Long id, String nome, Integer categoria, Integer subCategoria, String descricao,
			Plano plano, List<String> imagens) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.subCategoria = subCategoria;
		this.descricao = descricao;
		this.plano = plano;
		this.imagens = imagens;
	}

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

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Integer getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(Integer subCategoria) {
		this.subCategoria = subCategoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Plano getPlano() {
		return plano;
	}
	
	public void setPlano(Plano plano) {
		this.plano = plano;
	}
	
	public List<String> getImagens() {
		return imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", subCategoria=" + subCategoria
				+ ", descricao=" + descricao + ", plano=" + plano + ", imagens=" + imagens + "]";
	}
	
}
