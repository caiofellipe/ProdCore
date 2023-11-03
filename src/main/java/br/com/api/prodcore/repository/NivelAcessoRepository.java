package br.com.api.prodcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.prodcore.model.NivelAcesso;

public interface NivelAcessoRepository extends JpaRepository<NivelAcesso, Long> {

	@Query(value = "SELECT nb "
			+ "FROM NivelAcesso nb "
			+ "WHERE nb.nome LIKE '%:nomeNivelAcesso%'")
	NivelAcesso pesquisaNivelAcessoPeloNome(String nomeNivelAcesso);
	
	
}
