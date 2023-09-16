package br.com.api.prodcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.prodcore.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	
	Empresa findByCnpj(String cnpj);
	
	@Query(value = "SELECT e.*, en.* FROM empresa e"
			+ " INNER JOIN endereco en ON en.empresa_id = e.id", nativeQuery = true)
	List<Empresa> todasEmpresasComEndereco();
}
