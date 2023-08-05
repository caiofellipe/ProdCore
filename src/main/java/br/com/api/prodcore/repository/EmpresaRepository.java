package br.com.api.prodcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.prodcore.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	
	Empresa findByCnpj(String cnpj);
	
	@Query(value = "select distinct e.* from empresa e"
			+ " inner join endereco en on en.empresa_id = e.id"
			+ " inner join plano pl on pl.empresa_id  = e.id ", nativeQuery = true)
	List<Empresa> findAllEmpresa();
}
