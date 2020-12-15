package com.agenda.controller;

import java.util.List;
import java.util.Optional;

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
import com.agenda.domain.repository.ContatoRepository;
import com.agenda.domain.service.ContatoService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private ContatoService contatoService;
	
	@GetMapping
	@JsonView(Views.Contato.class)
	public List<Contato> listar() {
		return contatoRepository.findAll();
	}
	
	@GetMapping("/{usuarioId}")
	@JsonView(Views.Contato.class)
	public ResponseEntity<Contato> buscar(@PathVariable Long usuarioId) {
		Optional<Contato> contato = contatoRepository.findById(usuarioId);
		
		if (contato.isPresent()) {
			return ResponseEntity.ok(contato.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(Views.Contato.class)
	public Contato criar(@RequestBody Contato contato) throws Exception {
		
		return contatoService.criar(contato);
		
	}
	
	@PutMapping("/{usuarioId}")
	@JsonView(Views.Contato.class)
	public ResponseEntity<Contato> atualizar(@Valid @PathVariable Long usuarioId,
			@RequestBody Contato contato) {
		
		if (!contatoRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		
		contato.setId(usuarioId);
		contato = contatoService.salvar(contato);
		
		return ResponseEntity.ok(contato);
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> remover(@PathVariable Long usuarioId) {
		if (!contatoRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		
		contatoService.excluir(usuarioId);
		
		return ResponseEntity.noContent().build();
	}


}
