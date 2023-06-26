package br.com.api.prodcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.prodcore.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findUsuarioByLogin(String login);
	
	@Query(value = "select u from Usuario u")
	List<Usuario> findAllUsuario();

	@Query(value = "select u, nu from Usuario u inner join u.nivelUsuario nu on fk_nivel_usuario_id = nu.id where u.nome = :nome OR u.email = :email OR u.login = :login")
	Usuario procuraUsuarioPorNomeOuEmailOuLogin(@Param("nome") String nome, @Param("email") String email, @Param("login") String login);

	Usuario findByEmailOrLogin(String email, String login);
	
}
