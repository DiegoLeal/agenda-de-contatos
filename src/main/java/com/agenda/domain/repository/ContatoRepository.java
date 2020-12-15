package com.agenda.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agenda.domain.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
	
	@Query("select usuario from Usuario usuario")
	List<Contato> findByEmail(String email);	
	List<Contato> findByEmailContaining(String email);

}
