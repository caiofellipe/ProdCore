package br.com.api.prodcore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "convite")
public class Convite {
	@Id
	@GeneratedValue()
	Long id;
}
