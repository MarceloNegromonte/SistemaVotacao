package com.apisistemaVotacao.sistemaVotacao.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apisistemaVotacao.sistemaVotacao.model.Usuario;
import com.apisistemaVotacao.sistemaVotacao.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

/*@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { 
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email); 
		log.info("Buscando email");
		usuario.orElseThrow(() -> new UsernameNotFoundException(email + " not found."));
	
		return usuario.map(UserDetailsImpl::new).get();
	}
}*/
