package br.com.api.prodcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.prodcore.model.BeneficioAcesso;

@Repository
public interface BeneficioAcessoRepository extends JpaRepository<BeneficioAcesso, Long>{
	
	@Query(value = "SELECT b FROM BeneficioAcesso b "
			+ "INNER JOIN NivelAcesso n "
			+ "ON n.id = b.nivelAcesso.id "
			+ "WHERE b.nivelAcesso.id = :idNivelAcesso")
	List<BeneficioAcesso> pesquisaBeneficioAcessoPeloIdDoNivelAcesso(Long idNivelAcesso);

	@Query(value = "SELECT b FROM BeneficioAcesso b "
			+ "WHERE b.nome LIKE '%:nome%'")
	BeneficioAcesso likeNomeBeneficio(String nome);
}
