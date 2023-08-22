package br.com.api.prodcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.prodcore.model.Usuario;
import br.com.api.prodcore.model.UserRoles;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(value = "select u from Usuario u where u.email = :email")
	Usuario findByEmail(String email);
	
	@Query(value = "select u from Usuario u JOIN FETCH u.roles where u.email = :email")
	Usuario findByUseremailFetchRoles(String email);
	
	
	@Query(value = "SELECT ur from UserRoles ur where ur.usuarioId = :usuarioId AND ur.rolesId = :roleId")
	UserRoles findUserRoles(Long usuarioId, Long roleId);
	
}
