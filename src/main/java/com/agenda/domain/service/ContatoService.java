package com.agenda.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.domain.model.Contato;
import com.agenda.domain.model.Usuario;
import com.agenda.domain.repository.ContatoRepository;
import com.agenda.domain.repository.UsuarioRepository;

@Service
public class ContatoService {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Contato criar(Contato contato) throws Exception {
		Usuario usuario = usuarioRepository.findById(contato.getUsuario().getId())
				.orElseThrow(() -> new Exception("Usuario não encontrado"));
		
		contato.setUsuario(usuario);
		return contatoRepository.save(contato);
	}
	
    public Contato salvar(Contato contato) {
		
		return contatoRepository.save(contato);
	}

	public void excluir(Long usuarioId) {		
		contatoRepository.deleteById(usuarioId);
	}

}
