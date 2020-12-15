package com.agenda.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agenda.domain.service.UsuarioService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {


  private final UsuarioService usuarioService;

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails userDetails = usuarioService.getUsuario(username);

    if(userDetails != null) {
      return userDetails;
    }

    throw new UsernameNotFoundException(username);
  }


}
