package com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agenda.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findOneByEmail(String email);
	
	@Query("from Usuario usuario where usuario.email = ?1")
	Usuario findByEmail(String email);	
	Usuario findBySenha(String senha);
	Object findByEmail(Long id);	    
}
