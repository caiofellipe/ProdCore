package br.com.api.prodcore.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.prodcore.model.NivelAcesso;
import br.com.api.prodcore.model.PlanoAcesso;

@Repository
public interface PlanoAcessoRepository extends JpaRepository<PlanoAcesso, Long>{
	
	@Query(value = "SELECT pa FROM PlanoAcesso pa WHERE pa.nome LIKE '%:nome%' AND pa.valor = :valor")
	PlanoAcesso findByNomeEValor(String nome, BigDecimal valor);
	
	@Query(value = "SELECT DISTINCT pa, na, ba"
			+ " FROM PlanoAcesso pa"
			+ " JOIN FETCH pa.nivelAcesso na"
			+ " JOIN FETCH na.beneficioAcesso ba")
	List<PlanoAcesso> listarTodosNiveisDeAcessoComBeneficio();
	
	@Query(value = "SELECT DISTINCT na, ba"
			+ " FROM NivelAcesso na"
			+ " JOIN FETCH na.beneficioAcesso ba"
			+ " ORDER BY na.id")
	List<NivelAcesso> listarNiveisDeAcessoComBeneficios();
	

	@Query(value = "SELECT na FROM NivelAcesso na ORDER BY na.id")
	List<NivelAcesso> listarNiveisDeAcesso();

	
	@Query(value = "SELECT pa FROM PlanoAcesso pa "
			+ "JOIN FETCH pa.nivelAcesso "
			+ "WHERE pa.id = :id")
	PlanoAcesso pesquisaPorId(Long id);

}
