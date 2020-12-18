package com.agenda.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.config.view.Views;
import com.agenda.domain.model.Contato;
import com.agenda.domain.model.Usuario;
import com.agenda.domain.repository.ContatoRepository;
import com.agenda.domain.repository.UsuarioRepository;
import com.agenda.domain.service.ContatoService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/contatos")
@Resource
public class ContatoController {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private ContatoService contatoService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@JsonView(Views.Contato.class)
	@GetMapping("/usuario-{usuarioId}")
	public ResponseEntity<List<Contato>> findAll(@PathVariable Long usuarioId) {
		return ResponseEntity.ok().body(contatoRepository.findAllByUsuarioId(usuarioId));
	}	
	
	@GetMapping("/{contatoId}/usuario}")
	@JsonView(Views.Contato.class)
	public ResponseEntity<Contato> findById(@PathVariable Long contatoId, @PathVariable Long usuarioId) {
		Optional<Contato> contato = contatoRepository.findById(contatoId);
		
		if(contato.isPresent() && contato.get().getUsuario().getId() == usuarioId) {
			return ResponseEntity.ok().body(contato.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(Views.Contato.class)
	public ResponseEntity<Contato> criar(@Valid @RequestBody Contato contato) throws Exception {
		Usuario usuario_id = contato.getUsuario();
		Optional<Usuario> usuario = usuarioRepository.findById(usuario_id.getId()); 
		
		if(usuario != null) {
			contato.setUsuario(usuario.get());
		}
		
		Contato c = contatoService.criar(contato);
		
		contatoService.criar(c);
		
		return ResponseEntity.ok().body(c);
		
	}	
		
	@PutMapping("/{contatoId}")
	@JsonView(Views.Contato.class)
	public ResponseEntity<Contato> atualizar(@Valid @PathVariable Long contatoId,
			@RequestBody Contato contato) throws Exception {
		
		if (!contatoRepository.existsById(contatoId)) {
			return ResponseEntity.notFound().build();
		}
		
		Usuario usuario_id = contato.getUsuario();
		Optional<Usuario> usuario = usuarioRepository.findById(usuario_id.getId()); 
		
		if(usuario != null) {
			contato.setUsuario(usuario.get());
		}
		
		contato.setId(contatoId);
		contato = contatoService.criar(contato);
		
		return ResponseEntity.ok().body(contato);
	}
	
	@DeleteMapping("/{contatoId}")
	public ResponseEntity<Void> remover(@PathVariable Long contatoId) {
		if (!contatoRepository.existsById(contatoId)) {
			return ResponseEntity.notFound().build();
		}
		
		contatoService.excluir(contatoId);
		
		return ResponseEntity.noContent().build();
	}
}
