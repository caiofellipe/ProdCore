package br.com.api.prodcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.api.prodcore.model.Usuario;
import br.com.api.prodcore.model.UserRoles;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(value = "select u from Usuario u where u.email = :email")
	Usuario findByUsuarioEmail(String email);
	
	@Query(value = "SELECT u FROM Usuario u JOIN FETCH u.role WHERE u.email = :email")
	UserDetails findByEmail(String email);
	
	@Query(value = "select u from Usuario u JOIN FETCH u.role where u.email = :email")
	Usuario findByUseremailFetchRoles(String email);
	
	@Query(value = "SELECT ur from UserRoles ur where ur.usuarioId = :usuarioId AND ur.roleId = :roleId")
	UserRoles findUserRoles(Long usuarioId, Long roleId);
	
	@Query(value = "SELECT u FROM Usuario u "
			+ "JOIN FETCH u.role "
			+ "JOIN FETCH u.empresa "
			+ "WHERE u.email = :email")
	Usuario procuraUsuarioComEmpresaEPlano(String email);
}
