package br.com.api.prodcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.prodcore.model.UsuarioPlanoAcesso;

public interface UsuarioPlanoAcessoRepository extends JpaRepository<UsuarioPlanoAcesso, Long> {

	@Query(value = "SELECT upa FROM UsuarioPlanoAcesso upa "
			+ "WHERE upa.usuarioId = :usuarioId AND upa.planoAcessoId = :planoAcessoId")
	UsuarioPlanoAcesso pesquisaPorUsuarioIdEPlanoAcessoId(Long usuarioId, Long planoAcessoId);
}
