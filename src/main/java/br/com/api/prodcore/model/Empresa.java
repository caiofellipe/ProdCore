package br.com.api.prodcore.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "empresa")
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nome;
	private String cnpj;
	private String email;
	private String ramo;
	private String telefone;
	
	@OneToOne(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private Endereco endereco;
	
	@Column(name = "logo", columnDefinition = "text")
	private String logo;
	
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Plano> plano;
	
	public Empresa() {
		super();
	}
	
	public Empresa(Long id, String nome, String cnpj, String email, String ramo, String telefone, Endereco endereco,
			String logo, List<Plano> plano) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
		this.ramo = ramo;
		this.telefone = telefone;
		this.endereco = endereco;
		this.logo = logo;
		this.plano = plano;
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
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRamo() {
		return ramo;
	}
	public void setRamo(String ramo) {
		this.ramo = ramo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
		if(endereco != null) {
			endereco.setEmpresa(this);
		}
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public List<Plano> getPlanos() {
		return plano;
	}
	public void setPlanos(List<Plano> plano) {
		this.plano = plano;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", email=" + email + ", ramo=" + ramo
				+ ", telefone=" + telefone + ", endereco=" + endereco + ", logo=" + logo + ", plano="
				+ plano + "]";
	}

}
