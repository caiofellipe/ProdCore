package br.com.api.prodcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.prodcore.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	@Query("SELECT en, emp FROM Endereco en INNER JOIN  Empresa emp ON en.empresa = emp.id WHERE en.uf = :uf AND en.cidade LIKE %:cidade%")
	List<Endereco> localizacaoEmpresa(String uf, String cidade);
	
}
