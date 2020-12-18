package com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agenda.domain.model.Contato;
import com.agenda.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {		
	
	@Query("from Usuario where email = ?1")
	Usuario findOneByEmail(String email);	
	Usuario findBySenha(String senha);
	Object findByEmail(Long id);
	Contato save(Contato contato);	    
}
