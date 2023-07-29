package br.com.api.prodcore.model;

import javax.persistence.Column;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "sub_categoria_id")
	private SubCategoria subCategoria;
	
	@Column(columnDefinition = "TEXT")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "plano_id")
	@JsonIgnore
	private Plano plano;
	
	@Column(name = "imagem", columnDefinition = "text")
	private String imagem;

	public Produto() {
		super();
	}
	
	public Produto(Long id, String nome, Categoria categoria, SubCategoria subCategoria, String descricao,
			Plano plano, String imagem) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.subCategoria = subCategoria;
		this.descricao = descricao;
		this.plano = plano;
		this.imagem = imagem;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public SubCategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoria subCategoria) {
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
	
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", subCategoria=" + subCategoria
				+ ", descricao=" + descricao + ", plano=" + plano + ", imagem=" + imagem + "]";
	}
	
}
