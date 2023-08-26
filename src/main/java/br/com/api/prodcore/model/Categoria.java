package br.com.api.prodcore.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categoria")
public class Categoria {

		@Id
		private int id;
		private String nome;
		
		@OneToMany(mappedBy = "categoria")
		@JsonIgnore
		private List<SubCategoria> subCategoria;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public String getNome() {
			return nome;
		}
		
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public List<SubCategoria> getSubCategoria() {
			return subCategoria;
		}
		
		public void setSubCategoria(List<SubCategoria> subCategoria) {
			this.subCategoria = subCategoria;
		}

		@Override
		public String toString() {
			return "Categoria [id=" + id + ", nome=" + nome + ", subCategoria=" + subCategoria +"]";
		}
		
		
}

