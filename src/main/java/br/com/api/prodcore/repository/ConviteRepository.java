package br.com.api.prodcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.api.prodcore.model.Convite;

@Repository
public interface ConviteRepository extends JpaRepository<Convite, Long> {

}
