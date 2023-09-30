package br.com.api.prodcore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "nivel_acesso")
public class NivelAcesso {
	@Id
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@OneToMany(mappedBy = "nivelAcesso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<BeneficioAcesso> beneficioAcesso;
	
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
	
	public List<BeneficioAcesso> getBeneficioAcesso() {
		return beneficioAcesso;
	}
	
	public void setBeneficioAcesso(List<BeneficioAcesso> beneficioAcesso) {
		this.beneficioAcesso = beneficioAcesso;
	}
	
}
