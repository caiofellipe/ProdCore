package br.com.api.prodcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.prodcore.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	
	Empresa findByCNPJ(String cnpj);
}
