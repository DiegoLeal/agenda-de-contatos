package com.agenda.domain.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agenda.domain.model.Usuario;
import com.agenda.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder encoder;
	
	public Usuario salvar(Usuario usuario) throws Exception {
		Usuario usuarioExis = usuarioRepository.findOneByEmail(usuario.getEmail());

		if (usuarioExis != null && !usuarioExis.equals(usuario)) {
			throw new Exception("Ja exixte um Usuario cadastrado com este Email");
		}

		String encodedPassword = encoder.encode(usuario.getSenha());
		usuario.setSenha(encodedPassword);

		return usuarioRepository.save(usuario);
	}

	
	public Usuario atualizar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
  
	public void excluir(Long UsuarioId) {
		usuarioRepository.deleteById(UsuarioId);
	}

	public Usuario getUsuario(String username) {
		return usuarioRepository.findOneByEmail(username);
	}
}