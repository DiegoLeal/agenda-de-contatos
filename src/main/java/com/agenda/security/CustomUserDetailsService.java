package com.agenda.security;
/*package br.com.gritto.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gritto.domain.model.Usuario;
import br.com.gritto.domain.repository.UsuarioRepository;



@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findBySenha(userName)
				.orElseThrow(() -> new UsernameNotFoundException("Senha " + userName + " not found"));
		return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getSenha(),
				getAuthorities(usuario));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
		String[] userRoles = usuario.getRoles().stream().map((role) -> role.getNome()).toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}
}*/

