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
import com.agenda.domain.model.Usuario;
import com.agenda.domain.repository.UsuarioRepository;
import com.agenda.domain.service.UsuarioService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService cadastroUsuario;

	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		return ResponseEntity.ok().body(usuarioRepository.findAll());
	}
	
	@PostMapping("/email")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Usuario> findByEmail(@RequestBody Usuario usuario) {
		return ResponseEntity.ok().body(usuarioRepository.findOneByEmail(usuario.getEmail()));
	}

	@GetMapping("{usuarioId}")
	public ResponseEntity<Usuario> buscar(@PathVariable("id") Long usuarioId) {
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			return ResponseEntity.ok().body(usuario.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView({ Views.List.class })
	public Usuario criar(@Valid @RequestBody Usuario usuario) throws Exception {
		return cadastroUsuario.salvar(usuario);
	}

	@PutMapping("/{usuarioId}")
	public ResponseEntity<Usuario> Atualizar(@Valid @PathVariable Long usuarioId, @RequestBody Usuario usuario)
			throws Exception {

		if (!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}

		usuario.setId(usuarioId);
		usuario = cadastroUsuario.atualizar(usuario);

		return ResponseEntity.ok(usuario);
	}

	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> remover(@PathVariable Long usuarioId) {
		if (!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}

		cadastroUsuario.excluir(usuarioId);

		return ResponseEntity.noContent().build();
	}

}
