package br.com.api.prodcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.prodcore.model.NivelAcesso;

public interface NivelAcessoRepository extends JpaRepository<NivelAcesso, Long> {

	@Query(value = "SELECT nb "
			+ "FROM NivelAcesso nb "
			+ "JOIN FETCH nb.beneficioAcesso "
			+ "WHERE nb.id = :id")
	List<NivelAcesso> pesquisaNivelAcessoEBeneficioAcessoPorId(Long id);
	
}
