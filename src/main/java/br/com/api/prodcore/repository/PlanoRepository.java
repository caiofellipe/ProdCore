package br.com.api.prodcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.prodcore.model.Plano;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long>{

}
