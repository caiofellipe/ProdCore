package br.com.api.prodcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.prodcore.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findUsuarioByLogin(String login);
	
	@Query(value = "select u from Usuario u")
	List<Usuario> findAllUsuario();
	
}
