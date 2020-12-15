package com.agenda.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.agenda.domain.model.Role;
import com.agenda.domain.model.Usuario;



public class AuthenticatedUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	
	public AuthenticatedUser(Usuario usuario) {
		super(usuario.getEmail(), usuario.getPassword(), getAuthorities(usuario));
		this.usuario = usuario;
	}
	
	public Usuario getUser() {
		return usuario;
	}
	
	private static Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario)	{
		Set<String> roleAndPermissions = new HashSet<>();
		List<Role> roles = usuario.getRoles();
		
		for (Role role : roles)	{
			roleAndPermissions.add(role.getNome());
		}
		
		String[] roleNames = new String[roleAndPermissions.size()];
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleAndPermissions.toArray(roleNames));
		return authorities;
	}
}

